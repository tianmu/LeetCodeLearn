import java.util.concurrent.*;

public class SimpleTimeoutProcessor {
    // 创建线程池
    private final ExecutorService executorService;

    // 创建自定义的线程工厂，方便识别线程来源
    private static final ThreadFactory threadFactory = new ThreadFactory() {
        private int count = 1;
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("async-task-thread-" + count++);
            return thread;
        }
    };

    public SimpleTimeoutProcessor() {
        // 根据CPU核心数设置线程池
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maximumPoolSize = corePoolSize * 2;
        long keepAliveTime = 60L;

        // 创建有界队列，避免无限制地接收任务
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(1000);

        this.executorService = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                workQueue,
                threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy() // 使用调用者运行策略防止任务丢失
        );
    }

    public void processTask() {
        // 任务A：设置超时
        CompletableFuture<String> taskA = CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(600); // 模拟耗时操作
                        System.out.println("taskA");
                        return "A的结果";
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new CompletionException(e);
                    }
                }, executorService)
//                .completeOnTimeout(null,400, TimeUnit.MILLISECONDS)
                .orTimeout(400, TimeUnit.MILLISECONDS)
                .handle((result, throwable) -> {
                    if (throwable != null) {
                        if (throwable instanceof TimeoutException) {
                            return null;  // 超时返回null
                        }
                        throw new CompletionException(throwable);
                    }
                    return result;
                });

        // 任务B
        CompletableFuture<String> taskB = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(200); // 模拟耗时操作
                System.out.println("taskB");
                return "B的结果";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new CompletionException(e);
            }
        }, executorService);

        // 组合A和B，执行C
        taskA.thenCombine(taskB, (resultA, resultB) -> {
            if (resultA == null) {
                System.out.println("A超时，仅使用B的结果执行C");
                return "C的结果(只用B: " + resultB + ")";
            } else {
                System.out.println("A正常完成，使用A和B的结果执行C");
                return "C的结果(A: " + resultA + ", B: " + resultB + ")";
            }
        }).thenAccept(System.out::println);
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        SimpleTimeoutProcessor processor = new SimpleTimeoutProcessor();
        processor.processTask();

        try {
            Thread.sleep(1000); // 等待任务完成
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        processor.shutdown();
    }
}
