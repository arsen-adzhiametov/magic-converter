import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileFinder {

    public void search(File topDirectory, ArrayList<File> res) throws IOException {
        File[] list = topDirectory.listFiles();
        for (int i = 0; i < list.length; i++) {
            if (list[i].isDirectory()) {
                search(list[i], res);
            } else if (list[i].getName().contains(".zip")) {
                res.add(list[i]);
            }
        }
    }
}
