/*
 * Classname : Game
 * @author Ilan Bogomolnik
 */

/**
 * this class has the number of the block left in the game and removing a block when a hit occurred.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param gameObsticals all the game information.
     * @param removedBlocks and number of the remaining blocks
     */
    public BlockRemover(GameLevel gameObsticals , Counter removedBlocks) {
        this.game = gameObsticals;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit , Ball hitter) {
            beingHit.removeFromGame(game);
            beingHit.removeHitListener(this);
            remainingBlocks.decrease(1);
    }

    /**
     * @return true when the number of block is not zero otherwise false.
     */
    public Boolean zeroBlocksCheck() {
        return remainingBlocks.getValue() != 0;
    }

    /**
     * updating the information.
     * @param gameObsticals the new game information
     * @param cont the new number of block
     */
    public void updateBlockRemover(GameLevel gameObsticals , Counter cont) {
        this.game = gameObsticals;
        this.remainingBlocks = cont;
    }
}
