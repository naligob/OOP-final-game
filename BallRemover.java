/*
 * Classname : BallRemover
 * @author Ilan Bogomolnik
 */

/**
 * this class in charge of removing balls from the game.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * builder.
     * @param game the game level
     * @param removedBalls the number of balls to that need to be removed
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        hitter.removeHitListener(this);
        remainingBalls.decrease(1);
    }

    /**
     * @return true if more that zero otherwise false.
     */
    public Boolean zeroBallsCheck() {
        return remainingBalls.getValue() != 0;
    }

    /**
     * updating the GameLevel and the remaining balls.
     * @param gameObsticals the new game level
     * @param cont the number of balls
     */
    public void updateBallRemover(GameLevel gameObsticals , Counter cont) {
        this.game = gameObsticals;
        this.remainingBalls = cont;
    }
}
