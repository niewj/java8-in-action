package concurrent.coreTech.thread1_1;

import javax.swing.*;
import java.awt.*;

/**
 * 弹跳APP
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new BounceFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}


