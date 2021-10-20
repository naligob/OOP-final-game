/*
 * Classname : Paddle
 * @author Ilan Bogomolnik
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * this class making a paddle in a shape of rectangle.
 */
public class Paddle implements Sprite , Collidable {
    // constant
    private int paddleSpeed;
    // fields
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private java.awt.Color color;
    // paddle behavior in the game
    private int boarderRight;
    private int boarderLeft;
    private double areaSize;

    /**
     * builder.
     * @param rectangle the paddle shape in rectangle size
     * @param kbs for the moving the paddle
     * @param boarderRightXIndex the boarder for the paddle
     * @param boarderLeftXIndex the boarder for the paddle
     * @param color the color of the paddle
     * @param speed the speed of the paddle
     */
    public Paddle(Rectangle rectangle , KeyboardSensor kbs , int boarderRightXIndex , int boarderLeftXIndex
            , java.awt.Color color , int speed) {
        this.rectangle = rectangle;
        keyboard = kbs;
        this.boarderRight = boarderRightXIndex;
        this.boarderLeft = boarderLeftXIndex;
        this.areaSize = rectangle.getWidth() / 5;
        this.color = color;
        this.paddleSpeed = speed;
    }

    /**
     * this method makes the paddle move one step to the left.
     */
    public void moveLeft() {
        rectangle.setUpperLeft(new Point(rectangle.getUpperLeft().getX() - paddleSpeed
                , rectangle.getUpperLeft().getY()));
        // case when the paddle reach the boarder
        if (rectangle.getUpperLeft().getX() <= boarderLeft) {
            rectangle.setUpperLeft(new Point(boarderLeft , rectangle.getUpperLeft().getY()));
        }
    }

    /**
     * this method makes the paddle move one step to the right.
     */
    public void moveRight() {
        rectangle.setUpperLeft(new Point(rectangle.getUpperLeft().getX() + paddleSpeed
                , rectangle.getUpperLeft().getY()));
        // case when the paddle reach the boarder
        if (rectangle.getUpperRight().getX() >= boarderRight) {
            double distance = rectangle.getUpperRight().getX() - rectangle.getUpperLeft().getX();
            rectangle.setUpperLeft(new Point(boarderRight - distance , rectangle.getUpperLeft().getY()));
        }
    }

    // Sprite

    /**
     * this method check if any command ordered for the paddle.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * printing on surface command, the shape of the paddle.
     * @param d the surface we print on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(getColor());
        d.fillRectangle((int) rectangle.getUpperLeft().getX() , (int) rectangle.getUpperLeft().getY()
                , (int) rectangle.getWidth() , (int) rectangle.getHeight());
    }

    /**
     * adding the paddle to the game list of elements.
     * @param g game environment
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    // Collidable

    /**
     * the shape of the paddle.
     * @return rectangle shape
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    @Override
    /**
     * this method find where the collision occur on the shape and switch the velocity as ordered.
     */
    public Velocity hit(Ball hitter , Point collisionPoint, Velocity currentVelocity) {
        if (hitArea1(collisionPoint)) { // case area 1 occur
            return Velocity.fromAngleAndSpeed(300 , currentVelocity.getSpeed());
        } else if (hitArea2(collisionPoint)) { // case area 2 occur
            return Velocity.fromAngleAndSpeed(330 , currentVelocity.getSpeed());
        } else if (hitArea4(collisionPoint)) { // case area 4 occur
            return Velocity.fromAngleAndSpeed(60 , currentVelocity.getSpeed());
        } else if (hitArea5(collisionPoint)) { // case area 5 occur
            return Velocity.fromAngleAndSpeed(30 , currentVelocity.getSpeed());
        }
        // case area 3 occur (in the middle of the paddle no special change)
        Velocity temp = null;
        if (verticalPoint(collisionPoint)) {
            temp = new Velocity(currentVelocity.getDx()  , currentVelocity.getDy() * -1);
        }
        if (horizontalPoint(collisionPoint)) {
            temp = new Velocity(currentVelocity.getDx() * -1 , currentVelocity.getDy());
        }
        return temp;
    }

    /**
     * checking if the point is on the vertical lines of the paddle.
     * @param collisionPoint the point of the collision
     * @return true/false
     */
    private boolean verticalPoint(Point collisionPoint) {
        if (rectangle.getUpperLeft().getY() == collisionPoint.getY()
                || rectangle.getLowwerRight().getY() == collisionPoint.getY()) {
            return true;
        }
        return false;
    }

    /**
     * checking if the point is on the horizontal lines of the paddle.
     * @param collisionPoint the point of the collision
     * @return true/false
     */
    private boolean horizontalPoint(Point collisionPoint) {
        if (rectangle.getUpperLeft().getX() == collisionPoint.getX()
                || rectangle.getUpperRight().getX() == collisionPoint.getX()) {
            return true;
        }
        return false;
    }

    /**
     * checking if the ball hits the first area of the paddle.
     * @param collisionPoint the point of the collision
     * @return true/false
     */
    private boolean hitArea1(Point collisionPoint) {
        double fifthLeftXIndex = rectangle.getUpperRight().getX();
        if (collisionPoint.getX() <= fifthLeftXIndex && collisionPoint.getX() > fifthLeftXIndex - areaSize) {
            return true;
        }
        return false;
    }

    /**
     * checking if the ball hits the second area of the paddle.
     * @param collisionPoint the point of the collision
     * @return true/false
     */
    private boolean hitArea2(Point collisionPoint) {
        double fourthLeftXIndex = rectangle.getUpperRight().getX() - areaSize;
        if (collisionPoint.getX() <= fourthLeftXIndex && collisionPoint.getX() > fourthLeftXIndex - areaSize) {
            return true;
        }
        return false;
    }

    /**
     * checking if the ball hits the fourth area of the paddle.
     * @param collisionPoint the point of the collision
     * @return true/false
     */
    private boolean hitArea4(Point collisionPoint) {
        double secondLeftXIndex = rectangle.getUpperLeft().getX() + areaSize;
        if (collisionPoint.getX() >= secondLeftXIndex && collisionPoint.getX() < secondLeftXIndex + areaSize) {
            return true;
        }
        return false;
    }

    /**
     * checking if the ball hits the fifth area of the paddle.
     * @param collisionPoint the point of the collision
     * @return true/false
     */
    private boolean hitArea5(Point collisionPoint) {
        double mostLeftXIndex = rectangle.getUpperLeft().getX();
        if (collisionPoint.getX() >= mostLeftXIndex && collisionPoint.getX() < mostLeftXIndex + areaSize) {
            return true;
        }
        return false;
    }

    /**
     * getter for the color of the paddle.
     * @return the color
     */
    public Color getColor() {
        return color;
    }
}
