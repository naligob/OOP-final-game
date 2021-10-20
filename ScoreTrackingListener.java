/*
 * Classname : ScoreTrackingListener
 * @author Ilan Bogomolnik
 */

/**
 * this class updating the score when hit occurred.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * builder.
     * @param score the starting score.
     */
    public ScoreTrackingListener(Counter score) {
        this.currentScore = score;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
