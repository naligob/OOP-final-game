/*
 * Classname : Rectangle
 * @author Ilan Bogomolnik
 */
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * a class that represent a block with the shape of rectangle.
 */
public class Block implements Collidable , Sprite , HitNotifier {
    // fields
    private Rectangle rectangle;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    /**
     * builder.
     * @param rectangle the shape of the block
     * @param color the color of the block
     * @param hitListenerList a listener to remove the block
     * @param scoreListener a listener to update the score
     */
    public Block(Rectangle rectangle , java.awt.Color color , HitListener hitListenerList , HitListener scoreListener) {
        this.rectangle = rectangle;
        this.color = color;
        hitListeners = new LinkedList<>();
        hitListeners.add(scoreListener);
        hitListeners.add(hitListenerList);
    }
    /**
     * builder.
     * @param rectangle the shape of the block
     * @param color the color of the block
     * @param hitListenerList a listener to remove the block
     */
    public Block(Rectangle rectangle , java.awt.Color color , HitListener hitListenerList) {
        this.rectangle = rectangle;
        this.color = color;
        hitListeners = new LinkedList<>();
        hitListeners.add(hitListenerList);
    }
    /**
     * builder.
     * @param rectangle the shape of the block
     * @param color the color of the block
     */
    public Block(Rectangle rectangle , java.awt.Color color) {
        this.rectangle = rectangle;
        this.color = color;
        hitListeners = new LinkedList<>();
    }

    /**
     * make all the listeners to work when hit occur.
     * @param hitter the hitting ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        if (!hitListeners.isEmpty()) {
            List<HitListener> listeners = new ArrayList<>(this.hitListeners);
            // Notify all listeners about a hit event:
            for (HitListener hl : listeners) {
                hl.hitEvent(this , hitter);
            }
        }
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter , Point collisionPoint, Velocity currentVelocity) {
        Velocity temp = null;
        if (verticalPoint(collisionPoint)) {
            temp = new Velocity(currentVelocity.getDx()  , currentVelocity.getDy() * -1);
        }
        if (horizontalPoint(collisionPoint)) {
            temp = new Velocity(currentVelocity.getDx() * -1 , currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return temp;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * checking if the point is on the vertical lines of the block.
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
     * checking if the point is on the horizontal lines of the block.
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
     * @return the color of the block
     */
    public Color getColor() {
        return color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillRectangle((int) rectangle.getUpperLeft().getX() , (int) rectangle.getUpperLeft().getY()
                , (int) rectangle.getWidth() , (int) rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) rectangle.getUpperLeft().getX() , (int) rectangle.getUpperLeft().getY()
                , (int) rectangle.getWidth() , (int) rectangle.getHeight());
    }

    @Override
    public void timePassed() {

    }

    /**
     * removing the block from the game environment.
     * @param game the environment
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
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
