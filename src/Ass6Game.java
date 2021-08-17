import java.util.ArrayList;
import java.util.List;

/**.
 * This class starts a game
 */

/**.
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public class Ass6Game {
    /**.
     * The method checks if a string is a digit in the range 1-4 including 1 and 4
     * @param s - a string
     * @return - Case it is, the method returns true, false other
     */
    public static boolean isDigitBetweenOneAndFour(String s) {
        //Case the string's length isn't 1, it's not one of the digits 1-4
        if (s.length() != 1) {
            return false;
        }

        //Case the string is a single character but it's not one of the digits 1-4
        if (s.charAt(0) < '0' || s.charAt(0) > '4') {
            return false;
        }

        return true;
    }

    /**.
     * main method - The method starts a game with four levels
     * When run without arguments, a game start with four levels that run one after the other
     * When run with additional arguments, the arguments should be treated as a list of level numbers to run,
     * in the specified order
     * @param args - array of arguments
     */
    public static void main(String[] args) {
        //Case no arguments passed - start a game with four levels
        GameFlow flow = new GameFlow();
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        if (args.length == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());

            flow.runLevels(levels);

        /*Case 'ant run' without 'Dargs' used - args length is 1
         *Case it's not a digit between 1 and 4 - start a game with four levels
         */
       } else if (args.length == 1 && !isDigitBetweenOneAndFour(args[0])) {
                levels.add(new DirectHit());
                levels.add(new WideEasy());
                levels.add(new Green3());
                levels.add(new FinalFour());

                flow.runLevels(levels);
      //Case arguments passed - filter the levels and ignore the rest of the arguments
       } else {
            int level;

            //Loop through all the arguments
            for (int i = 0; i < args.length; i++) {
                //Case argument isn't one of the digits 1-4 - skip it
                if (!isDigitBetweenOneAndFour(args[i])) {
                    continue;
                }

                //Parse the level to an integer
                level = Integer.parseInt(args[i]);

                //Add the matching level to the levels list
                if (level == 1) {
                    levels.add(new DirectHit());
                } else if (level == 2) {
                    levels.add(new WideEasy());
                } else if (level == 3) {
                    levels.add(new Green3());
                } else if (level == 4) {
                    levels.add(new FinalFour());
                }
            }

            //Case the list isn't empty - run the game with the levels in it
            if (!levels.isEmpty()) {
                flow.runLevels(levels);
            }
        }
    }
}
