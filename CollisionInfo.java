/*
 * Classname : CollisionInfo
 * @author Ilan Bogomolnik
 */

/**
 * the class saves the information about the collision.
 */
public class CollisionInfo {
    private Point point;
    private Collidable collidableObject;

    /**
     * builder.
     * @param point the point of the collision
     * @param collidableObject the object that the collision occur
     */
    public CollisionInfo(Point point , Collidable collidableObject) {
        this.collidableObject = collidableObject;
        this.point = point;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.point;
    }


    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidableObject;
    }
}
