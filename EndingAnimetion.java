/*
 * Classname : EndingAnimetion
 * @author Ilan Bogomolnik
 */
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * this class making the win/lose ending to the game.
 */
public class EndingAnimetion implements Animation {
    private boolean ballsLeft;
    private Counter score;
    private boolean stop;

    /**
     * builder.
     * @param ab a boolean that asking if there any balls in the game
     * @param s the updated score when the animation started
     */
    public EndingAnimetion(boolean ab , Counter s) {
        this.ballsLeft = ab;
        this.score = s;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        if (ballsLeft) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + score.getValue(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score.getValue(), 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
