/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public class Point {
    //Point's fields
    private double x;
    private double y;

    /**
     * Instantiates a new Point.
     *
     * @param x - x value of the point
     * @param y - y value of the points
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**.
     * The method calculates the distance between 2 points
     *
     * @param other - other represents another Point
     * @return The method returns the distance of this point to the other point
     */
    public double distance(Point other) {
        //Calculate both x and y ranges
        double xRange = this.x - other.getX();
        double yRange = this.y - other.getY();

        //calculate and return the distance
        return Math.sqrt(Math.pow(xRange, 2) + Math.pow(yRange, 2));
    }

    /**.
     * The method checks if two points are equal
     *
     * @param other - other represents another Point
     * @return The method returns true is two points are equal, false otherwise
     */
    public boolean equals(Point other) {
        //Case not a valid point
        if (other == null) {
            return false;
        }

        //Case x or y values are different
        if ((this.x != other.getX()) || (this.y != other.getY())) {
            return false;
        }

        return true;
    }

    /**
     * @return The method returns the x value of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return The method returns the y value of the point
     */
    public double getY() {
        return this.y;
    }
    /**.
     * The method changes the x and y values of the point
     *
     * @param xVal - new x value of the point
     * @param yVal - new y value of the point
     */
    public void changePointValues(double xVal, double yVal) {
        this.x = xVal;
        this.y = yVal;
    }

    /**.
     * The method checks if two points are 'epsilon range' close
     *
     * @param other - other point
     * @return The method returns the rounded point
     */
    public Point epsilonCheck(Point other) {
     double epsilon = Math.pow(10, -12);

     Point newPoint = new Point(other.getX(), other.getY());

     //Check if the x values needs to be rounded up or down
     double xIntedVerUp = (int) other.getX() + 1;
     double xIntedVerDown = (int) other.getX();
     if (xIntedVerUp - newPoint.getX() < epsilon) {
      newPoint.changePointValues(xIntedVerUp, newPoint.getY());
     } else if (newPoint.getX() - xIntedVerDown < epsilon) {
      newPoint.changePointValues(xIntedVerDown, newPoint.getY());
     }

     //Check if the y values needs to be rounded up or down
     double yIntedVerUp = (int) other.getY() + 1;
     double yIntedVerDown = (int) other.getY();
     if (yIntedVerUp - newPoint.getY() < epsilon) {
      newPoint.changePointValues(newPoint.getX(), yIntedVerUp);
     } else if (newPoint.getY() - yIntedVerDown < epsilon) {
      newPoint.changePointValues(newPoint.getX(), yIntedVerDown);
     }

     return newPoint;
    }
}
