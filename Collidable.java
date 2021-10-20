/*
 * interface : Collidable
 * @author Ilan Bogomolnik
 */

/**
 * interface for the collidable object.
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();
    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * @param hitter the ball that hit
     * @param collisionPoint point of the collision
     * @param currentVelocity the velocity before the hit
     * @return return is the new velocity expected after the hit
     */
    Velocity hit(Ball hitter , Point collisionPoint , Velocity currentVelocity);
}
