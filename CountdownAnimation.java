/*
 * Classname : CountdownAnimation
 * @author Ilan Bogomolnik
 */


import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * this class in charge of the animation of the count down before a game.
 */
public class CountdownAnimation implements Animation {
    private double numberOfSeconds;
    private int countBegin;
    private SpriteCollection allObjects;
    private boolean stop;
    private int tempContBegin;
    // game Constants
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;


    /**
     * builder.
     * @param numOfSeconds number of second the animation run
     * @param countFrom where the countdown begin
     * @param gameScreen all the background elements
     */
    public CountdownAnimation(double numOfSeconds , int countFrom , SpriteCollection gameScreen) {
        this.numberOfSeconds = numOfSeconds;
        this.countBegin = countFrom;
        this.allObjects = gameScreen;
        this.stop = false;
        this.tempContBegin = countFrom;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.countBegin == -1) {
            this.stop = true;
            return;
        }
        Sleeper newSleaper = new biuoop.Sleeper();
        this.allObjects.drawAllOn(d);
        double sleepFor = (numberOfSeconds * 1000) / this.tempContBegin;
        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 2 , d.getHeight() / 2 , countBegin + "...", 56);
        newSleaper.sleepFor((long) sleepFor);
        this.countBegin--;
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
