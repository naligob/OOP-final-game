/*
 * Classname : GameFlow
 * @author Ilan Bogomolnik
 */
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * this class runs all the games together.
 */
public class GameFlow {
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private Counter score;

    /**
     * builder.
     * @param ar the platform that the animation run
     * @param ks the key board sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.runner = ar;
        this.keyboard = ks;
        this.score = new Counter(0);
    }

    /**
     * this method run in loop all the levels.
     * @param levels a list of levels
     */
    public void runLevels(List<LevelInformation> levels) {
        int i = 0;
        for (LevelInformation levelInfo : levels) {
            i++;
            GameLevel level = new GameLevel(levelInfo , score , this.keyboard , this.runner);

            level.initialize();

            while (level.getBallsCount() && level.getBlackCount()) {
                level.run();
            }

            if (!level.getBallsCount() || i == levels.size()) {
                level.runEndScreen();
                break;
            }

        }
    }
}
