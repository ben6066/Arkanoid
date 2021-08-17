import biuoop.DrawSurface;
import biuoop.Sleeper;

/**.
 * This class creates a countdown before each level
 */

/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public class CountdownAnimation implements Animation {
    //CountdownAnimation's fields
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private int startFrom;

    /**
     * Instantiates a new countdown animation.
     * @param numOfSeconds - number of seconds to countdown
     * @param countFrom - the first number the countdown starts form
     * @param gameScreen - the sprite collection
     */
   public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
       this.numOfSeconds = numOfSeconds;
       this.countFrom = countFrom;
       this.gameScreen = gameScreen;
       this.stop = false;
       this.startFrom = countFrom;
   }

   @Override
   public void doOneFrame(DrawSurface d) {
       //Draw all sprites before the countdown
       this.gameScreen.drawAllOn(d);

       //Initialize the sleeper
       Sleeper sleeper = new Sleeper();
       double millisecondsPerFrame = 1000 * (this.numOfSeconds / this.countFrom);

       //Decoration and display the countdown
       d.setColor(java.awt.Color.WHITE);
       d.fillCircle(397, 365, 60);
       d.setColor(java.awt.Color.BLACK);
       d.drawCircle(397, 365, 60);
       d.setColor(java.awt.Color.BLACK);
       d.drawText(370, 400, String.valueOf(this.startFrom), 100);

       //Case the countdown ended
       if (this.startFrom == 0) {
           this.stop = true;
       }

       //While the countdown is still running
       if (this.startFrom < this.countFrom) {
           sleeper.sleepFor((long) millisecondsPerFrame);
       }

       //Update countdown
       this.startFrom--;
   }

   @Override
   public boolean shouldStop() {
       return this.stop;
   }
}