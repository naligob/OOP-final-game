/*
 * Classname : Game4
 * @author Ilan Bogomolnik
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this class represent game number four.
 */
public class Game4 implements LevelInformation {
    private static final double BOARDERWIDTH = 30;
    private static final double BOARDERHIGHT = 20;
    private static final double WIDTH = 800;
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Random randAngle = new Random();
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            if (i % 2 == 0) {
                list.add(Velocity.fromAngleAndSpeed(randAngle.nextInt(60) + 5 , 6));
            } else {
                list.add(Velocity.fromAngleAndSpeed(randAngle.nextInt(60) - 65 , 6));
            }
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Game4Draw();
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        double blockLength = (WIDTH - 2 * BOARDERWIDTH) / 15;
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 15; j++) {
                Block block = new Block(
                        new Rectangle(
                                new Point(BOARDERWIDTH + (j - 1) *  blockLength , 250 - i * BOARDERHIGHT)
                                , blockLength , BOARDERHIGHT) , getColor(i));
                list.add(block);
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
    /**
     * this method gets the color by index.
     * @param i the index
     * @return the color
     */
    private Color getColor(int i) {
        if (i == 1) {
            return Color.CYAN;
        } else if (i == 2) {
            return Color.PINK;
        } else if (i == 3) {
            return Color.WHITE;
        } else if (i == 4) {
            return Color.GREEN;
        } else if (i == 5) {
            return Color.YELLOW;
        } else if (i == 6) {
            return Color.RED;
        } else {
            return Color.GRAY;
        }
    }
}
