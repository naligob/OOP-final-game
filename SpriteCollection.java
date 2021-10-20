import biuoop.DrawSurface;
/*
 * Classname : SpriteCollection
 * @author Ilan Bogomolnik
 */
import java.util.ArrayList;
import java.util.List;

/**
 * this class invoke a sprites list.
 */
public class SpriteCollection {
    private List<Sprite> spirits;

    /**
     * builder.
     */
    public SpriteCollection() {
        spirits = new ArrayList<>();
    }

    /**
     * adding sprite to the spirit list.
     * @param s sprite element
     */
    public void addSprite(Sprite s) {
        spirits.add(s);
    }

    /**
     * running all the sprites elements.
     */
    // call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        List<Sprite> copyList = new ArrayList<>(this.spirits);
        for (Sprite sprite : copyList) {
            sprite.timePassed();
        }
    }

    /**
     * draw all the sprites on surface.
     * @param d surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spirits) {
            sprite.drawOn(d);
        }
    }

    /**
     * removing sprite from the list.
     * @param s the sprite that need to be removed
     */
    public void removeSprite(Sprite s) {
        spirits.remove(s);
    }
}
