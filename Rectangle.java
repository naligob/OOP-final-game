/*
 * Classname : Rectangle
 * @author Ilan Bogomolnik
 */
import java.util.LinkedList;

/**
 * this class represent a rectangle structure by knowing one of the point, the width and height.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * builder.
     * @param upperLeft the point
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft , double width , double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
    }

    /**
     * checking the line and rectengle for intersecting.
     * @param line the line that migth be intersecting with the rectangle
     * @return list of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> listPoints = new LinkedList<>();
        // creating all the lines of the rectangle
        Line line1 = new Line(getUpperLeft() , getUpperRight());
        Line line2 = new Line(getUpperLeft() , getLowwerLeft());
        Line line3 = new Line(getLowwerLeft() , getLowwerRight());
        Line line4 = new Line(getUpperRight() , getLowwerRight());
        // checking each line
        if (line.isIntersecting(line1)) {
            listPoints.add(line.intersectionWith(line1));
        }
        if (line.isIntersecting(line2)) {
            listPoints.add(line.intersectionWith(line2));
        }
        if (line.isIntersecting(line3))  {
            listPoints.add(line.intersectionWith(line3));
        }
        if (line.isIntersecting(line4)) {
            listPoints.add(line.intersectionWith(line4));
        }
        // checking if there the same points
        return checkPoints(listPoints);
    }

    /**
     * checking if there is the same point in the array.
     * @param listPoints array of unchecked points
     * @return the checked points
     */
    private java.util.List<Point> checkPoints(java.util.List<Point> listPoints) {
        int start = 0;
        int checker = start + 1;
        // for each index in the array we check for similarity in all the next indexes
        while (start < listPoints.size()) {
            // case we found similarity
            if (checker < listPoints.size() && listPoints.get(start).equals(listPoints.get(checker))) {
                listPoints.remove(checker);
                start++;
                checker = start + 1;
            // case no similarity found
            } else if (checker > listPoints.size()) {
                start++;
                checker = start + 1;
            // case this index is not similar
            } else {
                checker++;
            }
        }
        return listPoints;
    }
    /**
     * getting the width of the rectangle.
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * getting the height of the rectangle.
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * getting the upper left point of the rectangle.
     * @return the point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * getting the lowwer rigth point of the rectangle.
     * @return the point
     */
    public Point getLowwerRight() {
        return new Point(getUpperLeft().getX() + getWidth() , getUpperLeft().getY() + getHeight());
    }
    /**
     * getting the lowwer left point of the rectangle.
     * @return the point
     */
    public Point getLowwerLeft() {
        return new Point(getUpperLeft().getX() , getUpperLeft().getY() + getHeight());
    }
    /**
     * getting the upper right point of the rectangle.
     * @return the point
     */
    public Point getUpperRight() {
        return new Point(getUpperLeft().getX() + getWidth() , getUpperLeft().getY());
    }

    /**
     * setting the upper point.
     * @param upperToLeft the new point
     */
    public void setUpperLeft(Point upperToLeft) {
        this.upperLeft = upperToLeft;
    }
}
