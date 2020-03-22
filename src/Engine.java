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

        parent.run.setEnabled(false);
        parent.dirChooser.setEnabled(false);
        parent.fieldCMD.setEditable(false);
        parent.fieldDIR.setEditable(false);

        String cmd = parent.fieldCMD.getText().replace("/", "//");
        File topDir = new File(parent.fieldDIR.getText());
        FileFinder fileFinder = new FileFinder();
        try {
            fileFinder.search(topDir, list, extensionToConvert(cmd));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            Thread thread = new Thread(new Process(i, list, cmd));
            thread.setDaemon(true);
            thread.start();
        }
    }

    public static String extensionToConvert(String cmd){
        String extensionToConvert = cmd.substring(cmd.indexOf("~")+1, cmd.indexOf("\"",cmd.indexOf("~")));
        return extensionToConvert;
    }
}
