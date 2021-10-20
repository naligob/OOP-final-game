/*
 * Classname : Game3Draw
 * @author Ilan Bogomolnik
 */
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * this class drawing the third level.
 */
public class Game3Draw implements Sprite {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.getHSBColor(0.3f , 1f , 0.5f));
        d.fillRectangle(0 , 0 , WIDTH , HEIGHT);
        d.setColor(Color.WHITE);
        d.fillRectangle(70 , 450 , 110 , 200);
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(110 , 400 , 30 , 50);
        d.fillRectangle(120 , 220 , 10 , 200);
        d.setColor(Color.BLACK);
        d.fillRectangle(70 , 450 , 110 , 10);
        d.fillRectangle(70 , 450 , 10 , 150);
        d.fillRectangle(170 , 450 , 10 , 150);
        for (int i = 1; i <= 4; i++) {
            d.fillRectangle(75 + 19 * i , 450 , 7 , 150);
            d.fillRectangle(70 , 450 + 30 * i , 110 , 7);
        }
        d.setColor(Color.ORANGE);
        d.fillCircle(125 , 210 , 11);
        d.setColor(Color.RED);
        d.fillCircle(125 , 210 , 7);
        d.setColor(Color.WHITE);
        d.fillCircle(125 , 210 , 3);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
