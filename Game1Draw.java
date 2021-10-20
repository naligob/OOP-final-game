/*
 * Classname : Game1Draw
 * @author Ilan Bogomolnik
 */
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * this class drawing the first level.
 */
public class Game1Draw implements Sprite {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0 , 0 , WIDTH , HEIGHT);
        d.setColor(Color.BLUE);
        d.drawLine(d.getWidth() / 2 - 150 , d.getHeight() / 2 - 150  , d.getWidth() / 2 + 150
                , d.getHeight() / 2 - 150);
        d.drawLine(d.getWidth() / 2 , d.getHeight() / 2 - 300 , d.getWidth() / 2
                , d.getHeight() / 2);
        d.drawCircle(d.getWidth() / 2 , d.getHeight() / 2 - 150 , 75);
        d.drawCircle(d.getWidth() / 2 , d.getHeight() / 2 - 150 , 100);
        d.drawCircle(d.getWidth() / 2 , d.getHeight() / 2 - 150 , 125);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
