import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Process implements Runnable {

    ArrayList<File> list;
    String cmd;
    int processorNumber;
    int size;

    Process(int processorNumber, ArrayList<File> list, String cmd) {
        this.list = list;
        this.cmd = cmd;
        this.processorNumber = processorNumber;
        size = list.size();
    }

    public void run() {
        File file;
        while (!list.isEmpty()) {
            synchronized (list) {
                file = list.get(0);
                list.remove(0);
            }
            try {
                convert(file, cmd);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void convert(File file, String cmd) throws InterruptedException, IOException {
        MagicConverter.field1.setText(file.getName());
        String finalExt = cmd.substring(cmd.lastIndexOf("."), cmd.length() - 1);
//        if (cmd.substring(cmd.indexOf("~") + 1, cmd.indexOf("\"", cmd.indexOf("~"))).equals("")) {
//            cmd = cmd.replaceFirst("~",file.getAbsolutePath())
//            cmd = cmd.replace("~", extensionRemoove(file.getAbsolutePath()));
//        }
        cmd = cmd.replace("~", extensionRemoove(file.getAbsolutePath()));
        Runtime r = Runtime.getRuntime();
        java.lang.Process p = r.exec(cmd);
        p.waitFor();
        if (new File(extensionRemoove(file.getAbsolutePath()) + finalExt).exists()) {
            file.delete();
        }
        MagicConverter.field2.setText("    " + String.valueOf(100 * (size - list.size()) / size) + "%");
    }

    public String extensionRemoove(String fileNameWithExt) {
        String fileNameWithOutExt = fileNameWithExt.substring(0,fileNameWithExt.lastIndexOf('.'));
        return fileNameWithOutExt;
    }

//    public String extension(String fileNameWithExt){
//        String extension = FilenameUtils.getExtension(fileNameWithExt);
//        return extension;
//    }

}
