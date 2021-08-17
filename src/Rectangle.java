import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public class Rectangle {
 //Rectangle's fields
 private Point upperLeft;
 private double width;
 private double height;

 /**
  * Instantiates a new Rectangle with location and width/height.
  *
  * @param upperLeft - the upper left vertex of the rectangle
  * @param width - the width of the rectangle
  * @param height - the height of the rectangle
  */
 public Rectangle(Point upperLeft, double width, double height) {
  this.upperLeft = upperLeft;
  this.width = width;
  this.height = height;
 }

 /**
  * @return The method returns the upper right vertex of the rectangle
  */
 public Point upperRight() {
  return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
 }

 /**
  * @return The method returns the down left vertex of the rectangle
  */
 public Point downLeft() {
  return new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
 }

 /**
  * @return The method returns the down right vertex of the rectangle
  */
 public Point downRight() {
  return new Point(upperRight().getX(), downLeft().getY());
 }

 /**
  * The method checks if a point is in the collision point list.
  * @param intersectionPointsList - list of intersection points
  * @param other - a point
  * @return The method returns true if the point is in the list, false otherwise.
  */
 private boolean isPointInList(List<Point> intersectionPointsList, Point other) {
  //Case the list is empty - return null
  if (intersectionPointsList.size() == 0 || other == null) {
   return false;
  }

  //Check if the point is in the list, case it's in the list- return true
  for (int i = 0; i < intersectionPointsList.size(); i++) {
   if (intersectionPointsList.get(i).equals(other)) {
    return true;
   }
  }

  //Otherwise, return false
  return false;
 }

 /**
  * @return The method returns the top edge of the rectangle
  */
 public Line getTopEdge() {
  return new Line(this.upperLeft, upperRight());
 }

 /**
  * @return The method returns the bottom edge of the rectangle
  */
 public Line getBottomEdge() {
  return new Line(downLeft(), downRight());
 }

 /**
  * @return The method returns the left edge of the rectangle
  */
 public Line getLeftEdge() {
  return new Line(downLeft(), this.upperLeft);
 }

 /**
  * @return The method returns the right edge of the rectangle
  */
 public Line getRightEdge() {
  return new Line(downRight(), upperRight());
 }

 /**.
  * The method returns a (possibly empty) list of intersection points with the specified line
  * @param line - a line
  * @return The method returns a list of intersection points with the specified line
  */
 public java.util.List<Point> intersectionPoints(Line line) {
  //Initialize a new intersection points list
  List<Point> intersectionPointsList = new ArrayList<Point>();

  //Calculate the edges of the rectangle
  Line topEdge = new Line(this.upperLeft, upperRight());
  Line bottomEdge = new Line(downLeft(), downRight());
  Line leftEdge = new Line(this.upperLeft, downLeft());
  Line rightEdge = new Line(upperRight(), downRight());

  Point intersectionPointTopEdge = null;
  Point intersectionPointBottomEdge = null;
  Point intersectionPointLeftEdge = null;
  Point intersectionPointRightEdge = null;

  //Calculate the intersection points with each of the edges of the rectangle
  intersectionPointTopEdge = line.intersectionWith(topEdge);
  intersectionPointBottomEdge = line.intersectionWith(bottomEdge);
  intersectionPointLeftEdge = line.intersectionWith(leftEdge);
  intersectionPointRightEdge = line.intersectionWith(rightEdge);

  /* Case there's an intersection point with one of the edges of the rectangle
   * and the point isn't in the list yet - add it to the list
   */
  if ((intersectionPointTopEdge) != null
  && !(isPointInList(intersectionPointsList, intersectionPointTopEdge))) {
   intersectionPointsList.add(intersectionPointTopEdge);
  }

  if ((intersectionPointBottomEdge) != null
  && !(isPointInList(intersectionPointsList, intersectionPointBottomEdge))) {
   intersectionPointsList.add(intersectionPointBottomEdge);
  }

  if ((intersectionPointLeftEdge) != null
  && !(isPointInList(intersectionPointsList, intersectionPointLeftEdge))) {
   intersectionPointsList.add(intersectionPointLeftEdge);
  }

  if ((intersectionPointRightEdge) != null
  && !(isPointInList(intersectionPointsList, intersectionPointRightEdge))) {
   intersectionPointsList.add(intersectionPointRightEdge);
  }

  return intersectionPointsList;
 }

 /**
  * @return The method returns the width of the rectangle
  */
 public double getWidth() {
  return this.width;
 }

 /**
  * @return The method returns the height of the rectangle
  */
 public double getHeight() {
  return this.height;
 }

 /**
  * @return The method returns the upper-left vertex of the rectangle
  */
 public Point getUpperLeft() {
  return this.upperLeft;
 }

 /**.
  * The method returns on which of the edges of the rectangle a point is on
  * @param other - a point
  * @return The method returns the edge that the point is on
  */
 public Line pointOnWhichEdge(Point other) {
  //Case the point is null
  if (other == null) {
   return null;
  }

  //Case the point's values are '.0000000000001' or '0.999999999' - round them
  Point convertedPoint = other.epsilonCheck(other);

  //Calculate the edges of the rectangle
  Line topEdge = new Line(this.upperLeft, upperRight());
  Line bottomEdge = new Line(downLeft(), downRight());
  Line leftEdge = new Line(this.upperLeft, downLeft());
  Line rightEdge = new Line(upperRight(), downRight());

  /* Check if the point is in the range of each of the edges, and return the edge the point is on
   * Case no edge was found, return null
   */
  if ((topEdge.checkXRangeLine1(convertedPoint.getX()))
  && (topEdge.checkYRangeLine1(convertedPoint.getY()))) {
   return topEdge;
  } else if ((bottomEdge.checkXRangeLine1(convertedPoint.getX()))
  && (bottomEdge.checkYRangeLine1(convertedPoint.getY()))) {
   return bottomEdge;
  } else if ((leftEdge.checkXRangeLine1(convertedPoint.getX()))
  && (leftEdge.checkYRangeLine1(convertedPoint.getY()))) {
   return leftEdge;
  } else if ((rightEdge.checkXRangeLine1(convertedPoint.getX()))
  && (rightEdge.checkYRangeLine1(convertedPoint.getY()))) {
   return rightEdge;
  }

  return null;
 }
}
