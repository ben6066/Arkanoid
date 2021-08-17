import biuoop.DrawSurface;

/**.
 * This class defined the You Win screen
 */
public class YouWin implements Animation {
   //PauseScreen's fields
   private Counter score;
   private boolean stop;

   /**
    * Instantiates a new GameOver screen.
    * @param s - a counter
    */
   public YouWin(Counter s) {
      this.score = s;
      this.stop = false;
   }

   @Override
   public void doOneFrame(DrawSurface d) {
       d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);
   }

   @Override
   public boolean shouldStop() { return this.stop; }
}