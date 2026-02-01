package filesystem;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class LsCommand implements Command {

    @Override
    public Object execute(String[] params) {
        if(params.length != 1) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        String filePath = params[0];
        List<String> filenames = new LinkedList<>();
        File file = FileUtils.getFile(filePath);
        if (file != null && file.getDirectory()) {
            for (File child : file.getChildren()) {
                filenames.add(child.getPath());
            }
        }
        filenames.sort(Comparator.comparing(t->t, String::compareTo));
        return filenames;
    }

    @Override
    public String name() {
        return "ls";
    }
}
