/*
 * Classname : Line
 * @author Ilan Bogomolnik
 */

/**
 * this class represent a line that organized with two points.
 */
public class Line {
    private Point start;
    private Point end;

    // constructors

    /**
     * constructor for Line class.
     * @param start start point
     * @param end end point
     */
    public  Line(Point start , Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor for Line class.
     * @param x1 x axis of the starting point
     * @param y1 y axis of the starting point
     * @param x2 x axis of the ending point
     * @param y2 y axis of the ending point
     */
    public  Line(double x1 , double y1 , double x2 , double y2) {
        Point startPoint = new Point(x1 , y1);
        Point endPoint = new Point(x2 , y2);
        this.start = startPoint;
        this.end = endPoint;
    }

    /**
     * Getting the length of a line.
     * @return the length
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Getting the middle point of the line.
     * @return Point
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2.0;
        double y = (this.start.getY() + this.end.getY()) / 2.0;
        Point middlePoint = new Point(x , y);
        return middlePoint;
    }

    /**
     * Getting the point where the line begining.
     * @return point
     */
    public Point start() {
        return this.start;
    }

    /**
     * Getting the point where the line ending.
     * @return point
     */
    public Point end() {
        return this.end;
    }

    /**
     * checking if the lines have intersection.
     * @param line the check line
     * @return true/false
     */
    public boolean isIntersecting(Line line) {
        Line l1 = new Line(this.start , this.end);
        if (l1.equals(line)) {
            return false;
        } else if (l1.tendency() == line.tendency()) {
            return false;
        } else if (checkIfBetween(l1.getPoint(line) , l1 , line) == null) {
            return false;
        }
        return true;
    }

    /**
     * Getting the tendency of the line.
     * @return double tendency
     */
    private Double tendency() {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        if ((x2 - x1) == 0) {
            return null;
        }
        return ((y2 - y1) / (x2 - x1));
    }

    /**
     * Getting the point of intersection.
     * @param line the check line
     * @return point
     */
    public Point intersectionWith(Line line) {
        Line l1 = new Line(this.start , this.end);
        if (l1.isIntersecting(line)) {
            return checkIfBetween(l1.getPoint(line) , l1 , line);
        }
        return null;
    }

    /**
     * Getting the free variable from the straight line equation.
     * @param line the checked line
     * @return double N
     */
    //from y-y1 = m(x-x1) -->y=mx (-x1*m+y1) --> N=-x1*m+y1
    private double getN(Line line) {
        return (-1 * line.start.getX() * line.tendency() + line.start.getY());
    }

    /**
     * Getting the intersection point.
     * @param line the checked line
     * @return point of the intersection
     */
    //from m1*x+n1=m2*x+n2 --> x=(n2-n1)/(m1-m2) --> y=m1*x+n1
    private Point getPoint(Line line) {
        double x, y;
        Line l1 = new Line(this.start , this.end);
        if (l1.tendency() == null && line.tendency() != null) {   // special case
            x = l1.start.getX();
            y = line.tendency() * x + getN(line);
            return new Point(x , y);
        } else if (l1.tendency() != null && line.tendency() == null) {    // special case
            x = line.start.getX();
            y = l1.tendency() * x + getN(l1);
            return new Point(x, y);
        } else if (l1.tendency() == null && line.tendency() == null && l1.equals(line)) {   // special case
            x = l1.start.getX();
            y = l1.start.getY();
            return new Point(x , y);
        } else if ((l1.tendency() != null && line.tendency() != null)) {
            x = ((getN(l1) - getN(line)) / (line.tendency() - l1.tendency()));
            y = (line.tendency() * x + getN(line));
            return new Point(x , y);
        } else {
            return null;
        }
    }

    /**
     * checking if the x is between x1 and x2.
     * @param x1 axis boarder
     * @param x2 axis boarder
     * @param x the checked point in axis
     * @return true/false
     */
    private boolean checkPoints(double x1 , double x2 , double x)  {
        if (Double.compare(x1 , x2) >= 0 && (x1 >= x && x >= x2)) {
            return true;
        } else { return Double.compare(x1, x2) < 0 && (x1 <= x && x <= x2); }
    }

    /**
     * Checking if the point is in the boarders of the line.
     * @param point the checked point
     * @param line1 the first line
     * @param line2 the second line
     * @return the checked point if everything is legal otherwise null
     */
    private Point checkIfBetween(Point point , Line line1 , Line line2) {
        double x1, x2, x3, x4, y1, y2, y3, y4;
        x1 = line1.start.getX();
        x2 = line1.end.getX();
        x3 = line2.start.getX();
        x4 = line2.end.getX();
        y1 = line1.start.getY();
        y2 = line1.end.getY();
        y3 = line2.start.getY();
        y4 = line2.end.getY();
        if (checkPoints(x1 , x2 , point.getX()) && checkPoints(x3 , x4 , point.getX())
        && checkPoints(y1 , y2 , point.getY()) && checkPoints(y3 , y4 , point.getY())) {
            return point;
        } else {
            return null;
        }
    }

    /**
     * checking if the same line.
     * @param line the checked line
     * @return true/false
     */
    public boolean equals(Line line) {
        return (this.start.equals(line.start()) && this.end.equals(line.end()));
    }

    /**
     * finding the point that both intersacting the rectangle and closest to the start of the line.
     * @param rect the shape of the rectangle
     * @return point if any found otherwise null
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> listPoints;
        listPoints = rect.intersectionPoints(new Line(start , end));
        if (listPoints.size() == 0) {
            return null;
        } else {
            Point nearestPoint = new Point(listPoints.get(0).getX() , listPoints.get(0).getY());
            double distence = start.distance(nearestPoint);
            for (int i = 1; i < listPoints.size(); i++) {
                if (listPoints.get(i).distance(start) < distence) {
                    nearestPoint = listPoints.get(i);
                    distence = listPoints.get(i).distance(start);
                }
            }
            return nearestPoint;
        }
    }
}
