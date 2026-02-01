package filesystem;

import java.util.LinkedList;
import java.util.List;

public class MkdirCommand implements Command {
    @Override
    public Object execute(String[] params) {
        if(params.length != 1) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        String dirPath = params[0];
        String[] split = dirPath.split(FileUtils.SPLIT);
        File fileRoot = FileUtils.getFileRoot();
        List<File> iter = new LinkedList<>();
        iter.addAll(fileRoot.getChildren());
        StringBuilder path = new StringBuilder();
        for (String fileName : split) {
            if (fileName.equals("")) {
                continue;
            }
            path.append(FileUtils.SPLIT + fileName);
            boolean exists = false;
            for (File file : iter) {
                if (file.getName().equals(fileName)) {
                    exists = true;
                    fileRoot = file;
                    break;
                }
            }
            if (!exists) {
                File file = new File();
                file.setName(fileName);
                file.setChildren(new LinkedList<>());
                file.setDirectory(true);
                file.setPath(path.toString());
                fileRoot.getChildren().add(file);
                fileRoot = file;
            }
        }

        return null;
    }

    @Override
    public String name() {
        return "mkdir";
    }
}
