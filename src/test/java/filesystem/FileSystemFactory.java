package filesystem;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂方法
 */
public class FileSystemFactory {

    private Map<String, Command> commons = new HashMap<String, Command>();

    public FileSystemFactory() {
        LsCommand lsSystem = new LsCommand();
        commons.put(lsSystem.name(), lsSystem);
        MkdirCommand mkdirSystem = new MkdirCommand();
        commons.put(mkdirSystem.name(), mkdirSystem);
        ReadContentFromFileCommand readContentFromFileSystem = new ReadContentFromFileCommand();
        commons.put(readContentFromFileSystem.name(), readContentFromFileSystem);
        AddContentToFileCommand addContentToFileSystem = new AddContentToFileCommand();
        commons.put(addContentToFileSystem.name(), addContentToFileSystem);
    }

}
