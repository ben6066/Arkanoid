import biuoop.DrawSurface;

/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public interface Animation {
    /**
     * This method is in charge of the game-specific logic.
     * @param d - the surface the logic is being draw on.
     */
   void doOneFrame(DrawSurface d);
   /**
    * This method is in charge of the stopping conditions.
    * @return - the method returns true if the game should stop, false otherwise.
    */
   boolean shouldStop();
}