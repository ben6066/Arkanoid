import java.util.ArrayList;
import java.util.List;

/**.
 * This class defined the first level 'Direct Hit'
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        //Declaration of the velocities list
        List<Velocity> ballsVelocities = new ArrayList<Velocity>();

        //Ball's Velocity
        ballsVelocities.add(new Velocity(0, -4));

        return ballsVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Sprite background = new Background(java.awt.Color.BLACK, "Direct Hit");
        return background;
    }

    @Override
    public List<Block> blocks() {
        //Declaration of the blocks list
        List<Block> blocksList = new ArrayList<Block>();
        //Setting the block
        Rectangle r = new Rectangle(new Point(380, 100), 40, 40);
        blocksList.add(new Block(r, java.awt.Color.RED));
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
