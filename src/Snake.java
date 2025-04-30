import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Snake {
    public GameRectangle[] body = new GameRectangle[100];
    public double bodyWidth, bodyHeight;

    public int size;
    public int tail = 0;
    public int head;

    public Snake(int size, double startX, double startY, double bodyWidth, double bodyHeight) {
        this.size = size;
        this.bodyWidth = bodyWidth;
        this.bodyHeight = bodyHeight;

        for (int i = 0; i < size; i++) {
            GameRectangle bodyPiece = new GameRectangle(bodyWidth, bodyHeight, startX + i * bodyWidth, startY);
            body[i] = bodyPiece;
            head++;
        }
        head--;
    }



    public void draw(Graphics2D g2d) {
        for (int i = tail; i != head; i = (i + 1) % body.length) {
            GameRectangle piece = body[i];
            double subWidth = (piece.width - 6.0) / 2.0;
            double subHeight = (piece.height - 6.0) / 2.0;

            g2d.setColor(Color.BLACK);
            g2d.fill(new Rectangle2D.Double(piece.x + 2.0, piece.y + 2.0, subWidth, subHeight));
            g2d.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 2.0, subWidth, subHeight));
            g2d.fill(new Rectangle2D.Double(piece.x + 2.0, piece.y + 4.0 + subHeight, subWidth, subHeight));
            g2d.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 4.0 * subHeight, subWidth, subHeight));
        }
    }
}
