import java.awt.*;

import javax.swing.*;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    public static void createGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());


        JButton centerButton = new JButton("Select level");
        panel.add(centerButton, BorderLayout.CENTER);
        centerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(frame,"Levels",true);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setLocationRelativeTo(null);
                dialog.setSize(500, 400);
                dialog.setLayout(new GridLayout(5,1));
                JButton one = new JButton("First");
                dialog.add(one);
                one.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        frame.setVisible(false);
                        new Game("/level_1.png");
                    }
                });
                JButton two = new JButton("Second");
                dialog.add(two);
                two.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        frame.setVisible(true);
                        //  new Game("/level_1.png");
                    }
                });
                JButton three = new JButton("Third");
                dialog.add( three);
                three.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        frame.setVisible(true);
                        // new Game();
                    }
                });
                JButton four = new JButton("Fourth");
                dialog.add(four);
                four.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        frame.setVisible(true);
                        // new Game();
                    }
                });
                JButton five = new JButton("Fifth");
                dialog.add(five);
                five.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        frame.setVisible(true);
                        // new Game();
                    }
                });
                dialog.setVisible(true);
            }
        });
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(500, 400));

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
