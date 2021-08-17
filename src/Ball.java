import biuoop.DrawSurface;

/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public class Ball implements Sprite {
    //Balls's fields
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment environment;

    /**
     * Instantiates a new Ball.
     *
     * @param center - center point of the ball
     * @param r - radius of the ball
     * @param color - color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x - x value of the ball
     * @param y - y value of the ball
     * @param r - radius of the ball
     * @param color - color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * @return The method returns the x value of the center of the Ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return The method returns the y value of the center of the Ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return The method returns the size of the Ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return The method returns the color of the Ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**.
     * The method draws the ball on a surface
     *
     * @param surface - a surface to draw on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        //Set the color of the ball and draw it on the surface
        surface.setColor(java.awt.Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**.
     * The method sets the Ball's velocity
     *
     * @param v - Ball's velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**.
     * The method sets the Ball's velocity
     *
     * @param dx - Ball's speed in x axis
     * @param dy - Ball's speed in y axis
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return The method returns the velocity of the Ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**.
     * The method is moving the ball one step using applyToPoint method from Velocity class
     * Case the ball's next step is out of boundaries, the method changes the Ball's direction
     * The method assumes that the start point of the board is (0,0)
     *
     * @param horizontal - horizontal boundary
     * @param vertical - vertical boundary
     */
    public void moveOneStep(int horizontal, int vertical) {
        //Case the ball is out of horizontal boundaries - change the direction
        if (this.center.getX() + this.velocity.getDx() + this.radius > horizontal) {
            setVelocity(new Velocity(-this.velocity.getDx(), this.velocity.getDy()));
        }

        //Case the ball is out of vertical boundaries - change the direction
        if (this.center.getY() + this.velocity.getDy() - this.radius < 0) {
            setVelocity(new Velocity(this.velocity.getDx(), -this.velocity.getDy()));
        }

        //Case the ball is out of horizontal boundaries - change the direction
        if (this.center.getX() + this.velocity.getDx() - this.radius < 0) {
            setVelocity(new Velocity(-this.velocity.getDx(), this.velocity.getDy()));
        }

        //Case the ball is out of vertical boundaries - change the direction
        if (this.center.getY() + this.velocity.getDy() + this.radius > vertical) {
            setVelocity(new Velocity(this.velocity.getDx(), -this.velocity.getDy()));
        }

        //Move the ball one step
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**.
     * The method is moving the ball one step using applyToPoint method from Velocity class
     * Case the ball's next step is out of boundaries, the method changes the Ball's direction
     *
     * @param horizontalStart - left horizontal boundary
     * @param verticalStart - left vertical boundary
     * @param horizontalEnd - right horizontal boundary
     * @param verticalEnd - right vertical boundary
     */
    public void moveOneStep(int horizontalStart, int verticalStart, int horizontalEnd, int verticalEnd) {
        //Case the ball is out of horizontal boundaries - change the direction
        if (this.center.getX() + this.velocity.getDx() + this.radius > horizontalEnd) {
            setVelocity(new Velocity(-this.velocity.getDx(), this.velocity.getDy()));
        }

        //Case the ball is out of vertical boundaries - change the direction
        if (this.center.getY() + this.velocity.getDy() - this.radius < verticalStart) {
            setVelocity(new Velocity(this.velocity.getDx(), -this.velocity.getDy()));
        }

        //Case the ball is out of horizontal boundaries - change the direction
        if (this.center.getX() + this.velocity.getDx() - this.radius < horizontalStart) {
            setVelocity(new Velocity(-this.velocity.getDx(), this.velocity.getDy()));
        }

        //Case the ball is out of vertical boundaries - change the direction
        if (this.center.getY() + this.velocity.getDy() + this.radius > verticalEnd) {
            setVelocity(new Velocity(this.velocity.getDx(), -this.velocity.getDy()));
        }

        //Move the ball one step
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**.
     * The method sets the game environment
     * @param game - GameEnvironment
     */
    public void setGameEnvironment(GameEnvironment game) {
        this.environment = game;
    }

    /**.
     * The method is moving the ball one step using applyToPoint method from Velocity class
     * Case the ball's next step is out of boundaries, the method changes the Ball's direction
     * Case a collision occur in the the ball's next step, the method changes the Ball's Velocity and direction
     */
    public void moveOneStep() {
        //Calculate the ball's next point assuming there was no collision
        Point nonCollisionEndPoint = this.getVelocity().applyToPoint(this.center);

        Line trajectory = new Line(this.center, nonCollisionEndPoint);

        //Receive information about the collision - if one occurred
        CollisionInfo info = this.environment.getClosestCollision(trajectory);

        //Case no collision occurred - move the ball with the same velocity
        if (info == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }

        //Declaration of the collision object - a paddle or a block
        Collidable collisiobObject = info.collisionObject();

        if (collisiobObject == null) {
            return;
        }

        //Get the collision edge
        Line colissionEdge = collisiobObject.getCollisionRectangle().pointOnWhichEdge(info.collisionPoint());

        //Case no collision occurred
        if (colissionEdge == null) {
            return;
  }
        //Case the ball's next step is out of boundaries - change it's velocity by using the 'hit' function
        if (780 - info.collisionPoint().getX() < 0.7 || info.collisionPoint().getX() - 20 < 0.7
                || 580 - info.collisionPoint().getY() < 0.7 || 20 - info.collisionPoint().getY() < 0.7) {
            setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.getVelocity()));
            return;
        }

        /* Case the ball's next step is a collision with the top edge - check from which side it occurred
         * Change the ball's center a bit after the collision point
         */
        if (colissionEdge.start().equals(collisiobObject.getCollisionRectangle().getUpperLeft())
                && (colissionEdge.start().getY() == colissionEdge.end().getY())) {
            if (this.getVelocity().getDx() > 0 && this.getVelocity().getDy() > 0) {
                this.center.changePointValues(info.collisionPoint().getX() + 0.7, info.collisionPoint().getY() - 0.7);
   } else if (this.getVelocity().getDx() < 0 && this.getVelocity().getDy() > 0) {
                this.center.changePointValues(info.collisionPoint().getX() - 0.7, info.collisionPoint().getY() - 0.7);
   }
        }

        /* Case the ball's next step is a collision with the bottom edge - check from which side it occurred
         * Change the ball's center a bit after the collision point
         */
        if (colissionEdge.start().equals(collisiobObject.getCollisionRectangle().downLeft())
                && (colissionEdge.start().getY() == colissionEdge.end().getY())) {

   if (this.getVelocity().getDx() > 0 && this.getVelocity().getDy() < 0) {
                this.center.changePointValues(info.collisionPoint().getX() + 0.7, info.collisionPoint().getY() + 0.7);
   } else if (this.getVelocity().getDx() < 0 && this.getVelocity().getDy() < 0) {
                this.center.changePointValues(info.collisionPoint().getX() - 0.7, info.collisionPoint().getY() + 0.7);
   }
        }

        /* Case the ball's next step is a collision with the left edge - check from which side it occurred
         * Change the ball's center a bit after the collision point
         */
        if (colissionEdge.start().equals(collisiobObject.getCollisionRectangle().downLeft())
                && (colissionEdge.start().getX() == colissionEdge.end().getX())) {
            if (this.getVelocity().getDx() > 0 && this.getVelocity().getDy() > 0) {
                this.center.changePointValues(info.collisionPoint().getX() - 0.7, info.collisionPoint().getY() + 0.7);
             } else if (this.getVelocity().getDx() > 0 && this.getVelocity().getDy() < 0) {
                this.center.changePointValues(info.collisionPoint().getX() - 0.7, info.collisionPoint().getY() - 0.7);
             }
        }

        /* Case the ball's next step is a collision with the right edge - check from which side it occurred
         * Change the ball's center a bit after the collision point
         */
        if (colissionEdge.start().equals(collisiobObject.getCollisionRectangle().upperRight())
                && (colissionEdge.start().getX() == colissionEdge.end().getX())) {
            if (this.getVelocity().getDx() < 0 && this.getVelocity().getDy() < 0) {
                this.center.changePointValues(info.collisionPoint().getX() + 0.7, info.collisionPoint().getY() - 0.7);
   } else if (this.getVelocity().getDx() < 0 && this.getVelocity().getDy() > 0) {
                this.center.changePointValues(info.collisionPoint().getX() + 0.7, info.collisionPoint().getY() + 0.7);
   }
        }

        //Notify that a collision occurred and set the ball's new velocity using the 'hit' function
        setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.getVelocity()));
    }

    @Override
    /**.
     * The method is moves the ball one step using the 'moveOneStep' function
     */
    public void timePassed() {
        moveOneStep();
    }

    /**.
     * The method will adds a ball to the game, calling the appropriate game methods.
     * @param g - a Game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**.
     * The method removes a ball to the game, calling the appropriate methods.
     * @param g - a Game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
