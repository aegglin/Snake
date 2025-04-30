import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GameScene extends Scene {

    GameRectangle background, foreground;

    public GameScene() {
        background = new GameRectangle(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        foreground = new GameRectangle(24, 48, 24 * 31, 24 * 22);
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fill(new Rectangle2D.Double(background.x, background.y, background.width, background.height));

        g2d.setColor(Color.WHITE);
        g2d.fill(new Rectangle2D.Double(foreground.x, foreground.y, foreground.width, foreground.height));
    }
}
