import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**.
 * This class defined the third level 'Green 3'
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        //Declaration of the velocities list
        List<Velocity> ballsVelocities = new ArrayList<Velocity>();

        //First Ball's Velocity
        ballsVelocities.add(new Velocity(-5, -2));

        //Second's Velocity
        ballsVelocities.add(new Velocity(3, -4));

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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Sprite background = new Background(new Color(34, 139, 34), "Green 3");
        return background;
    }

    @Override
    public List<Block> blocks() {
        //Declaration of the blocks list
        List<Block> blocksList = new ArrayList<Block>();

        //Setting the blocks
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j < 10 - i; j++) {
                if (i == 0) {
                    blocksList.add(new Block(new Rectangle(
                            new Point(730 - j * 50, 100 + (i * 25)), 50, 25), java.awt.Color.GRAY));
                } else if (i == 1) {
                    blocksList.add(new Block(new Rectangle(
                            new Point(730 - j * 50, 100 + (i * 25)), 50, 25), java.awt.Color.RED));
                } else if (i == 2) {
                    blocksList.add(new Block(new Rectangle(
                            new Point(730 - j * 50, 100 + (i * 25)), 50, 25), java.awt.Color.YELLOW));
                } else if (i == 3) {
                    blocksList.add(new Block(new Rectangle(
                            new Point(730 - j * 50, 100 + (i * 25)), 50, 25), java.awt.Color.BLUE));
                } else if (i == 4) {
                    blocksList.add(new Block(new Rectangle(
                            new Point(730 - j * 50, 100 + (i * 25)), 50, 25), java.awt.Color.WHITE));
                }
            }
        }

        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
