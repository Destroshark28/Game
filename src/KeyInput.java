import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    ProcessingClass process;

    public KeyInput(ProcessingClass process) {
        this.process = process;
    }

    public void keyPressed(KeyEvent event) {
    int keyForTheSecond = event.getKeyCode();
        int key = event.getKeyCode();
     for (int i = 0; i < process.objects.size(); i++) {
        BaseObject tempObject = process.objects.get(i);
        //если объект в контейнере есть пользователь, то он может перемещаться
        if (tempObject.getId() == ID.Player) {
            if (keyForTheSecond == KeyEvent.VK_UP)
                process.setUpForTheSecond(true);
            if (keyForTheSecond == KeyEvent.VK_DOWN)
                process.setDownForTheSecond(true);
            if (keyForTheSecond == KeyEvent.VK_LEFT)
                process.setLeftForTheSecond(true);
            if (keyForTheSecond == KeyEvent.VK_RIGHT)
                process.setRightForTheSecond(true);
        }
         if (tempObject.getId() == ID.Player) {
             if (key == KeyEvent.VK_W)
                 process.setUp(true);
             if (key == KeyEvent.VK_S)
                 process.setDown(true);
             if (key == KeyEvent.VK_A)
                 process.setLeft(true);
             if (key == KeyEvent.VK_D)
                 process.setRight(true);
         }
    }
}

    public void keyReleased(KeyEvent event) {
        int keyForTheSecond = event.getKeyCode();
        int key = event.getKeyCode();
        for (int i = 0; i < process.objects.size(); i++) {
            BaseObject tempObject = process.objects.get(i);

            //Константы типа VK обозначают виртуальные коды кавиш, не зависящие от
            // таких модифицирующих клавиш, как <Control>, <Alt> или <Shift>.
            if (tempObject.getId() == ID.Player) {
                if (keyForTheSecond ==KeyEvent.VK_UP)
                    process.setUpForTheSecond(false);
                if (keyForTheSecond == KeyEvent.VK_DOWN)
                    process.setDownForTheSecond(false);
                if (keyForTheSecond == KeyEvent.VK_LEFT)
                    process.setLeftForTheSecond(false);
                if (keyForTheSecond == KeyEvent.VK_RIGHT)
                    process.setRightForTheSecond(false);
            }
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W)
                    process.setUp(false);
                if (key == KeyEvent.VK_S)
                    process.setDown(false);
                if (key == KeyEvent.VK_A)
                    process.setLeft(false);
                if (key == KeyEvent.VK_D)
                    process.setRight(false);
            }
        }
    }

}
