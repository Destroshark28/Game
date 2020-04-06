import java.awt.*;
import java.util.Random;

public class Enemy extends BaseObject {

    private ProcessingClass process;
    Random random = new Random();
    int choose = 0;
    int hp = 100;


    public Enemy(int x, int y, ID id, ProcessingClass process) {
        super(x, y, id);
        this.process=process;
    }

    @Override
    public void tick() {
        x+=moveX;
        y+=moveY;

        choose = random.nextInt(9);

        for(int i = 0; i< process.objects.size(); i++){
            BaseObject tempObject = process.objects.get(i);

            if(tempObject.getId() == ID.Box){
                if(getBoundsBig().intersects(tempObject.getBounds())) {
                    x += (moveX * 5) * -1;
                    y += (moveY * 5) * -1;
                    moveX *= -1;
                    moveY *= -1;
                } else if (choose == 0){
                    moveY = (random.nextInt(4 - -4) + -4);
                    moveX = (random.nextInt(4 - -4) + -4);
                }
            }

            if (tempObject.getId() == ID.BULLET){
                if(getBounds().intersects(tempObject.getBounds())){
                    hp -= 50;
                    process.removeObj(tempObject);
                }
            }
        }

        if (hp <= 0)
            process.removeObj(this);//
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.magenta);
        graphics.fillOval(x,y,32,32);

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics.setColor(Color.green);
        graphics2D.draw(getBoundsBig());

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32,32);
    }

    public Rectangle getBoundsBig() {
        return new Rectangle(x - 16, y - 16, 64,64);
    }
}
