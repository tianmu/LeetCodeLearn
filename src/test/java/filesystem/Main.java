package filesystem;

public class Main {

    public static void main(String[] args) {
        // 这里应该使用工厂，因为时间关系暂时不使用
        MkdirCommand mkdirSystem = new MkdirCommand();
        mkdirSystem.execute(new String[]{"/user/abc"});
        mkdirSystem.execute(new String[]{"/user/abd"});
        mkdirSystem.execute(new String[]{"/etc/001/002/003"});
        mkdirSystem.execute(new String[]{"/etc/001/002/005"});
        mkdirSystem.execute(new String[]{"/etc/001/003/005"});
        System.out.println(FileUtils.getFileRoot());

        LsCommand lsCommand = new LsCommand();
        System.out.println(lsCommand.execute(new String[]{"/user"}));
    }
}
