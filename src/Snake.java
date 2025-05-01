import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Snake {
    public GameRectangle[] body = new GameRectangle[100];
    public double bodyWidth, bodyHeight;

    public int size;
    public int tail = 0;
    public int head;

    public Direction direction = Direction.RIGHT;

    public double origWaitBetweenUpdates = 0.3f;
    public double waitTimeLeft = origWaitBetweenUpdates;

    public Snake(int size, double startX, double startY, double bodyWidth, double bodyHeight) {
        this.size = size;
        this.bodyWidth = bodyWidth;
        this.bodyHeight = bodyHeight;

        for (int i = 0; i <= size; i++) {
            GameRectangle bodyPiece = new GameRectangle(startX + i * bodyWidth, startY, bodyWidth, bodyHeight);
            body[i] = bodyPiece;
            head++;
        }
        head--;
    }

    public void changeDirection(Direction newDirection) {

        // Snake can't move back on itself
        if (!(newDirection == Direction.RIGHT && direction == Direction.LEFT) &&
                !(newDirection == Direction.LEFT && direction == Direction.RIGHT) &&
                !(newDirection == Direction.UP && direction == Direction.DOWN) &&
                !(newDirection == Direction.DOWN && direction == Direction.UP)) {
            direction = newDirection;
        }
    }

    public void update(double dt) {
        if (waitTimeLeft > 0) {
            waitTimeLeft -= dt;
        } else {
            waitTimeLeft = origWaitBetweenUpdates;
            double newX = 0;
            double newY = 0;

            if (direction == Direction.RIGHT) {
                newX = body[head].x + bodyWidth;
                newY = body[head].y;
            } else if (direction == Direction.LEFT) {
                newX = body[head].x - bodyWidth;
                newY = body[head].y;
            } else if (direction == Direction.UP) {
                newX = body[head].x;
                newY = body[head].y - bodyHeight;
            } else if (direction == Direction.DOWN) {
                newX = body[head].x;
                newY = body[head].y + bodyHeight;
            }

            body[(head + 1) % body.length] = body[tail];
            body[tail] = null;
            head = (head + 1) % body.length;
            tail = (tail + 1) % body.length;

            body[head].x = newX;
            body[head].y = newY;
        }
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
            g2d.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 4.0 + subHeight, subWidth, subHeight));
        }
    }
}
