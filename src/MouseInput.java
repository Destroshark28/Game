import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private ProcessingClass process;
    private Camera camera;
    private Game game;
    public MouseInput(ProcessingClass process, Camera camera, Game game) {
        this.camera = camera;
        this.process=process;
        this.game = game;
    }//

    public void mousePressed(MouseEvent mouseEvent){
        int mouseX=(int) (mouseEvent.getX() + camera.getX());
        int mouseY=(int) (mouseEvent.getY() + camera.getY());

        for(int i = 0; i < process.objects.size(); i++){
            BaseObject tempObject = process.objects.get(i);

            if(tempObject.getId() == ID.Player && game.ammo >= 1){
                process.addObj(new Bullet(tempObject.getX()+16, tempObject.getY()+16, ID.BULLET, process, mouseX, mouseY));
                game.ammo--;
            }
        }
    }
}
