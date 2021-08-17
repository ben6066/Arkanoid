import java.util.List;

/**.
 * The LevelInformation interface specifies the information required to fully describe a level
 */
public interface LevelInformation {
    /**.
     * @return - The method returns the number of balls in the level
     */
   int numberOfBalls();

   /**.
    * @return - The method returns a list of the balls velocities
    */
   List<Velocity> initialBallVelocities();

   /**.
    * @return - The method returns the paddle's speed
    */
   int paddleSpeed();

   /**.
    * @return - The method returns the paddle's width
    */
   int paddleWidth();

   /**.
    * The level name will be displayed at the top of the screen.
    * @return - The method returns the level's name
    */
   String levelName();

   /**.
    * @return - The method returns the background of the level as a sprite
    */
   Sprite getBackground();

   /**.
    * @return - The method returns a list of the blocks in the level
    */
   List<Block> blocks();

   /**.
    * @return - The method returns the number of blocks that should be removed
    */
   int numberOfBlocksToRemove();
}