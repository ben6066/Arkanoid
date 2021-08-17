import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public class Paddle implements Sprite, Collidable {
 //Paddle's fields
 private biuoop.KeyboardSensor keyboard;
 private Rectangle player;
 private double speed;

 /**
  * Instantiates a new Paddle.
  *
  * @param keyboard - the keyboard used to move the paddle
  * @param upperLeft - the upper left vertex of the paddle
  * @param width - the width of the paddle
  * @param speed - the speed of the paddle
  */
 public Paddle(KeyboardSensor keyboard, Point upperLeft, double width, double speed) {
  //Initialize the paddle using a rectangle, and initialize the keyboard
  this.player = new Rectangle(upperLeft, width, 15);
  this.keyboard = keyboard;
  this.speed = speed;
 }

 /**.
  * The method moves the paddle to the left case left arrow is pressed on the keyboard
  */
 public void moveLeft() {
  double currentXUpperLeftVal = this.player.getUpperLeft().getX();

  //Check that the paddle doesn't cross the left border
  if (currentXUpperLeftVal <= 20) {
   return;
  }
  //Move paddle to the left
  double currentYVal = this.player.getUpperLeft().getY();
  Point moveLeft = null;
  moveLeft = new Point(this.player.getUpperLeft().getX() - this.speed, currentYVal);
  this.player = new Rectangle(moveLeft, this.player.getWidth(), this.player.getHeight());
 }
 /**.
  * The method moves the paddle to the right case right arrow is pressed on the keyboard
  */
 public void moveRight() {
  double currentXUpperRightVal = this.player.getUpperLeft().getX() + this.player.getWidth();

  //Check that the paddle doesn't cross the right border
  if (currentXUpperRightVal >= 780) {
   return;
  }

  //Move paddle to the right
  double currentYVal = this.player.getUpperLeft().getY();
  Point moveRight = null;
  moveRight = new Point(this.player.getUpperLeft().getX() + this.speed, currentYVal);
  this.player = new Rectangle(moveRight, this.player.getWidth(), this.player.getHeight());
 }

 /**.
  * The method checks if the "left" or "right" keys are pressed, and if so move it accordingly.
  */
 public void timePassed() {
  if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
   moveLeft();
  } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
   moveRight();
  }
 }

 @Override
 /**.
  * The method draws the paddle on a surface
  * @param surface - a surface to draw on
  */
 public void drawOn(DrawSurface d) {
  int upperLeftXVal = (int) this.player.getUpperLeft().getX();
  int upperLeftYVal = (int) this.player.getUpperLeft().getY();

  d.setColor(Color.orange);
  d.fillRectangle(upperLeftXVal, upperLeftYVal, (int) this.player.getWidth(), (int) this.player.getHeight());
 }

 @Override
 /**
  * @return The method returns the blocking rectangle that represents the paddle
  */
 public Rectangle getCollisionRectangle() {
  return this.player;
 }

 @Override
 /**.
  * The method notifies the object that we collided with it at collisionPoint with a given velocity.
  * The method returns the new velocity expected after the hit (based on the force the object inflicted on us)
  *
  * @param collisionPoint - the collision point
  * @param currentVelocity - the current velocity
  * @return The method returns the updated velocity or the current velocity
  */
 public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
  //Calculate the line we collided with, 'null' if no collision occurred
  Line edge = this.player.pointOnWhichEdge(collisionPoint);

  //Case no collision occurred - return the current velocity
        if (edge == null) {
            return currentVelocity;
        }

        //Size of each region
  double deltaX = this.player.getWidth() / 5;

  //Dividing the regions
  double firstRegionXStart = this.player.getUpperLeft().getX();
  double secondRegionXStart = firstRegionXStart + deltaX;
  double middleRegionXStart = secondRegionXStart + deltaX;
  double fourthRegionXStart = middleRegionXStart + deltaX;
  double fifthRegionXStart = fourthRegionXStart + deltaX;

  //Case the object hits left edge of the paddle - change only the horizontal velocity
  if (collisionPoint.getX() == firstRegionXStart
  && collisionPoint.getY() >  this.player.getUpperLeft().getY()) {
   return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
  }

  //Case the object hits the right edge of the paddle - change only the horizontal velocity
  if (collisionPoint.getX() == fifthRegionXStart
  && collisionPoint.getY() >  this.player.upperRight().getY()) {
   return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
  }

  double newSpeed = Velocity.calculateSpeed(currentVelocity);

  //Case the object hits the first region - the object bounces back with an angle of 300 degrees
  if ((collisionPoint.getX() >= firstRegionXStart) && (collisionPoint.getX() <= secondRegionXStart)) {
   return Velocity.fromAngleAndSpeed(300, newSpeed);
  } else if ((collisionPoint.getX() > secondRegionXStart)
  && (collisionPoint.getX() <= middleRegionXStart)) {
   return Velocity.fromAngleAndSpeed(330, newSpeed);
  } else if ((collisionPoint.getX() > middleRegionXStart)
  && (collisionPoint.getX() <= fourthRegionXStart)) {
   return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
  } else if ((collisionPoint.getX() > fourthRegionXStart)
  && (collisionPoint.getX() <= fifthRegionXStart)) {
   return Velocity.fromAngleAndSpeed(30, newSpeed);
  } else if ((collisionPoint.getX() > fifthRegionXStart)
  && (collisionPoint.getX() <= this.player.getUpperLeft().getX() + this.player.getWidth())) {
   return Velocity.fromAngleAndSpeed(60, newSpeed);
  }
  return null;
 }

 /**.
  * The method will add the paddle to the game, calling the appropriate game methods.
  * @param g - game
  */
 public void addToGame(GameLevel g) {
  g.addSprite(this);
  g.addCollidable(this);
 }
}
