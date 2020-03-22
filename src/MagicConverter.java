import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MagicConverter {
    JPanel windowContent;
    JLabel labelCMD;
    static JLabel field1;
    static JLabel field2;
    JTextField fieldCMD;
    JTextField fieldDIR;
    JButton dirChooser;
    JButton run;

    MagicConverter() {
        windowContent = new JPanel();
        GridLayout f1 = new GridLayout(4, 2, 4, 4);
        windowContent.setLayout(f1);
        labelCMD = new JLabel("                                                command");
        fieldCMD = new JTextField(25);
        fieldCMD.setText("\"converter path\" \"~.zip\" \"~.(epub, mobi, pdf)\"");
        // "C:\Program Files (x86)\Calibre2\ebook-convert.exe" "~.zip" "~.epub"

        fieldDIR = new JTextField(25);
        fieldDIR.setText("");
        dirChooser = new JButton("Directory");
        dirChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser dirOpen = new JFileChooser();
                dirOpen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int ret = dirOpen.showSaveDialog(null);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = dirOpen.getSelectedFile();
                    fieldDIR.setText(file.getAbsolutePath());
                }
            }
        });

        run = new JButton("Run");

        windowContent.add(labelCMD);
        windowContent.add(fieldCMD);
        windowContent.add(dirChooser);
        windowContent.add(fieldDIR);
        windowContent.add(run);
        windowContent.add(field1 = new JLabel());
        windowContent.add(field2 = new JLabel());

        JFrame frame = new JFrame("MagicConverter v.2.0");
        frame.setContentPane(windowContent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 150);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        //frame.pack();

        Engine engine = new Engine(this);
        run.addActionListener(engine);
    }

    public static void main(String[] args) {
        MagicConverter magicConverter = new MagicConverter();
    }
}
