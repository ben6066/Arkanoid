// Velocity specifies the change in position on the `x` and the `y` axes.

/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public class Velocity {
    //Velocity's fields
    private double dx;
    private double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx - Ball's speed in a axis
     * @param dy - Ball's speed in y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**.
     * The method generates a Velocity from angle and speed
     *
     * @param angle - Speed's angle
     * @param speed - Ball's speed
     * @return The method returns a Velocity calculated from angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //Calculate the speed in x axis and the speed in y axis
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**.
     * The method changes a Point's values
     *
     * @param p - a Point
     * @return The method returns a new Point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * @return The method returns the speed in x axis
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return The method returns the speed in y axis
     */
    public double getDy() {
        return this.dy;
    }

    /**.
     * The method calculates the speed from a given Velocity
     *
     * @param v - velocity
     * @return The method returns the speed calculated
     */
    public static double calculateSpeed(Velocity v) {
        //Calculate the speed
        return Math.sqrt(Math.pow(v.getDx(), 2) + Math.pow(v.getDy(), 2));
    }
}
