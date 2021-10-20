/*
 * Classname : Game2Draw
 * @author Ilan Bogomolnik
 */

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * this class drawing the second level.
 */
public class Game2Draw implements Sprite {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0 , 0 , WIDTH , HEIGHT);
        d.setColor(Color.getHSBColor(0.15f , 0.4f , 1f));
        for (int i = 0; i < 6 * 15; i++) {
            d.drawLine(150 , 150 , i * 8 , 250);
        }
        d.setColor(Color.getHSBColor(0.15f , 0.4f , 1f));
        d.fillCircle(150 , 150 , 50);
        d.setColor(Color.getHSBColor(0.15f , 0.7f , 1f));
        d.fillCircle(150 , 150 , 40);
        d.setColor(Color.getHSBColor(0.15f , 1.0f , 1f));
        d.fillCircle(150 , 150 , 30);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
