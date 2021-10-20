/*
 * Classname : Velocity
 * @author Ilan Bogomolnik
 */

/**
 * Each velocity parameter have his dx dy and angle.
 */
public class Velocity {

    private double dx;
    private double dy;
    private double angle;

    /**
     * This method creating velocity by setting the advance in the X & Y axis.
     * @param dx the advance in the X axis
     * @param dy the advance in the Y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This method creating velocity by setting angle and speed to advance in the X & Y axis.
     * @param angle the direction of the movement
     * @param speed the speed of the movment
     * @return a new velocity by dx & dy
     */
    public static  Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = -1 * (Math.sin((angle * Math.PI) / 180) * speed);
        double dy = -1 * (Math.cos((angle * Math.PI) / 180) * speed);
        return new Velocity(dx, dy);
    }

    /**
     * Setting the velocity.
     * @param v the new velocity
     */
    public void setVelocity(Velocity v) {
        this.dx = v.dx;
        this.dy = v.dy;
    }

    /**
     * Get the angle.
     * @return the current angle
     */
    public double getAngle() {
        this.angle = (Math.atan2(this.dy , this.dx) * (180 / Math.PI));
        return this.angle;
    }

    /**
     * the oposite operation to the calculate at fromAngleAndSpeed method.
     * @return the speed value
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Setting the velocity by dx & dy.
     * @param dX the new dx
     * @param dY the new dy
     */
    public void setVelocity(double dX , double dY) {
        this.dx = dX;
        this.dy = dY;
    }

    /**
     * Getting the dx in the velocity.
     * @return current dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Getting the dy in the velocity.
     * @return current dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Getting the velocity.
     * @return the current velocity
     */
    public Velocity getVelocity() {
        return new Velocity(this.dx , this.dy);
    }

    /**
     * Making new point by using the last point and the velocity.
     * @param p the current point
     * @return the new point after applying the velocity
     */
    public Point applyToPoint(Point p) {
        return new Point((this.dx + p.getX()) , (this.dy + p.getY()));
    }
}
