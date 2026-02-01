import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimeoutTaskProcessor {
    private final ExecutorService executorService;

    public TimeoutTaskProcessor() {
        this.executorService = Executors.newCachedThreadPool();
    }

    public void processTask() {
        // 使用AtomicBoolean来安全地追踪A是否超时
        AtomicBoolean aTimedOut = new AtomicBoolean(false);

        // 创建原始的A任务
        CompletableFuture<String> originalTaskA = CompletableFuture.supplyAsync(() -> {
            try {
                // 模拟一个可能耗时的任务
                Thread.sleep(600); // 这里设置600ms来测试超时场景
                return "A的结果";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new CompletionException(e);
            }
        }, executorService);

        // 创建带超时的A任务
        CompletableFuture<String> taskA = originalTaskA
                .completeOnTimeout("A超时", 400, TimeUnit.MILLISECONDS);

        // 创建B任务
        CompletableFuture<String> taskB = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(200); // 模拟B任务耗时
                return "B的结果";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new CompletionException(e);
            }
        }, executorService);

        // 组合A和B，执行C
        taskA.thenCombine(taskB, (resultA, resultB) -> {
            if (resultA.equals("A超时")) {
                aTimedOut.set(true);
                System.out.println("A超时，使用B的结果执行C");
                return "C的结果(只基于B: " + resultB + ")";
            } else {
                System.out.println("A正常完成，执行C");
                return "C的结果(基于A: " + resultA + " 和 B: " + resultB + ")";
            }
        }).thenAccept(resultC -> {
            System.out.println(resultC);
        });

        // 处理原始A任务的最终完成
        originalTaskA.thenAccept(resultA -> {
            if (aTimedOut.get()) {
                System.out.println("A最终完成，执行D");
                executeTaskD(resultA);
            }
        });
    }

    private void executeTaskD(String resultA) {
        // 执行D任务的逻辑
        System.out.println("D任务执行，处理A的延迟结果: " + resultA);
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
        TimeoutTaskProcessor processor = new TimeoutTaskProcessor();
        processor.processTask();

        // 等待足够的时间以观察结果
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        processor.shutdown();
    }
}