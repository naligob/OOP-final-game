/*
 * interface : Animation
 * @author Ilan Bogomolnik
 */
import biuoop.DrawSurface;

/**
 * this interface represent an animation object that running and stopping.
 */
public interface Animation {
    /**
     * the animation for one frame.
     * @param d the surface that the animation run
     */
    void doOneFrame(DrawSurface d);

    /**
     * checking if the animation should stop.
     * @return true/false if we need to stop
     */
    boolean shouldStop();
}
