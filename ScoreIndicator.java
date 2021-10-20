/*
 * Classname : ScoreIndicator
 * @author Ilan Bogomolnik
 */

import biuoop.DrawSurface;

/**
 * this class tracking the score and showing it.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * builder.
     * @param currentScore the current score
     */
    public ScoreIndicator(Counter currentScore) {
        this.score = currentScore;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(300 , 25 , "Score: " + score.getValue() , 25);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
