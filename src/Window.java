import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable{

    public static Window window = null;

    public boolean isRunning;
    public int currentState;
    public Scene currentScene;
    public KeyHandler keyHandler = new KeyHandler();
    public MouseHandler mouseHandler = new MouseHandler();

    public Window(int width, int height, String title) {
        setSize(width, height);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        isRunning = true;

        changeState(0);
        addKeyListener(keyHandler);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public static Window getWindow() {
        if (Window.window == null) {
            Window.window = new Window(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.SCREEN_TITLE);
        }
        return Window.window;
    }

    public void changeState(int newState) {
        currentState = newState;
        switch (currentState) {
            case 0:
                currentScene = new MenuScene(keyHandler, mouseHandler);
                break;
            case 1:
                currentScene = new GameScene(keyHandler);
                break;
            default:
                System.out.println("Unknown scene.");
                currentScene = null;
                break;
        }
    }

    public void close() {
        isRunning = false;

    }

    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        getGraphics().drawImage(dbImage, 0, 0, this);

        currentScene.update(dt);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        currentScene.draw(g);
    }

    @Override
    public void run() {
        double lastFrameTime = 0.0;
        try {
            while (isRunning) {
                double time = Time.getTime();
                double deltaTime = time - lastFrameTime;
                lastFrameTime = time;
                update(deltaTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.dispose();
    }
}
