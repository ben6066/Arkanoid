/**
* The HitNotifier interface indicate that objects that implement it send notifications when they are being hit.
*/
public interface HitNotifier {
   /**
   * The method adds a hit listener as a listener to hit events.
   * @param hl - a hit listener to add
   */
   void addHitListener(HitListener hl);
   /**
   * The method removes a hit listener from the list of listeners to hit events.
   * @param hl - a hit listener to remove
   */
   void removeHitListener(HitListener hl);
}
