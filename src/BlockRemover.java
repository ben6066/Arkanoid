/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
   //BlockRemover's fields
   private GameLevel game;
   private Counter remainingBlocks;

   /**.
    * Instantiates a BlockRemover
    * @param game - a Game.
    * @param blocksRemained - amount of blocks remained.
    */
   public BlockRemover(GameLevel game, Counter blocksRemained) {
       this.game = game;
       this.remainingBlocks = blocksRemained;
   }

   /**.
    * This method is called whenever the beingHit object is hit.
    * Blocks that are hit should be removed.
    * The hitter parameter is the hitting Ball.
    * @param beingHit - the block being hit
    * @param hitter - the hitting ball
    */
   public void hitEvent(Block beingHit, Ball hitter) {
       /*
       * Case a hit occured - decrease the number of blocks by one
       * Remove the block
       * Remove the BlockRemover from the listeners list of the collision block
       */
       this.remainingBlocks.decrease(1);
       this.game.hitEvent(beingHit, hitter);
       beingHit.removeHitListener(this);
   }
}
