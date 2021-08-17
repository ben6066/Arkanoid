/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public interface Collidable {
  /**
   * The method returns the "collision shape" of the object.
   * @return the method return the collision shape of the object
  */
   Rectangle getCollisionRectangle();
   /**.
    * The method notifies the object that we collided with it at collisionPoint with a given velocity
    * The return is the new velocity expected after the hit (based on the force the object inflicted on us)
    * @param collisionPoint - collision Point
    * @param currentVelocity - current velocity of the ball
    * @param hitter - the hitting ball
    * @return the method returns an updated velocity
    */
   Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
