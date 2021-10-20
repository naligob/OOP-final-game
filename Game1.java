/*
 * Classname : Game1
 * @author Ilan Bogomolnik
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class represent game number one.
 */
public class Game1 implements LevelInformation {
    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(0 , 3));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Game1Draw();
    }

    @Override
    public List<Block> blocks() {
        Block block = new Block(new Rectangle(new Point(WIDTH / 2 - 20 , HEIGHT / 2 - 170) , 40 , 40)
                , Color.RED);
        List<Block> list = new ArrayList<>();
        list.add(block);
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
