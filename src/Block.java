import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //Block's fields
    private Rectangle blockingRec;
    private Color recColor;
    private List<HitListener> hitListeners;

    /**
     * Instantiates a new Block.
     *
     * @param rec - the block object is represented as a rectangle
     * @param color - color of the block
     */
    public Block(Rectangle rec, Color color) {
        this.blockingRec = rec;
        this.recColor = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    @Override
    /**
     * @return The method returns the blocking rectangle that represents the block
     */
    public Rectangle getCollisionRectangle() {
        return this.blockingRec;
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
        Line edge = this.blockingRec.pointOnWhichEdge(collisionPoint);

        //Case no collision occurred - return the current velocity
        if (edge == null) {
            return currentVelocity;
        }

        //Calculate all of the block's vertexes
        Point upperLeft = this.blockingRec.getUpperLeft();
        Point downLeft = this.blockingRec.downLeft();
        Point upperRight = this.blockingRec.upperRight();
        Point downRight = this.blockingRec.downRight();

        Velocity velocityAfterCollision;

        //Case the collision point is one of the block's vertexes - change both horizontal and vertical velocities
        if (collisionPoint.equals(upperLeft) || collisionPoint.equals(downLeft)
                || collisionPoint.equals(upperRight) || collisionPoint.equals(downRight)) {
            velocityAfterCollision = new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            this.notifyHit(hitter);
            return velocityAfterCollision;
        }

        //Case a collision occurred with a vertical edge - change the horizontal velocity
        if (edge.start().getX() == edge.end().getX()) {
            velocityAfterCollision = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            this.notifyHit(hitter);
            return velocityAfterCollision;
        }

        //Case a collision occurred with a horizontal edge - change the vertical velocity
        velocityAfterCollision = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());

        //Notify that a hit had been occurred
        this.notifyHit(hitter);

        return velocityAfterCollision;
    }

    /**
     * @return The method returns the color of the Block
     */
    public java.awt.Color getColor() {
        return this.recColor;
    }

    @Override
    /**.
     * The method draws the block on a surface
     * @param surface - a surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(java.awt.Color.BLACK);
        Point upperLeft = this.blockingRec.getUpperLeft();

        int blockingRecWidth = (int) this.blockingRec.getWidth();
        int blockingRecHeight = (int) this.blockingRec.getHeight();
        int upperLeftXVal = (int) upperLeft.getX();
        int upperLeftYVal = (int) upperLeft.getY();

        surface.fillRectangle(upperLeftXVal, upperLeftYVal, blockingRecWidth, blockingRecHeight);

        surface.setColor(this.recColor);
        surface.fillRectangle(upperLeftXVal + 1, upperLeftYVal + 1, blockingRecWidth - 2, blockingRecHeight - 2);
    }

    @Override
    /**.
     * not used at the moment
     */
    public void timePassed() {

    }

    /**.
     * The method will add a block to the game, calling the appropriate game methods.
     * @param g - a game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**.
     * The method add a new listener to the listeners list
     * @param hl - a listener to add
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**.
     * The method removes a listener from the listeners list
     * @param hitListener - a listener to remove
     */
    public void removeHitListener(HitListener hitListener) {
        if (!this.hitListeners.isEmpty()) {
            for (int i = 0; i < this.hitListeners.size(); i++) {
                if (this.hitListeners.get(i).equals(hitListener)) {
                    this.hitListeners.remove(i);
                    return;
                }
            }
        }
    }

    /**.
     * The method notifies all the listeners about a hit event
     * @param hitter - the hitting ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
