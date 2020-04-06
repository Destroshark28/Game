import java.awt.*;

public class Bullet extends BaseObject {

    private ProcessingClass process;

    public Bullet(int x, int y, ID id, ProcessingClass process, int mouseX, int mouseY) {
        super(x, y, id);

        this.process = process;
        moveX=(mouseX-x)/10;
        moveY=(mouseY-y)/10;

    }//

    @Override
    public void tick() {
        x+=moveX;
        y+=moveY;

        for(int i = 0; i<process.objects.size(); i++){
            BaseObject tempObject = process.objects.get(i);

            if(tempObject.getId()==ID.Box){
                if(getBounds().intersects(tempObject.getBounds())){
                    process.removeObj(this);
                }
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.yellow);
        graphics.fillOval(x,y,6,6);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,6,6);
    }
}
