import java.awt.*;

public class HealthBox extends BaseObject{
    public HealthBox(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(x,y,32,32);
    }

    @Override//
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
}
