/*
 * Interface : HitListener
 * @author Ilan Bogomolnik
 */

/**
 * interface implementing the hitListener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *  The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block that been hit
     * @param hitter the ball the hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
