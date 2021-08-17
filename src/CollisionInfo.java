/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public class CollisionInfo {
 //CollisionInfo's fields
 private Point collisionPoint;
 private Collidable collisionObject;

 /**.
  * Instantiates a new CollisionInfo
  *
  * @param collisionPoint - the collision point
  * @param collisionObject - the collision object
  */
 public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
  this.collisionPoint = collisionPoint;
  this.collisionObject = collisionObject;
 }

 /**
  * @return The method returns the point at which the collision occurs
  */
 public Point collisionPoint() {
  return this.collisionPoint;
 }

 /**
  * @return The method returns the collidable object involved in the collision
  */
 public Collidable collisionObject() {
  return this.collisionObject;
 }

 /**.
  * The method print the info
  */
 public void printInfo() {
  if (this.collisionPoint == null || this.collisionObject == null) {
   return;
  }
 }
}
