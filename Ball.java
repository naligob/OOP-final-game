/*
 * Classname : Ball
 * @author Ilan Bogomolnik
 */
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * Ball class, each ball has his position, radius ,boarders , color and velocity.
 */
public class Ball implements Sprite , HitNotifier {
    private Point point;
    private int radius;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;
    private List<HitListener> hitListeners;
    private int startLenX;
    private int startLenY;
    private int endLenX;
    private int endLenY;

    /**
     * ball constructor.
     * @param center point of the center
     * @param r balls radius
     * @param color balls color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.point = center;
        this.radius = r;
        this.color = color;
        this.startLenX = 0;
        this.startLenY = 0;
        hitListeners = new LinkedList<>();
    }

    /**
     * builder.
     * @param center point of the center
     * @param r ball's radius
     * @param color ball's color
     * @param listener ball' listener
     */
    public Ball(Point center, int r, java.awt.Color color , HitListener listener) {
        this.point = center;
        this.radius = r;
        this.color = color;
        this.startLenX = 0;
        this.startLenY = 0;
        hitListeners = new LinkedList<>();
        addHitListener(listener);
    }
    /**
     * ball constructor.
     * @param x axis of the center
     * @param y axis of the center
     * @param r balls radius
     * @param color balls color
     */
    public Ball(int x , int y , int r , java.awt.Color color) {
        this.point = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.startLenX = 0;
        this.startLenY = 0;
        hitListeners = new LinkedList<>();
    }
    /**
     * ball constructor.
     * @param x axis of the center
     * @param y axis of the center
     * @param r balls radius
     * @param color balls color
     * @param listener ball listener
     */
    public Ball(int x , int y , int r , java.awt.Color color , HitListener listener) {
        this.point = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.startLenX = 0;
        this.startLenY = 0;
        hitListeners = new LinkedList<>();
        addHitListener(listener);
    }

    /**
     * Getting the x axis of the ball center.
     * @return int x
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * Getting the y axis of the ball center.
     * @return int y
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * Getting the radius of the ball.
     * @return int radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Getting the color of the ball.
     * @return java.awt.Color color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * showing the ball on the surface.
     * @param surface updated
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(getX() , getY() , getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(getX() , getY() , getSize());
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * setting for the ball the game environment.
     * @param gameOfEnvironment all the blocks in the game
     */
    public void setGameEnvironment(GameEnvironment gameOfEnvironment) {
        this.gameEnvironment = gameOfEnvironment;
    }

    /**
     * @return the game environment of the ball
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    /**
     * Setting the velocity of the ball.
     * @param v1 velocity
     */
    public void setVelocity(Velocity v1) {
        this.v = v1;
    }

    /**
     * Setting the velocity of the ball.
     * @param dx rate on x axis
     * @param dy rate on y axis
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Getting the velocity of the ball.
     * @return velocity v
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Making the ball move one step by his velocity.
     */
    public void moveOneStep() {
        Velocity v1;
        Line trajectory = ballTrajectory();
        // getting a collision
        CollisionInfo collision = gameEnvironment.getClosestCollision(trajectory);
        // case no collision occured
        if (collision == null) {
            this.point = v.applyToPoint(this.point);

        } else {         // case collision occured
            Point setNearPoint = collision.collisionPoint();
            Point currentPoint = new Point(collision.collisionPoint());
            // setting the new point near as possible before the collision point
            setNearPoint.setX(setNearPoint.getX() - (v.getDx() / 1000));
            setNearPoint.setY(setNearPoint.getY() - (v.getDy() / 1000));
            v.setVelocity(collision.collisionObject().hit(this , currentPoint , v));
            this.point = setNearPoint;
        }
    }

    /**
     * @return the line of the trajectory (that belong to the ball)
     */
    private Line ballTrajectory() {
        Point end = new Point(point.getX() + v.getDx() , point.getY() + v.getDy());
        return new Line(point , end);
    }

    /**
     * removing the ball from the game.
     * @param game the game environment
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}
