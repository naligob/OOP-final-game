/*
 * interface : LevelInformation
 * @author Ilan Bogomolnik
 */

import java.util.List;

/**
 * this interface represent the information about each game.
 */
public interface LevelInformation {
    /**
     * @return number of balls on the game.
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return paddle speed in the game.
     */
    int paddleSpeed();

    /**
      * @return paddle length in the game.
     */
    int paddleWidth();

    /**
      * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
      * @return Returns a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level.
     */
    List<Block> blocks();

    /**
      * @return Number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}
