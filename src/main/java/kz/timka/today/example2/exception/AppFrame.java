package kz.timka.today.example2.exception;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AppFrame extends JFrame {

    public AppFrame() throws HeadlessException {
        setBounds(200, 200, 500, 500);
        JButton button = new JButton("send");
        button.addActionListener((e) -> {
            ReportMaker reportMaker = new ReportMaker();
            try {
                reportMaker.createReport("1.tx", "ewwefwef");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "ERORR");
            }
        });
        setTitle("title");
        add(button);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    public static void main(String[] args) {
        new AppFrame();
    }
}
