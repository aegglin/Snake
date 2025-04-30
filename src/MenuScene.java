import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuScene extends Scene {

    public KeyHandler keyHandler;
    public MouseHandler mouseHandler;
    public BufferedImage title, play, playPressed, exit, exitPressed;
    public GameRectangle playRect, exitRect, titleRect;

    public BufferedImage playCurrentImage, exitCurrentImage;

    public MenuScene(KeyHandler keyHandler, MouseHandler mouseHandler) {
        this.keyHandler = keyHandler;
        this.mouseHandler = mouseHandler;

        try {
            BufferedImage spritesheet = ImageIO.read(new File("assets/menuSprite.png")); //new File starts at the root
            title = spritesheet.getSubimage(0, 242, 960, 240);
            play = spritesheet.getSubimage(0, 121, 261, 121);
            playPressed = spritesheet.getSubimage(264, 121, 261, 121);
            exit = spritesheet.getSubimage(0, 0, 233, 93);
            exitPressed = spritesheet.getSubimage(264, 0, 233, 93);

        } catch (Exception e) {
            e.printStackTrace();
        }

        playCurrentImage = play;
        exitCurrentImage = exit;

        titleRect = new GameRectangle(240, 100, 300, 100);
        playRect = new GameRectangle(310, 280, 150, 70);
        exitRect = new GameRectangle(318, 355, 130, 55);
    }

    @Override
    public void update(double dt) {
        if (mouseHandler.getX() >= playRect.x && mouseHandler.getX() <= playRect.x + playRect.width &&
            mouseHandler.getY() >= playRect.y && mouseHandler.getY() <= playRect.y + playRect.height) {
            playCurrentImage = playPressed;
            if (mouseHandler.isPressed()) {
                Window.changeState(1);
            }
        } else {
            playCurrentImage = play;
        }

        if (mouseHandler.getX() >= exitRect.x && mouseHandler.getX() <= exitRect.x + exitRect.width &&
                mouseHandler.getY() >= exitRect.y && mouseHandler.getY() <= exitRect.y + exitRect.height) {
            exitCurrentImage = exitPressed;
            if (mouseHandler.isPressed()) {

            }
        } else {
            exitCurrentImage = exit;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color (157, 221, 228));
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        g.drawImage(title, (int)titleRect.x, (int)titleRect.y, (int)titleRect.width, (int)titleRect.height, null);
        g.drawImage(playCurrentImage, (int)playRect.x, (int)playRect.y, (int)playRect.width, (int)playRect.height, null);
        g.drawImage(exitCurrentImage, (int)exitRect.x, (int)exitRect.y, (int)exitRect.width, (int)exitRect.height, null);
    }
}
