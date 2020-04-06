import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {

    private BufferedImage level = null;
    private boolean isRunning = false;
    private Thread thread; //определяем поток
    private ProcessingClass process;
    private Camera camera;
    private MainWindow mainwindow;
    //private TheMainCharacter theMainCharacter;
    public int ammo = 100;
    public int hp = 100;

    //public String levelpath="/level_1.png";
    public Game(String path) {
        mainwindow = new MainWindow(1000, 1000, "SUPER GAME YEAH", this);
        start();

        process = new ProcessingClass();
        camera = new Camera(0, 0);
        this.addKeyListener(new KeyInput(process));
        this.addMouseListener(new MouseInput(process, camera, this));
        BufferedImageLoader bufferedImageLoader = new BufferedImageLoader();
        level = bufferedImageLoader.loadImage(path);
        loadLevel(level);
        // process.addObj(new TheMainCharacter(32, 32, ID.Player, process));
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);//создаем новый поток
        thread.start();//начало потока
    }

    public void stop() {
        isRunning = false;
        try {
            thread.join();//приостановка потока
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void end() {
        mainwindow.terminate();

    }

    public void run() {
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0; //кол-во кадров в секунду
        double ns = 1000000000 / amountOfTicks;
        double delta = 0; //время между текущим моментом и прошлым обновлением кадра
        long timer = System.currentTimeMillis();

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) { //если разница между текущим моментов и моментом прошлого обновления кадра больше единицы
                tick(); //то мы вызывает следующее обновление кадра
                delta--; //и "удаляем" эту единицу
            }
            render(); //обновляем картинку

            //  if(System.currentTimeMillis() - timer > 1000){
            //      timer += 1000;
            //   }
        }
        stop();//приостанавливаем поток
    }

    public void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            this.createBufferStrategy(3); // при работе одного кадра, следующие кадры (2) уже загружены для дальшейшей работы
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();
        //Определяет текущее графическое состояние, а также методы графического вывода
        Graphics2D graphics2D = (Graphics2D) graphics;
        ////////////////////////////////////
        graphics.setColor(Color.gray);//установить цвет покраски
        graphics.fillRect(0, 0, 3000, 1500); //закрасить участок окна 1500х1500
        //перемещения исходной точки общего пути в точку (getX,getY). Все операции рисования используют
        // позиционирование относительно точки (getX,getY) вместо точки (0,0).
        graphics2D.translate(-camera.getX(), -camera.getY());
        process.render(graphics);//циклическое обновление графики
        graphics2D.translate(camera.getX(), camera.getY());

        graphics.setColor(Color.green);
        graphics.fillRect(5, 5, hp, 32);
        // graphics.setColor(Color.orange);
        // graphics.fillRect(5,5,hp*2,32);
        graphics.setColor(Color.black);
        graphics.drawRect(5, 5, 100, 32);
        graphics.setColor(Color.white);
        // graphics.drawString("HP: " + hp, 5, 50);
        graphics.drawString("Ammo: " + ammo, 5, 50);

        graphics.dispose();
        bufferStrategy.show();//вывод
    }

    //обновление состояния
    public void tick() {
        //постоянное обновление состояния камеры
        for (int i = 0; i < process.objects.size(); i++) {
            if (process.objects.get(i).getId() == ID.Player)
                camera.tick(process.objects.get(i));
        }
        process.tick();
    }

    //загрузка уровня
    private void loadLevel(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = image.getRGB(x, y);

                /*в
                int (который, как известно 4 байта) упакованы компоненты цвета в следующем порядке:
                биты 31-24 - Alpha - альфа-каналs
                биты 23-16 - Red - красный канал
                биты 15-8 - Green - зеленый канал
                биты 7-0 - Blue - синий канал
                Разбить int на байты не составит труда: *///
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255)
                    process.addObj(new Block(x * 32, y * 32, ID.Box));

                if (green == 75 && blue == 25 && red == 75)
                    process.addObj(new TheSecondCharacter(x * 32, y * 32, ID.Player, process, this));

                if (blue == 255 && green == 0)
                    process.addObj(new TheMainCharacter(x * 32, y * 32, ID.Player, process, this));

                if (green == 255 && blue == 0)
                    process.addObj(new Enemy(x * 32, y * 32, ID.Enemy, process));

                if (green == 255 && blue == 255)
                    process.addObj(new AmmoBox(x * 32, y * 32, ID.AMMOBOX));

                if (green == 100 && blue == 100 && red == 100)
                    process.addObj(new EndBlock(x * 32, y * 32, ID.ENDBLOCK));

                if (green == 50 && blue == 50 && red == 50)
                    process.addObj(new HealthBox(x * 32, y * 32, ID.HEALTHBOX));

            }
        }
    }

    public static void main(String args[]) {
        Menu.createGUI();
    }
}
