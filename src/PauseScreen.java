import biuoop.DrawSurface;

/**.
 * This class defined the pause screen
 */
public class PauseScreen implements Animation {
   //PauseScreen's fields
   private boolean stop;

   /**
    * Instantiates a new PauseScreen.
    */
   public PauseScreen() {
      this.stop = false;
   }

   @Override
   public void doOneFrame(DrawSurface d) {
      d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
   }

   @Override
   public boolean shouldStop() { return this.stop; }
}