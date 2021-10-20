/*
 * Classname : GameLevel
 * @author Ilan Bogomolnik
 */
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
/**
 * this class stands for a game. the initialize and run.
 */
public class GameLevel implements Animation {
    // fields
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.Sleeper sleeper;
    private BlockRemover blockListener;
    private BallRemover ballListener;
    private ScoreTrackingListener scoreListener;
    private Counter score;
    private LevelInformation info;
    // game constants
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BOARDERWIDTH = 30;
    private static final int BOARDERHEIGHT = 30;
    private static final int RADIUS = 5;
    private static final int PEDDALHEIGHT = 20;
    /**
     * builder.
     * @param information the info of the game
     * @param gs the score that the game begin
     * @param ks key board sensor
     * @param run the animation that run
     */
    public GameLevel(LevelInformation information , Counter gs , KeyboardSensor ks , AnimationRunner run) {
        this.info = information;
        this.score = gs;
        this.keyboard = ks;
        this.runner = run;
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
    }

    /**
     * adding a collidable to the game memory.
     * @param c collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /**
     * adding a sprite to the game memory.
     * @param s sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * this method creating a paddle.
     */
    private void creatPaddleToGame() {
        int middle = (WIDTH / 2) - (info.paddleWidth() / 2);
        Rectangle rectangle0 = new Rectangle(new Point(middle , HEIGHT  - BOARDERHEIGHT)
                , info.paddleWidth() , PEDDALHEIGHT);
        Paddle paddle = new Paddle(rectangle0 , this.keyboard , WIDTH - BOARDERWIDTH
                , BOARDERWIDTH , Color.YELLOW , this.info.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * this method creating balls.
     */
    private void creatBallsToGame() {
        Point middleOfPaddle = new Point(WIDTH / 2 , HEIGHT - 2 * PEDDALHEIGHT - RADIUS);
        for (int i = 0; i < this.info.numberOfBalls(); i++) {
            Ball ball = new Ball(middleOfPaddle , RADIUS , Color.WHITE , this.ballListener);
            ball.setVelocity(this.info.initialBallVelocities().get(i));
            ball.addToGame(this);
            // the ball receives the information about the game
            ball.setGameEnvironment(environment);
        }
    }

    /**
     * this method creating blocks.
     */
    private void creatBlocksToGame() {
        for (Block block : this.info.blocks()) {
            block.addHitListener(this.blockListener);
            block.addHitListener(this.scoreListener);
            block.addToGame(this);
        }
        creatFrameToGame();
    }

    /**
     * creating the block that are the frame of the game and also the block that removes the balls from the game.
     */
    private void creatFrameToGame() {
        Rectangle block0 = new Rectangle(new Point(0 , 0) , WIDTH , BOARDERHEIGHT);
        Block scorePannle = new Block(block0 , Color.LIGHT_GRAY);
        scorePannle.addToGame(this);
        // boarders rectangles
        Rectangle rectangle = new Rectangle(new Point(30 , 30) , WIDTH , BOARDERHEIGHT);
        Block block1 = new Block(rectangle , Color.GRAY);
        block1.addToGame(this);
        Rectangle rectangle2 = new Rectangle(new Point(0 , 30) , BOARDERWIDTH , HEIGHT);
        Block block2 = new Block(rectangle2 , java.awt.Color.GRAY);
        block2.addToGame(this);
        Rectangle rectangle3 = new Rectangle(new Point(WIDTH - BOARDERWIDTH , 30) , BOARDERWIDTH , HEIGHT);
        Block block3 = new Block(rectangle3 , java.awt.Color.GRAY);
        block3.addToGame(this);
        Rectangle rectangle4 = new Rectangle(new Point(0 , HEIGHT - BOARDERHEIGHT + 30) , WIDTH , BOARDERHEIGHT);
        Block deathBlock = new Block(rectangle4 , Color.BLUE , ballListener);
        deathBlock.addToGame(this);
    }

    /**
     * this method creating the base for each game.
     */
    public void initialize() {
        blockListener = new BlockRemover(this , new Counter(0));
        ballListener = new BallRemover(this , new Counter(0));
        scoreListener = new ScoreTrackingListener(score);
        this.sprites.addSprite(info.getBackground());
        creatBallsToGame();
        creatPaddleToGame();
        creatBlocksToGame();
        ScoreIndicator currentScore = new ScoreIndicator(score);
        currentScore.addToGame(this);
        blockListener.updateBlockRemover(this , new Counter(info.numberOfBlocksToRemove()));
        ballListener.updateBallRemover(this , new Counter(info.numberOfBalls()));
        this.sprites.addSprite(new NameIndicator(info.levelName()));
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2 , 3 , sprites)); // countdown before turn starts.
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }
    /**
     * @param c the collidable we want to remove from the collidable list.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * @param s the sprite we want to remove from the sprite list.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (!blockListener.zeroBlocksCheck()) {
            score.increase(100);
            this.running = false;
        } else if (!blockListener.zeroBlocksCheck()) {
            this.running = false;
        } else if (!ballListener.zeroBallsCheck()) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            Animation pause = new PauseScreen();
            this.runner.run(new KeyPressStoppableAnimation(keyboard , "space" , pause));
        }
    }

    /**
     * getter for the balls in the game.
     * @return the number of balls
     */
    public boolean getBallsCount() {
        return ballListener.zeroBallsCheck();
    }

    /**
     * getter for the blocks in the game.
     * @return the number of blocks
     */
    public boolean getBlackCount() {
        return blockListener.zeroBlocksCheck();
    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * this method running the end screen after each game.
     */
    public void runEndScreen() {
        Animation ending = new EndingAnimetion(getBallsCount() , score);
        if (getBallsCount()) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard , "space" , ending));
        } else {
            this.runner.run(new KeyPressStoppableAnimation(keyboard , "space" , ending));
        }

    }
}
