import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GameScene extends Scene {

    public GameRectangle background, foreground;
    public Snake snake;
    public KeyHandler keyHandler;

    public GameScene(KeyHandler keyHandler) {
        background = new GameRectangle(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        foreground = new GameRectangle(24, 48, 24 * 31, 24 * 22);
        snake = new Snake(10, 48, 48+24, 24, 24);

        this.keyHandler = keyHandler;
    }

    @Override
    public void update(double dt) {

        if (keyHandler.isKeyPressed(KeyEvent.VK_UP)) {
            snake.changeDirection(Direction.UP);
        } else if (keyHandler.isKeyPressed(KeyEvent.VK_DOWN)) {
            snake.changeDirection(Direction.DOWN);
        } else if (keyHandler.isKeyPressed(KeyEvent.VK_RIGHT)) {
            snake.changeDirection(Direction.RIGHT);
        } else if (keyHandler.isKeyPressed(KeyEvent.VK_LEFT)) {
            snake.changeDirection(Direction.LEFT);
        }

        snake.update(dt);
    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fill(new Rectangle2D.Double(background.x, background.y, background.width, background.height));

        g2d.setColor(Color.WHITE);
        g2d.fill(new Rectangle2D.Double(foreground.x, foreground.y, foreground.width, foreground.height));

        snake.draw(g2d);
    }
}
