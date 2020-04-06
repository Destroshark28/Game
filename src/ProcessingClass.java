import java.awt.*;
import java.util.LinkedList;

//класс для хранения и работы с остальными классами
public class ProcessingClass {
    /*В LinkedList хранит объекты базового класса, для работы с объектами типа ENEMY, BOX, HERO и т.д. */
    LinkedList<BaseObject> objects = new LinkedList<BaseObject>();

    private boolean up = false, down = false, right = false, left = false;
    private boolean upForTheSecond = false, downForTheSecond = false, rightForTheSecond = false, leftForTheSecond = false;
    //мы получаем объект(это может быть Игрок, может быть ящик и тд) и постоянно обновляем его
    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            BaseObject tempObject = objects.get(i);
            tempObject.tick();
        }
    }//

    //тут мы постоянно обновляем его графику
    public void render(Graphics graphics) {
        for (int i = 0; i < objects.size(); i++) {
            BaseObject tempObject = objects.get(i);
            tempObject.render(graphics);
        }
    }

    //добавление объектов в контейнер
    public void addObj(BaseObject tempObject) {
        objects.add(tempObject);
    }

    //и удаление объектов из контейнера
    public void removeObj(BaseObject tempObject) {
        objects.remove(tempObject);
    }

    //ниже идут геттеры и сеттеры для нашего класса
    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUpForTheSecond() {
        return upForTheSecond;
    }

    public void setUpForTheSecond(boolean upForTheSecond) {
        this.upForTheSecond = upForTheSecond;
    }

    public boolean isDownForTheSecond() {
        return downForTheSecond;
    }

    public void setDownForTheSecond(boolean downForTheSecond) {
        this.downForTheSecond = downForTheSecond;
    }

    public boolean isRightForTheSecond() {
        return rightForTheSecond;
    }

    public void setRightForTheSecond(boolean rightForTheSecond) {
        this.rightForTheSecond = rightForTheSecond;
    }

    public boolean isLeftForTheSecond() {
        return leftForTheSecond;
    }

    public void setLeftForTheSecond(boolean leftForTheSecond) {
        this.leftForTheSecond = leftForTheSecond;
    }
}
