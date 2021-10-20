/*
 * Classname : KeyPressStoppableAnimation
 * @author Ilan Bogomolnik
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this class in charge of all the possibilities to stop the game.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * builder.
     * @param s the key board
     * @param k the key that stops the loop
     * @param ani the animation loop
     */
    public KeyPressStoppableAnimation(KeyboardSensor s , String k , Animation ani) {
        this.key = k;
        this.sensor = s;
        this.animation = ani;
        this.isAlreadyPressed = true;
        stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (sensor.isPressed(key) && !isAlreadyPressed) {
            stop = true;
            isAlreadyPressed = true;
        } else {
            isAlreadyPressed = false;
        }
        animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
