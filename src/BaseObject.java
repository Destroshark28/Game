import java.awt.*;

public abstract class BaseObject {
    protected int x, y;
    protected int moveX, moveY;
    protected ID id;

    public BaseObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }//

    public abstract void tick();

    public abstract void render(Graphics graphics);

    public abstract Rectangle getBounds();

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getMoveX() {
        return moveX;
    }

    public void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    public int getMoveY() {
        return moveY;
    }

    public void setMoveY(int moveY) {
        this.moveY = moveY;
    }
}
