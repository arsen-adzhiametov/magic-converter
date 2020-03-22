import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileFinder {

    public void search(File topDirectory, ArrayList<File> res, String extension) throws IOException {
        File[] list = topDirectory.listFiles();
        for (int i = 0; i < list.length; i++) {
            if (list[i].isDirectory()) {
                search(list[i], res, extension);
            } else if (list[i].getName().endsWith(extension)) {
                res.add(list[i]);
            }
        }
    }
}
