import biuoop.DrawSurface;

/**
* ScoreIndicator which will be in charge of displaying the current score.
*/
public class ScoreIndicator implements Sprite {
    private Counter currentScore;

    /**
     * Instantiates a new ScoreIndicator.
     *
     * @param currentScore - the current score
     */
    public ScoreIndicator(Counter currentScore) {
        this.currentScore = currentScore;
    }

    @Override
    /**
     * The method draws the score on the board
     *
     * @param d - a surface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.fillRectangle(0, 0, 800, 0);
        d.setColor(java.awt.Color.BLACK);
        d.drawText(280, 15, "Score: " + this.currentScore.getValue(), 15);
    }

    @Override
    public void timePassed() {
    }
}
