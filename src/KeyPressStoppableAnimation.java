import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**.
 * KeyPressStoppableAnimation is a decorator-class that will wrap an existing animation
 * and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    //KeyPressStoppableAnimation's fields
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new KeyPressStoppableAnimation.
     * The constructor initializes the keyboard sensor, a key and the animation.
     * @param sensor - keyboard sensor
     * @param key - a key on the keyboard that is pressed
     * @param animation - the animation running
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //Run the animation
        this.animation.doOneFrame(d);

        //Case it's the first time the key is pressed - stop the animation
        if (this.sensor.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
            this.isAlreadyPressed = true;

         //There was a time point after the animation started in which 'key' was not pressed.
        } else if (!this.sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
            this.stop = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}