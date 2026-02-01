package filesystem;

public class ReadContentFromFileCommand implements Command {
    @Override
    public Object execute(String[] params) {
        if(params.length != 1) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        String filePath = params[0];
        File file = FileUtils.getFile(filePath);
        return file.getContent();
    }


    @Override
    public String name() {
        return "readContentFromFile";
    }
}
