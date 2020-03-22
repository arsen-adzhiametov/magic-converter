import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Engine implements ActionListener {

    ArrayList<File> list = new ArrayList<File>();

    MagicConverter parent;

    Engine(MagicConverter parent) {
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        File unzipper = new File(parent.fieldA.getText());
        File converter = new File(parent.fieldB.getText());
        File topDir = new File(parent.fieldC.getText());
        FileFinder fileFinder = new FileFinder();
        try {
            fileFinder.search(topDir, list);
            parent.progressBar.setIndeterminate(true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            new Thread(new Process(i, list, unzipper, converter)).start();
        }
    }
}
