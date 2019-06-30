package uk.aston.dc2060.controller.enemy;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import uk.aston.dc2060.model.enemy.Enemy;

import java.util.Iterator;

/**
 * An action used to wait until all enemies have been removed from the map. Used at the end of each round.
 */
public class WaitUntilNoEnemiesAction extends Action {

    private final Array<Actor> actors;

    public WaitUntilNoEnemiesAction(Array<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public boolean act(float delta) {
        Iterator<Actor> iterator = new Array.ArrayIterator<>(actors);
        while (iterator.hasNext()) {
            if (iterator.next() instanceof Enemy) {
                return false;
            }
        }
        return true;
    }
}
