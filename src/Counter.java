/**.
 * Counter is a simple class that is used for counting things
 */
public class Counter {
    private int counter;

    /**.
     * Instantiates a new Counter.
     * @param num - a number
     */
    public Counter(int num) {
        this.counter = num;
    }

    /**.
     * The method adds a number to the current count.
     * @param number - a number to add to the current count.
     */
    void increase(int number) {
        this.counter += number;
    }

    /**.
     * The method subtracts a number from the current count.
     * @param number - a number to subtract from the current count.
     */
    void decrease(int number) {
        this.counter -= number;
    }

    /**.
     * @return - The method returns the current count
     */
    int getValue() {
        return this.counter;
    }
}
