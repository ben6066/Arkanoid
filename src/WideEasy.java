import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**.
 * This class defined the second level 'Wide Easy'
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        //Declaration of the velocities list
        List<Velocity> ballsVelocities = new ArrayList<Velocity>();

        //Ball's Velocities
        int y = -8;
        for (int i = 0; i < 5; i++) {
            ballsVelocities.add(new Velocity(i + 1, y));
            ballsVelocities.add(new Velocity(-1 * (i + 1), y));
            y += 1;
        }
        return ballsVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Sprite background = new Background(new Color(255, 255, 255), "Wide Easy");

        return background;
    }

    @Override
    public List<Block> blocks() {
        //Declaration of the blocks list
        List<Block> blocksList = new ArrayList<Block>();

        //Setting the blocks
        for (int j = 0; j <= 14; j++) {
            if (j == 0 || j == 1) {
                blocksList.add(new Block(new Rectangle(
                        new Point(730 - j * 50.6666667, 215), 51, 25), java.awt.Color.CYAN));
            } else if (j == 2 || j == 3) {
                blocksList.add(new Block(new Rectangle(
                        new Point(730 - j * 50.6666667, 215), 51, 25), java.awt.Color.PINK));
            } else if (j == 4 || j == 5) {
                blocksList.add(new Block(new Rectangle(
                        new Point(730 - j * 50.6666667, 215), 51, 25), java.awt.Color.BLUE));
            } else if (j == 6 || j == 7 || j == 8) {
                blocksList.add(new Block(new Rectangle(
                        new Point(730 - j * 50.6666667, 215), 51, 25), java.awt.Color.GREEN));
            } else if (j == 9 || j == 10) {
                blocksList.add(new Block(new Rectangle(
                        new Point(730 - j * 50.6666667, 215), 51, 25), java.awt.Color.YELLOW));
            } else if (j == 11 || j == 12) {
                blocksList.add(new Block(new Rectangle(
                        new Point(730 - j * 50.6666667, 215), 51, 25), java.awt.Color.ORANGE));
            } else if (j == 13 || j == 14) {
                blocksList.add(new Block(new Rectangle(
                        new Point(730 - j * 50.6666667, 215), 51, 25), java.awt.Color.RED));
            }
        }

        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
