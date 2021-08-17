import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public class GameEnvironment {
 //GameEnvironment's field
 private List<Collidable> obstacles;

 /**
  * Instantiates a new GameEnvironment.
  */
 public GameEnvironment() {
  //Initialize the collidable arraylist
  this.obstacles = new ArrayList<Collidable>();
 }

 /**
  * The method adds the given collidable to the environment.
  * @param c - a collidable to add to the environment
  */
 public void addCollidable(Collidable c) {
  this.obstacles.add(c);
 }

 /**.
  * The method assumes an object is moving from line.start() to line.end()
  * Case this object will not collide with any of the collidables in this collection, return null
  * Otherwise, return the information about the closest collision that is going to occur
  *
  * @param trajectory - the trajectory of the object
  * @return The method returns the info about the collision point and object or null
  */
 public CollisionInfo getClosestCollision(Line trajectory) {
  //Case the collection is empty
  if (this.obstacles.size() == 0) {
   return null;
  }

  Point collisionPoint = null, closestCollisionPoint = null;
  Collidable obstacle = null;
  boolean flag = false;

  //Find the closest collision point
  for (int i = 0; i < this.obstacles.size(); i++) {
   collisionPoint = trajectory.closestIntersectionToStartOfLine(this.obstacles.get(i).getCollisionRectangle());

   if (collisionPoint != null) {
    //Case it's the first collision point found
    if (!flag) {
     flag = true;
     closestCollisionPoint = collisionPoint;
     obstacle = this.obstacles.get(i);
     continue;
    }

    //Case another collision point found - check if it's closer than the current closest one
    if ((flag)
    && (trajectory.start().distance(collisionPoint) < trajectory.start().distance(closestCollisionPoint))) {
     closestCollisionPoint = collisionPoint;
     obstacle = this.obstacles.get(i);
    }
   }
  }

  //Case no collision points found
  if (closestCollisionPoint == null || obstacle == null) {
   return null;
  }

  //Create a CollisionInfo with the info about the collision point and the collision object
  CollisionInfo closestCollisionPointInfo = new CollisionInfo(closestCollisionPoint, obstacle);

  return closestCollisionPointInfo;
 }

 /**
  * @return The method returns the collidables collection
  */
 public List<Collidable> getCollidables() {
  return this.obstacles;
 }

 /**.
  * The method updates the collidables list with a given list
  * @param collidablesList - the collidables list
  */
 public void updateList(List<Collidable> collidablesList) {
     this.obstacles = collidablesList;
 }
}
