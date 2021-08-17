/**
 * a BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 */

/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public class BallRemover implements HitListener {
    //BallRemover's fields
    private GameLevel game;
    private Counter remainingBalls;

    /**.
     * Instantiates a BallRemover
     * @param game - a Game
     * @param ballsRemained - number of balls remained
     */
    public BallRemover(GameLevel game, Counter ballsRemained) {
        this.game = game;
        this.remainingBalls = ballsRemained;
    }

    /**.
     * This method is called whenever the beingHit object is hit.
     * Balls that hit the death region should be removed.
     * The hitter parameter is the hitting Ball.
     * @param beingHit - the block being hit
     * @param hitter - the hitting ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        /*
         * Case hitting the death region's block:
         * Decrease the number of remaining balls by one and remove the ball
         */
        if (beingHit.getCollisionRectangle().getTopEdge().start().getY() == 600) {
            this.remainingBalls.decrease(1);
            hitter.removeFromGame(this.game);
        }
    }
}
