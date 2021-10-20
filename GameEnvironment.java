/*
 * Classname : GameEnvironment
 * @author Ilan Bogomolnik
 */
import java.util.ArrayList;
import java.util.List;

/**
 * this class keeps all the obstacle that are on the board.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * builder.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    // add the given collidable to the environment.

    /**
     * adding a collidable to the list in the class.
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    /**
     * this method returns the information about the collision.
     * @param trajectory the route of the ball
     * @return the information otherwise null
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
       Collidable tempCollidable = null;
       Point p;
       Point temp = null;
       boolean first = true;
        for (Collidable c : collidables) {
            p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (first && p != null) {
                temp = p;
                first = false;
                tempCollidable = c;
            }
            if (!first && p != null && p.distance(trajectory.start()) < temp.distance(trajectory.start())) {
                temp = p;
                tempCollidable = c;
            }
        }
        if  (temp != null) {
            return new CollisionInfo(temp , tempCollidable);
        } else {
            return null;
        }
    }

    /**
     * @param c the collidable we want to remove from the collidable list.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }
}
