public class Camera {
    private float x,y;

    public Camera(float x, float y){
        this.x=x;
        this.y=y;
    }//

    public void tick(BaseObject tempObject){
        //доделать карту еще нужно, после этого настроить камеру

        x += (tempObject.getX() - x - 1000/2)*0.05f;
        y += (tempObject.getY() - y - 1000/2)*0.05f;
        if(x <= 0 ) x = 0;
        if (y>=5032) y=5032;
        if(y <= 0 ) y = 0;
        if (x>=7000) x=7000;


    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
