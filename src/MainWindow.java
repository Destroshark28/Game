import java.awt.Dimension;
import javax.swing.JFrame;

public class MainWindow {
    public JFrame frame;
    public MainWindow(int width, int heigth, String title, Game game) {
       frame = new JFrame(title);
//
        frame.setPreferredSize((new Dimension(width, heigth))); //изначальный размер
        frame.setMaximumSize(new Dimension(width, heigth)); //максимальный размер окна
        frame.setMinimumSize(new Dimension(width, heigth));//минимальный размер окна

        frame.add(game);
        frame.setResizable(false); //сделать размер статическим
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //
        frame.setLocationRelativeTo(null); //появление окна в центре экрана
        frame.setVisible(true);
    }

    public void terminate() {
        frame.setVisible(false);
    }
}
