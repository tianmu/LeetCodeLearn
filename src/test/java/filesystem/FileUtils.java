package filesystem;

import java.util.LinkedList;
import java.util.List;

public class FileUtils {
    /**
     * 分隔符
     */
    protected static final String SPLIT = "/";
    // TODO 找个地方初始化它
    protected static File root;

    static {
        root = new File();
        root.setChildren(new LinkedList<>());
        root.setPath("/");
    }

    public static File getFileRoot() {
        return root;
    }

    protected static File getFile(String filePath) {
        File fileRoot = getFileRoot();
        List<File> iter = new LinkedList<>();
        iter.add(fileRoot);
        while(iter.size() > 0) {
            List<File> newIter = new LinkedList<File>();
            for (File file : iter) {
                if (file.getPath() != null && file.getPath().equals(filePath)) {
                    return file;
                }
                List<File> children = file.getChildren();
                if (children != null) {
                    newIter.addAll(children);
                }
            }
            iter = newIter;
        }
        return null;
    }

}
