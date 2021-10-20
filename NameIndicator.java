/*
 * Classname : NameIndicator
 * @author Ilan Bogomolnik
 */

import biuoop.DrawSurface;

/**
 * this class show the name of the level.
 */
public class NameIndicator implements Sprite {
    private String levelName;

    /**
     * builder.
     * @param name the name of the level
     */
    public NameIndicator(String name) {
        this.levelName = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(450 , 25 , "Level Name: " + levelName , 25);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
