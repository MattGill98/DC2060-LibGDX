package uk.ac.aston.dc2060.model.tower;

import com.badlogic.gdx.scenes.scene2d.Action;
import uk.ac.aston.dc2060.model.DrawableActor;

/**
 * A class modelling a placed tower.
 */
public class Tower extends DrawableActor {

    /**
     * Create a tower from a given icon.
     *
     * @param icon the icon to use to create the tower.
     */
    public Tower(TowerIcon icon) {
        super(icon.getTexture());
        setAlpha(0.65f);
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                rotateBy(1);
                return false;
            }
        });
    }

    /**
     * Called when the tower has been placed.
     */
    public void realise() {
        setAlpha(1f);
    }
}
