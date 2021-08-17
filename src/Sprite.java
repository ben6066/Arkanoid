import biuoop.DrawSurface;

/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public interface Sprite {
   /**.
    * The method draws the sprite on the surface
    * @param d - draw surface
   */
   void drawOn(DrawSurface d);
   /**.
    * The method notifies the sprite that time has passed
   */
   void timePassed();
}
