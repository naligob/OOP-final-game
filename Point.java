/*
 * Classname : AveragePrimes
 * @author Ilan Bogomolnik
 */
/**
 * Each Point parameter have his x & y axis.
 */
public class Point {
    // Fields
    private double x;
    private double y;
    // constructor

    /**
     * A constructor for Point.
     * @param x x axis
     * @param y y axis
     */
    public Point(double x , double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * A constructor for Point.
     * @param point include both x and y axis
     */
    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }
    // Access methods

    /**
     * Getting the x axis at a Point.
     * @return x axis
     */
    public double getX() {
        return this.x;
    }

    /**
     * Getting the y axis at a Point.
     * @return y axis
     */
    public double getY() {
        return this.y;
    }

    /**
     * updating the x axis at a Point.
     * @param x1 the new x axis
     */
    public void setX(double x1) {
        this.x = x1;
    }

    /**
     * updating the y axis at a Point.
     * @param y1 the new y axis
     */
    public void setY(double y1) {
        this.y = y1;
    }

    /**
     * Checking the distance between two points.
     * @param point the checker point
     * @return the distance
     */
    public double distance(Point point) {
        double x1 = x;
        double y1 = y;
        double x2 = point.getX();
        double y2 = point.getY();
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    /**
     * Checking if the points are the same.
     * @param point the checker point
     * @return true/flase
     */
    public boolean equals(Point point) {
        return (this.x == point.getX() && this.y == point.getY());
    }

}
