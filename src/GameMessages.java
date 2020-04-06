import java.awt.*;

import javax.swing.*;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameMessages {
    public static void showlosemessage(Game game,String message) {
        game.end();
        JFrame frame = new JFrame(message);
        // Не закрывать окно по нажатию на кнопку с крестиком
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300, 200));
        frame.setLayout(new GridLayout(2,1));
        JLabel label = new JLabel(String.format(message));
        frame.add(label);
        frame.getContentPane().add(label);
        frame.pack();//
        frame.pack();////
        JButton two = new JButton("Back to menu");
        frame.add(two);
        two.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frame.setVisible(false);
                Menu.createGUI();
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                Menu.createGUI();
                frame.setVisible(false);
            }
        });
    }

}
