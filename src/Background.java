import java.awt.Color;

import biuoop.DrawSurface;

/**.
 * This class defines a background of a level
 */

/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public class Background implements Sprite {
    //Background's field
    private Color color;
    private String levelName;
    /**
     * Instantiates a new Background.
     * The constructor initializes the background's color according to the level.
     * @param color - a color
     * @param levelName - the name of the level
     */
    public Background(java.awt.Color color, String levelName) {
        this.color = color;
        this.levelName = levelName;
    }

    /**
     * The method instantiates a new background matching the 'Direct Hit' level.
     * @param d - a surface to draw on
     */
    private void directHitDraw(DrawSurface d) {
        d.setColor(java.awt.Color.BLUE);
        d.drawCircle(400, 120, 60);
        d.drawCircle(400, 120, 80);
        d.drawCircle(400, 120, 100);
        d.drawLine(400, 20, 400, 95);
        d.drawLine(400, 145, 400, 235);
        d.drawLine(285, 120, 375, 120);
        d.drawLine(422, 120, 515, 120);
    }

    /**
     * The method instantiates a new background matching the 'Wide Easy' level.
     * @param d - a surface to draw on
     */
    private void wideEasy(DrawSurface d) {
        d.setColor(java.awt.Color.lightGray);
        d.fillCircle(100, 100, 60);
        d.setColor(java.awt.Color.ORANGE);
        d.fillCircle(100, 100, 50);
        d.setColor(java.awt.Color.YELLOW);
        d.fillCircle(100, 100, 40);

        int x = 0;
        for (int i = 1; i <= 40; i++) {
            d.drawLine(100, 160, 20 + x, 215);
            x += 20;
        }
    }

    /**
     * The method instantiates a new background matching the 'Final Four' level.
     * @param d - a surface to draw on
     */
    private void finalFour(DrawSurface d) {
        d.setColor(java.awt.Color.WHITE);
        int x = 5;
        for (int i = 1; i <= 10; i++) {
            d.drawLine(100 + x, 415, 120 + x, 600);
            x += 5;
        }

        d.setColor(java.awt.Color.WHITE);
        d.fillCircle(100, 400, 20);
        d.fillCircle(120, 415, 25);
        d.fillCircle(135, 395, 25);
        d.setColor(java.awt.Color.lightGray);
        d.fillCircle(145, 420, 20);
        d.fillCircle(150, 395, 30);
        d.setColor(java.awt.Color.WHITE);

        int y = 5;
        for (int i = 1; i <= 10; i++) {
            d.drawLine(600 + y, 520, 520 + y, 600);
            y += 6;
        }

        d.setColor(java.awt.Color.lightGray);
        d.fillCircle(649, 500, 35);
        d.fillCircle(610, 496, 15);
        d.setColor(java.awt.Color.WHITE);
        d.fillCircle(600, 520, 20);
        d.fillCircle(620, 535, 25);
        d.fillCircle(635, 545, 25);
        d.setColor(java.awt.Color.lightGray);
        d.fillCircle(645, 560, 15);
        d.setColor(java.awt.Color.WHITE);
    }

    @Override
    /**.
     * The method draws the background on the surface
     * @param d - draw surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 20, 780, 580);
        if (levelName.contentEquals("Direct Hit")) {
            directHitDraw(d);
        } else if (levelName.contentEquals("Wide Easy")) {
            wideEasy(d);
        } else if (levelName.contentEquals("Final Four")) {
            finalFour(d);
        }
    }
    @Override
    /**.
     * The method notifies the sprite that time has passed
     */
    public void timePassed() {
        //not used
    }
}
