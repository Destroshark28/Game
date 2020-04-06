import java.awt.*;

public class TheSecondCharacter extends BaseObject {
    ProcessingClass process;
    Game game;
    public TheSecondCharacter(int x, int y, ID id, ProcessingClass process, Game game) {
        super(x, y, id);
        this.process = process;
        this.game = game;
    }

    public boolean place_free(int x, int y, Rectangle myRect, Rectangle otherRect) {
        myRect.x = x;
        myRect.y = y;
        if (myRect.intersects(otherRect)) {
            return false;
        }
        return true;
    }

    @Override
    public void tick() {

        x += moveX;
        y += moveY;
        //движение вверх
        if (process.isUpForTheSecond()) moveY = -10;
        else if (!process.isDownForTheSecond()) moveY = 0;
        //движение вниз
        if (process.isDownForTheSecond()) moveY = 10;
        else if (!process.isUpForTheSecond()) moveY = 0;
        //движение вправо
        if (process.isRightForTheSecond()) moveX = 10;
        else if (!process.isLeftForTheSecond()) moveX = 0;
        //движение влево
        if (process.isLeftForTheSecond()) moveX = -10;
        else if (!process.isRightForTheSecond()) moveX = 0;
        collision();

    }


    //   private void collision(){
    //      for (int i = 0; i< process.objects.size(); i++){
    //        BaseObject tempObject = process.objects.get(i);
    //      if(tempObject.getId() == ID.Box){
    //        if(getBounds().intersects(tempObject.getBounds()))
    //          x-=moveX * (-1);
    //        y-=moveY * (-1);
    // }
    //  }
    //}

    //визуализация игрока
    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.green);
        graphics.fillRect(x, y, 32, 32);
    }

    //границы полегона игрока(для дальнейней обработки столкновений)
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    private void collision() {
        for (int i = 0; i < process.objects.size(); i++) {
            BaseObject tempObject = process.objects.get(i);

            if (tempObject.getId() == ID.Box) {
                if (!place_free((int) (x + moveX), y, getBounds(), tempObject.getBounds())) {
                    moveX = 0;
                }

                if (!place_free(x, (int) (y + moveY), getBounds(), tempObject.getBounds())) {
                    moveY = 0;
                }
            }

            if (tempObject.getId() == ID.AMMOBOX) {
                if (!place_free((int) (x + moveX), y, getBounds(), tempObject.getBounds())) {
                    game.ammo += 15;
                    process.removeObj(tempObject);
                }

                if (!place_free(x, (int) (y + moveY), getBounds(), tempObject.getBounds())) {
                    game.ammo += 15;
                    process.removeObj(tempObject);
                }
            }
            if (tempObject.getId() == ID.Enemy) {
                if (!place_free((int) (x + moveX), y, getBounds(), tempObject.getBounds())) {
                    game.hp--;

                    if(game.hp == 0){
                        GameMessages.showlosemessage(game,"You are loooser!");
                    }
                    // process.removeObj(tempObject);
                }

                if (!place_free(x, (int) (y + moveY), getBounds(), tempObject.getBounds())) {
                    game.hp--;
                    if(game.hp == 0){
                        GameMessages.showlosemessage(game,"You are loooser!");
                    }
                }

            }

            if (tempObject.getId() == ID.HEALTHBOX) {
                if (!place_free((int) (x + moveX), y, getBounds(), tempObject.getBounds())) {
                    game.hp +=15;
                    if (game.hp > 100)
                        game.hp = 100;
                    process.removeObj(tempObject);
                }

                if (!place_free(x, (int) (y + moveY), getBounds(), tempObject.getBounds())) {
                    game.hp +=15;
                    if (game.hp > 100)
                        game.hp = 100;
                    process.removeObj(tempObject);
                }
            }

            if (tempObject.getId() == ID.ENDBLOCK) {
                if (!place_free((int) (x + moveX), y, getBounds(), tempObject.getBounds())) {
                    GameMessages.showlosemessage(game,"You are winner!");
                    process.removeObj(tempObject);

                }

                if (!place_free(x, (int) (y + moveY), getBounds(), tempObject.getBounds())) {
                    GameMessages.showlosemessage(game,"You are winner!");
                    process.removeObj(tempObject);


                }
            }


        }
    }
}
