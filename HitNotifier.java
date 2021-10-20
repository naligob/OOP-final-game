/*
 * Interface : HitNotifier
 * @author Ilan Bogomolnik
 */

/**
 * this interface adding or removing hit listener.
 */
public interface HitNotifier {
    /**
     * @param hl Add hl as a listener to hit events.
     */
    void addHitListener(HitListener hl);
    /**
     * @param hl Remove hl from the list of listeners to hit events.
     */
    void removeHitListener(HitListener hl);

}
