import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Process implements Runnable {

    ArrayList<File> list;
    File unzipper;
    File converter;
    int processorNumber;
    int size;

    Process(int processorNumber, ArrayList<File> list, File unzipper, File converter) {
        this.list = list;
        this.unzipper = unzipper;
        this.converter = converter;
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
                unzipAndConvert(file, unzipper, converter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void unzipAndConvert(File zip, File unzip, File convert) throws InterruptedException, IOException {
        if (new File(zip.toString().substring(0, zip.toString().length() - 4)).exists()) {
            new File(zip.toString().substring(0, zip.toString().length() - 4)).delete();
        }
        String cmd = getFormattedPath(unzip) + " e -o" + getFormattedPath(zip.getParentFile()) + " " + getFormattedPath(zip);
        Runtime r = Runtime.getRuntime();
        java.lang.Process p = r.exec(cmd);
        p.waitFor();
        File fb2 = new File(zip.toString().substring(0, zip.toString().length() - 4));
        String cmd2 = getFormattedPath(convert) + " \"" + zip.toString().substring(0, zip.toString().length() - 4) + "\" \""
                + zip.toString().substring(0, zip.toString().length() - 8) + ".epub" + "\"";
        p = r.exec(cmd2);
        p.waitFor();
        fb2.delete();
        zip.delete();
        MagicConverter.field1.setText(fb2.getName());
        MagicConverter.field2.setText(String.valueOf(100 * (size - list.size()) / size) + "%");
        //System.out.println("CPU_" + processorNumber + "  " + zip.toString().substring(0, zip.toString().length() - 8) + ".epub");
    }

    private String getFormattedPath(File file) {
        return "\"" + file.getPath() + "\"";
    }
    //Сделать прогу для конвертации всего вообще. Паттерн такой - берется вся команда пишется в строке, вместо имен файлов
    //пишется

}
