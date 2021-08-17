import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**.
 * This class defined the fourth level 'Final Four'
 */
public class FinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        //Declaration of the velocities list
        List<Velocity> ballsVelocities = new ArrayList<Velocity>();

        //First ball Velocity
        ballsVelocities.add(new Velocity(-3, -2));

        //Second ball Velocity
        ballsVelocities.add(new Velocity(3, -2));

        //Third ball Velocity
        ballsVelocities.add(new Velocity(-3, -3));

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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Sprite background = new Background(new Color(20, 123, 200), "Final Four");
        return background;
    }
    @Override
    public List<Block> blocks() {
        //Declaration of the blocks list
        List<Block> blocksList = new ArrayList<Block>();

        //Setting the blocks
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 14; j++) {
                if (i == 0) {
                    blocksList.add(new Block(new Rectangle(
                            new Point(730 - j * 50.6666667, 100 + (i * 25)), 51, 25), java.awt.Color.GRAY));
                } else if (i == 1) {
                    blocksList.add(new Block(new Rectangle(
                            new Point(730 - j * 50.6666667, 100 + (i * 25)), 51, 25), java.awt.Color.RED));
                } else if (i == 2) {
                    blocksList.add(new Block(new Rectangle(
                            new Point(730 - j * 50.6666667, 100 + (i * 25)), 51, 25), java.awt.Color.YELLOW));
                } else if (i == 3) {
                    blocksList.add(new Block(new Rectangle(
                            new Point(730 - j * 50.6666667, 100 + (i * 25)), 51, 25), java.awt.Color.GREEN));
                } else if (i == 4) {
                    blocksList.add(new Block(new Rectangle(
                            new Point(730 - j * 50.6666667, 100 + (i * 25)), 51, 25), java.awt.Color.WHITE));
                } else if (i == 5) {
                    blocksList.add(new Block(new Rectangle(
                            new Point(730 - j * 50.6666667, 100 + (i * 25)), 51, 25), java.awt.Color.PINK));
                } else if (i == 6) {
                    blocksList.add(new Block(new Rectangle(
                            new Point(730 - j * 50.6666667, 100 + (i * 25)), 51, 25), java.awt.Color.CYAN));
                }
            }
        }

        return blocksList;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
