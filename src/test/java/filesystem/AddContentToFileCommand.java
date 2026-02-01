package filesystem;

public class AddContentToFileCommand implements Command {
    private FileUtils fileUtils = new FileUtils();
    @Override
    public Object execute(String[] params) {
        if(params.length != 2) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        String filePath = params[0];
        String content = params[1];
        File file = fileUtils.getFile(filePath);
        if(file.getContent() == null) {
            file.setContent(content);
        } else {
            file.setContent(file.getContent() + content);
        }

        return null;
    }


    @Override
    public String name() {
        return "addContentToFile";
    }
}
