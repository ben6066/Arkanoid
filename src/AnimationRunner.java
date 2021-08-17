import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public class AnimationRunner {
   //AnimationRunner's fields
   private GUI gui;
   private int framesPerSecond;
   private Sleeper sleeper;
   /**.
    * Instantiates a new AnimationRunner.
    * The constructor initializes the framesPerSecond, Sleeper and the gui
    * @param gui - a GUI
    */
   public AnimationRunner(GUI gui) {
       this.gui = gui;
       this.framesPerSecond = 60;
       this.sleeper = new Sleeper();
   }
   /**.
    * The method takes an Animation object and runs it
    * @param animation - an animation object to run
    */
   public void run(Animation animation) {
      int millisecondsPerFrame = 1000 / this.framesPerSecond;
      while (!animation.shouldStop()) {
         long startTime = System.currentTimeMillis(); // timing
         DrawSurface d = gui.getDrawSurface();
         animation.doOneFrame(d);
         gui.show(d);
         long usedTime = System.currentTimeMillis() - startTime;
         long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
         if (milliSecondLeftToSleep > 0) {
             this.sleeper.sleepFor(milliSecondLeftToSleep);
         }
      }
   }
   /**.
    * @return - The method returns the GUI
    */
   public GUI getGUI() {
       return this.gui;
   }
}