/*
 * Classname : PauseScreen
 * @author Ilan Bogomolnik
 */

import biuoop.DrawSurface;

/**
 * this class making the pause screen display.
 */
public class PauseScreen implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return true;
    }
}
