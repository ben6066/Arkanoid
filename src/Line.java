import java.util.List;

/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public class Line {

 //Line's fields
 private Point start;
 private Point end;

 /**
  * Instantiates a new Point.
  *
  * @param start - the start point of the line
  * @param end - the end point of the line
  */
 public Line(Point start, Point end) {
  this.start = start;
  this.end = end;
 }

 /**
  * Instantiates a new Point.
  *
  * @param x1 - x value of the start point
  * @param y1 - y value of the start point
  * @param x2 - x value of the emd point
  * @param y2 - y value of the end point
  */
 public Line(double x1, double y1, double x2, double y2) {
  this.start = new Point(x1, y1);
  this.end = new Point(x2, y2);
 }


 /**.
  * The method calculates the length of the line
  *
  * @return The method returns the length of the line
  */
 public double length() {
  //calculating the line's length using distance method of Point class
  return this.end.distance(this.start);
 }

 /**.
  * The method calculates the middle point of the line
  *
  * @return The method returns the middle point of the line
  */
 public Point middle() {
  //Calculate the middle x and y values
  double middleX = (this.end.getX() + this.start.getX()) / 2;
  double middleY = (this.end.getY() + this.start.getY()) / 2;

  //Create the middle point
  Point middlePoint = new Point(middleX, middleY);

  return middlePoint;
 }

 /**
  * @return The method returns the start point of the line
  */
 public Point start() {
  return this.start;
 }

 /**
  * @return The method returns the end point of the line
  */
 public Point end() {
  return this.end;
 }

 /**.
  * The method checks if the lines intersect
  * @param other - other line
  * @return The method returns true if the lines intersect, false otherwise
  */
 public boolean isIntersecting(Line other) {
  /*Check if the lines intersect using intersectionWith method
   * Case intersectionWith returns null, the lines don't intersect
   */
  if (intersectionWith(other) == null) {
   return false;
  }

  return true;
 }

 /**.
  * The method checks if x value is in range of the line
  * @param xVal - x value of a point
  * @return The method returns true if the x value is in range, false otherwise
  */
 public boolean checkXRangeLine1(double xVal) {
  double supXOfLine1 = this.start.getX();

  //Case the x value of the end point is higher, store it in supXOfLine1
  if (this.end.getX() > supXOfLine1) {
   supXOfLine1 = this.end.getX();
  }

  //Case xVal is higher than supXOfLine1, it's not in the range of the line
  if (xVal > supXOfLine1) {
   return false;
  }

  double infXOfLine1 = this.start.getX();

  //Case the x value of the end point is lower, store it in infXOfLine1
  if (this.end.getX() < infXOfLine1) {
   infXOfLine1 = this.end.getX();
  }

  //Case xVal is lower than infXOfLine1, it's not in the range of the line
  if (xVal < infXOfLine1) {
   return false;
  }
  return true;
 }

 /**.
  * The method checks if y value is in range of the line
  * * @param yVal - y value of a point
  * @return The method returns true if the y value is in range, false otherwise
  */
 public boolean checkYRangeLine1(double yVal) {
  double supYOfLine1 = this.start.getY();

  //Case the y value of the end point is higher, store it in supYOfLine1
  if (this.end.getY() > supYOfLine1) {
   supYOfLine1 = this.end.getY();
  }

  //Case yVal is higher than supYOfLine1, it's not in the range of the line
  if (yVal > supYOfLine1) {
   return false;
  }

  double infYOfLine1 = this.start.getY();

  //Case the y value of the end point is lower, store it in infYOfLine1
  if (this.end.getY() < infYOfLine1) {
   infYOfLine1 = this.end.getY();
  }

  //Case yVal is lower than infYOfLine1, it's not in the range of the line
  if (yVal < infYOfLine1) {
   return false;
  }
  return true;
 }

 /**.
  * The method checks if x value is in the range of a line
  * @param xVal - x value of a point
  * @param other - other line
  * @return The method returns true if the x value is in range, false otherwise
  */
 public boolean checkXRangeLine2(double xVal, Line other) {
  double supXOfLine2 = other.start().getX();

  //Case the x value of the end point is higher, store it in supXOfLine2
  if (other.end().getX() > supXOfLine2) {
   supXOfLine2 = other.end().getX();
  }

  //Case xVal is higher than supXOfLine2, it's not in the range of the line
  if (xVal > supXOfLine2) {
   return false;
  }

  double infXOfLine2 = other.start().getX();

  //Case the x value of the end point is lower, store it in infXOfLine2
  if (other.end().getX() < infXOfLine2) {
   infXOfLine2 = other.end().getX();
  }

  //Case xVal is lower than infXOfLine2, it's not in the range of the line
  if (xVal < infXOfLine2) {
   return false;
  }
  return true;
 }

 /**.
  * The method checks if y value is in the range of a line
  * @param yVal - y value of a point
  * @param other - other line
  * @return The method returns true if the y value is in range, false otherwise
  */
 public boolean checkYRangeLine2(double yVal, Line other) {
  double supYOfLine2 = other.start().getY();

  //Case the y value of the end point is higher, store it in supYOfLine2
  if (other.end().getY() > supYOfLine2) {
   supYOfLine2 = other.end().getY();
  }

  //Case yVal is higher than supYOfLine2, it's not in the range of the line
  if (yVal > supYOfLine2) {
   return false;
  }

  double infYOfLine2 = other.start().getY();

  //Case the y value of the end point is lower, store it in infYOfLine2
  if (other.end().getY() < infYOfLine2) {
   infYOfLine2 = other.end().getY();
  }

  //Case yVal is lower than infYOfLine2, it's not in the range of the line
  if (yVal < infYOfLine2) {
   return false;
  }
  return true;
 }

 /**.
  * The method checks if two vertical lines intersect, and if they do, it returns the intersection point
  * @param other - other line
  * @return The method returns the intersection point if the lines intersect and null otherwise
  */
 private Point caseOfInfinityLines(Line other) {
  //Calculate the supremums of the lines
  double supYOfLine1 = this.start.getY();
  double supYOfLine2 = other.start.getY();
  supYOfLine1 = Math.max(supYOfLine1, this.end.getY());
  supYOfLine2 = Math.max(supYOfLine2, other.end().getY());

  //Calculate the infimums of the lines
  double infYOfLine1 = this.start.getY();
  double infYOfLine2 = other.start.getY();
  infYOfLine1 = Math.min(infYOfLine1, this.end.getY());
  infYOfLine2 = Math.min(infYOfLine2, other.end().getY());

  //Case the supremum of the first line is the infimum of the second line - the lines intersect
  if (supYOfLine1 == infYOfLine2) {
   return new Point(this.start.getX(), supYOfLine1);
  }

  //Case the supremum of the second line is the infimum of the first line - the lines intersect
  if (supYOfLine2 == infYOfLine1) {
   return new Point(this.start.getX(), supYOfLine2);
  }

  return null;
 }

 /**.
  * The method checks if two lines intersect, and if they do, it returns the intersection point
  * @param other - other line
  * @return The method returns the intersection point if the lines intersect and null otherwise
  */
 public Point intersectionWith(Line other) {
  //Calculate the slopes of the lines
  double slopeOfFirstLine = (this.end.getY() - this.start.getY()) / ((this.end.getX()) - this.start.getX());
  double slopeOfSecondLine = (other.end().getY() - other.start().getY())
    / ((other.end().getX()) - other.start().getX());

  /* Case the slopes are equal, check if there's only one intersection point
   * Case there's one intersection point, the lines intersect - return the intersection point
   * Otherwise, the lines don't intersect - return null
   */
  if (slopeOfFirstLine == slopeOfSecondLine) {
   if ((this.end().equals(other.start())) && !(this.start.equals(other.end()))) {
    return this.end();
   }

   if ((other.end().equals(this.start())) && !(this.end.equals(other.start()))) {
    return this.end();
   }

   if ((this.end().equals(other.end())) && !checkXRangeLine1(other.start().getX())) {
    return this.end();
   }

   if ((this.start().equals(other.start())) && !(checkXRangeLine1(other.end().getX()))
     && !(checkXRangeLine2(this.end.getX(), other))) {
    return this.end();
   }

   return null;
  }

  /*
   * [Y = Mx + freeVal] is the formula of a line
   * After calculating the slopes, calculate the B's value
   */
  double freeVal1 = this.start.getY() - (slopeOfFirstLine * this.start.getX());
  double freeVal2 = other.start().getY() - (slopeOfSecondLine * other.start.getX());

  //Case first line's slope is infinity [X = 5 for example]
  if (this.start.getX() == this.end.getX()) {
   double intersectionPointXVal = this.start.getX();

   //Check if the x value of the intersection point is in the range of the second line
   if (!checkXRangeLine2(intersectionPointXVal, other)) {
    return null;
   }

   double intersectionPointYVal;

   //Calculate the y value of the intersection point in case the other line slope isn't infinity
   if ((other.end().getX()) != other.start().getX()) {
    intersectionPointYVal = (slopeOfSecondLine * intersectionPointXVal) + freeVal2;

    //Check if the y value of the intersection point is in the range of the second line
    if (!checkYRangeLine1(intersectionPointYVal)) {
     return null;
    }

    //Return the intersection point
    return new Point(intersectionPointXVal, intersectionPointYVal);
   }

   if (caseOfInfinityLines(other) == null) {
    return null;
   }
  }

  //Case second line's slope is infinity
  if (other.start().getX() == other.end().getX()) {
   double intersectionPointXVal = other.start().getX();

   //Check if the x value of the intersection point is in the range of the first line
   if (!checkXRangeLine1(intersectionPointXVal)) {
    return null;
   }

   //Calculate the y value of the intersection point
   double intersectionPointYVal = (slopeOfFirstLine * intersectionPointXVal) + freeVal1;

   //Check if the y value of the intersection point is in the range of the first line
   if (!checkYRangeLine2(intersectionPointYVal, other)) {
    return null;
   }

   //Return the intersection point
   return new Point(intersectionPointXVal, intersectionPointYVal);
  }

  //Calculate the slope differences and the B's differences
  double slopeDifference = slopeOfFirstLine - slopeOfSecondLine;
  double freeValDifference = freeVal2 - freeVal1;

  //Calculate the x value of the intersection point
  double intersectionPointXVal =  freeValDifference / slopeDifference;

  //Check if the x value of the intersection point is in the range of the first line
  if (!checkXRangeLine1(intersectionPointXVal)) {
   return null;
  }

  //Check if the x value of the intersection point is in the range of the second line
  if (!checkXRangeLine2(intersectionPointXVal, other)) {
   return null;
  }

  //Calculate the y value of the intersection point
  double intersectionPointYVal = (slopeOfFirstLine * intersectionPointXVal) + freeVal1;

  //Return the intersection point
  return new Point(intersectionPointXVal, intersectionPointYVal);
 }


 /**.
  * The method checks if two lines are equal
  * @param other - other line
  * @return The method returns true if the lines are equal, false otherwise
  */
 public boolean equals(Line other) {
  //Case x and y values of both start and end points of the lines are equal, the lines are equal
  if ((this.start.getX() == other.start().getX()) && (this.end.getX() == other.end().getX())
    && (this.start.getY() == other.start().getY()) && (this.end.getY() == other.end().getY())) {
   return true;
  }
  return false;
 }

 /**.
  * The method checks if a line intersects with a rectangle
  * Case they intersect, return the closest intersection point to the start of the line
  * Otherwise, return null
  * @param rect - a rectangle
  * @return The method returns the closest intersection point to start of the line or null
  */
 public Point closestIntersectionToStartOfLine(Rectangle rect) {
  //Initialize an intersection points list
  List<Point> intersectionPointsList = rect.intersectionPoints(new Line(this.start, this.end));
  //Case the list is empty - return null
  if (intersectionPointsList == null || intersectionPointsList.size() == 0) {
   return null;
  }

  double minDistance = intersectionPointsList.get(0).distance(this.start);

  Point closestIntersectionPoint = intersectionPointsList.get(0);

  //Find the closest intersection point
  for (int i = 1; i < intersectionPointsList.size(); i++) {
   if (this.start.distance(intersectionPointsList.get(i)) < minDistance) {
    closestIntersectionPoint = intersectionPointsList.get(i);
   }
  }

  return closestIntersectionPoint;
 }
}
