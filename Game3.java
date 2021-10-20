/*
 * Classname : Game3
 * @author Ilan Bogomolnik
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this class represent game number three.
 */
public class Game3 implements LevelInformation {
    private static final int BOARDERWIDTH = 30;
    private static final int WIDTH = 800;
    private static final int BLOCKWIDTH = 45;
    private static final int BLOCKHEIGHT = 25;

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Random randAngle = new Random();
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            if (i % 2 == 0) {
                list.add(Velocity.fromAngleAndSpeed(randAngle.nextInt(60) + 5 , 4));
            } else {
                list.add(Velocity.fromAngleAndSpeed(randAngle.nextInt(60) - 65 , 4));
            }
        }
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Game3Draw();
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        Color color;
        for (int j = 0; j < 5; j++) {
            color = setColor(j);         // each row different color
            for (int i = 0; i < 10 - j; i++) {
                // making new rectangle and each i change the horizontal layout and the j the vertical layout
                Rectangle rectangleLoop = new Rectangle(new Point(
                        (WIDTH - BLOCKWIDTH - BOARDERWIDTH) - i * BLOCKWIDTH
                        , 150 + j * BLOCKHEIGHT), BLOCKWIDTH, BLOCKHEIGHT);
                Block block = new Block(rectangleLoop , color);
                list.add(block);
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

    /**
     * this method gets the color by index.
     * @param i the index
     * @return the color
     */
    private Color setColor(int i) {
        if (i == 0) {
            return Color.GRAY;
        } else if (i == 1) {
            return Color.RED;
        } else if (i == 2) {
            return Color.YELLOW;
        } else if (i == 3) {
            return Color.BLUE;
        } else {
            return Color.WHITE;
        }
    }
}
