/*
 * Classname : Game2
 * @author Ilan Bogomolnik
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this class represent game number two.
 */
public class Game2 implements LevelInformation {
    private static final int BOARDERWIDTH = 30;
    private static final int WIDTH = 800;

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Random randAngle = new Random();
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            if (i % 2 == 0) {
                list.add(Velocity.fromAngleAndSpeed(randAngle.nextInt(60) + 5 , 5));
            } else {
                list.add(Velocity.fromAngleAndSpeed(randAngle.nextInt(60) - 65 , 5));
            }
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 1;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Game2Draw();
    }

    @Override
    public List<Block> blocks() {
        double blockLength = (WIDTH - 2 * BOARDERWIDTH) / 15;
        List<Block> list = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            Block block = new Block(new Rectangle(new Point(BOARDERWIDTH + (i - 1) * blockLength , 250)
                    , blockLength , 30) , getColor(i));
            list.add(block);
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    /**
     * this method gets the color by index.
     * @param i the index
     * @return the color
     */
    private Color getColor(int i) {
        if (i == 1 || i == 2) {
            return Color.RED;
        } else if (i == 3 || i == 4) {
            return Color.ORANGE;
        } else if (i == 5 || i == 6) {
            return Color.YELLOW;
        } else if (i == 7 || i == 8 || i == 9) {
            return Color.GREEN;
        } else if (i == 10 || i == 11) {
            return Color.BLUE;
        } else if (i == 12 || i == 13) {
            return Color.PINK;
        } else {
            return Color.CYAN;
        }
    }
}
