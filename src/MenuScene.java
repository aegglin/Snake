import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class MenuScene extends Scene {

    public KeyHandler keyHandler;

    public MenuScene(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

    @Override
    public void update(double dt) {
        if (keyHandler.isKeyPressed(KeyEvent.VK_UP)) {
            System.out.println("Up aarrow is pressed");
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }
}
