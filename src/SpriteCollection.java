import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public class SpriteCollection {
    //SpriteCollection's fields
    private List<Sprite> sprites;
    /**
     * Instantiates a new SpriteCollection.
     */
 public SpriteCollection() {
  //Initialize a sprites list
  this.sprites = new ArrayList<Sprite>();
 }

 /**.
  * The method adds the given sprite to the sprite collection
  * @param s - a sprite to add to the sprite collection
  */
 public void addSprite(Sprite s) {
  this.sprites.add(s);
 }

 /**.
  * The method calls 'timePassed()' method  on all sprites in the sprite collection
  */
 public void notifyAllTimePassed() {
  for (int i = 0; i < this.sprites.size(); i++) {
   this.sprites.get(i).timePassed();
  }
 }

 /**.
  * The method calls 'drawOn(d)' method on all sprites in the sprite collection
  * @param d - a draw surface
  */
 public void drawAllOn(DrawSurface d) {
  for (int i = 0; i < this.sprites.size(); i++) {
   this.sprites.get(i).drawOn(d);
  }
 }

 /**.
  * @return The method returns the sprites list
  */
 public List<Sprite> getSprites() {
  return this.sprites;
 }

 /**.
  * The method updates the sprites list with a given list
  * @param spriteList - the sprites list
  */
 public void updateList(List<Sprite> spriteList) {
     this.sprites = spriteList;
 }
}
