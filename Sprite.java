/*
 * interface name : Sprite
 * @author Ilan Bogomolnik
 */
import biuoop.DrawSurface;

/**
 * interface sprite.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * add element to the game.
     * @param g game environment
     */
    void addToGame(GameLevel g);
}
