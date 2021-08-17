/**
* ScoreTrackingListener updates a counter when blocks are being hit and removed.
*/
public class ScoreTrackingListener implements HitListener {
   private Counter currentScore;

   /**
    * Instantiates a new ScoreTrackingListener.
    *
    * @param scoreCounter - a score counter
    */
   public ScoreTrackingListener(Counter scoreCounter) {
      this.currentScore = scoreCounter;
   }

   /**.
    * This method is called whenever the beingHit object is hit.
    * Each block being hit awards 5 points.
    * @param beingHit - the block being hit
    * @param hitter - the hitting ball
    */
   public void hitEvent(Block beingHit, Ball hitter) {
       //Increase the score by 5 because a block was removed
       this.currentScore.increase(5);
   }
}
