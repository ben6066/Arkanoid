import java.util.List;

import biuoop.GUI;
import biuoop.KeyboardSensor;

/**.
 * This class is in charge of creating the different levels,
 * and moving from one level to the next.
 */
public class GameFlow {
    private Counter currentScore = new Counter(0);
    /**.
     * The method runs the different levels
     * @param levels - the levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        GameLevel level = null;
        for (LevelInformation levelInfo : levels) {
            //Setting the level
            level = new GameLevel(levelInfo, this.currentScore, gui);
            level.initialize();

            /*
             * Play the level
             * Case no balls left - a 'Game Over' screen will be displayed with the result
             */
            level.playOneTurn();
        }

        /* Case the player cleared all the levels
         * Display a winning screen with the result
         */
            level.getAnimationRunner().run(new KeyPressStoppableAnimation(
                    gui.getKeyboardSensor(), KeyboardSensor.SPACE_KEY, new YouWin(this.currentScore)));
            gui.close();
    }
}