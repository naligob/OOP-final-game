/*
 * Classname : Game4Draw
 * @author Ilan Bogomolnik
 */

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * this class drawing the fourth level.
 */
public class Game4Draw implements Sprite {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.getHSBColor(0.59f , 1f , 1f));
        d.fillRectangle(0 , 0 , WIDTH , HEIGHT);
        d.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            d.drawLine(125 + i * 10 , 400 , 105 + i * 10 , 600);
            d.drawLine(610 + i * 10 , 500 , 580 + i * 10 , 600);
        }
        d.setColor(Color.getHSBColor(0.5f , 0.1f , 0.7f));
        d.fillCircle(145 , 420 , 30);
        d.fillCircle(125 , 395 , 25);
        d.fillCircle(610 , 480 , 20);
        d.fillCircle(630 , 510 , 30);
        d.setColor(Color.getHSBColor(0.5f , 0.1f , 0.6f));
        d.fillCircle(160 , 385 , 30);
        d.fillCircle(650 , 490 , 30);
        d.setColor(Color.getHSBColor(0.5f , 0.1f , 0.5f));
        d.fillCircle(200 , 400 , 35);
        d.fillCircle(175 , 420 , 25);
        d.fillCircle(690 , 500 , 35);
        d.fillCircle(665 , 520 , 20);


    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
