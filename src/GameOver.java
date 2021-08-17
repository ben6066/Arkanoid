import biuoop.DrawSurface;

/**.
 * This class defined the Game Over screen
 */
public class GameOver implements Animation {
   //PauseScreen's fields
   private Counter score;
   private boolean stop;

   /**.
    * Instantiates a new GameOver screen.
    * @param s - a counter
    */
   public GameOver(Counter s) {
      this.score = s;
      this.stop = false;
   }

   @Override
   public void doOneFrame(DrawSurface d) {
       d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
   }

   @Override
   public boolean shouldStop() { return this.stop; }
}