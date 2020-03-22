import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MagicConverter {
    JPanel windowContent;
    JTextField fieldA;
    JTextField fieldB;
    JTextField fieldC;
    static JTextField field1;
    static JTextField field2;

    JButton fileChooser1;
    JButton fileChooser2;
    JButton fileChooser3;
    JButton run;
    JProgressBar progressBar;

    MagicConverter() {
        windowContent = new JPanel();
        GridLayout f1 = new GridLayout(8,2,10,5);
        windowContent.setLayout(f1);
        fieldA = new JTextField(15);
        fieldA.setText("C:\\Program Files\\7-Zip\\7z.exe");
        fieldB = new JTextField(15);
        fieldB.setText("C:\\Program Files (x86)\\Calibre2\\ebook-convert.exe");
        fieldC = new JTextField(15);
        fieldC.setText("");
        run = new JButton("Run");
        fileChooser1 = new JButton("7z.exe");
        fileChooser1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              fileOpening(fieldA);
            }
        });
        fileChooser2 = new JButton("ebook-convert.exe");
        fileChooser2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             fileOpening(fieldB);
            }
        });
        fileChooser3 = new JButton("Directory");
        fileChooser3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileOpen = new JFileChooser();
                int ret = fileOpen.showSaveDialog(null);
                if (ret == JFileChooser.DIRECTORIES_ONLY) {
                    File file = fileOpen.getCurrentDirectory();
                    fieldC.setText(file.getAbsolutePath());
                }
            }
        });
        progressBar = new JProgressBar();

        windowContent.add(fileChooser1);
        windowContent.add(fieldA);
        windowContent.add(fileChooser2);
        windowContent.add(fieldB);
        windowContent.add(fileChooser3);
        windowContent.add(fieldC);
        windowContent.add(run);
        windowContent.add(progressBar);
        windowContent.add(field1=new JTextField(20));
        windowContent.add(field2=new JTextField(20));

        JFrame frame = new JFrame("MagicConverter v.1.0");
        frame.setContentPane(windowContent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(800,300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();

        Engine engine = new Engine(this);
        run.addActionListener(engine);
    }

    public static void  fileOpening(JTextField field){
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            field.setText(file.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        MagicConverter magicConverter = new MagicConverter();
    }
}
