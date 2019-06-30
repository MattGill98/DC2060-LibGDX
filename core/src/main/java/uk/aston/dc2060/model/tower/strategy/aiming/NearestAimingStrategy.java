package uk.aston.dc2060.model.tower.strategy.aiming;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import uk.aston.dc2060.model.enemy.Enemy;
import uk.aston.dc2060.model.tower.Tower;

/**
 * The action used to aim at the nearest enemy.
 */
public class NearestAimingStrategy extends AimingStrategy {

    public NearestAimingStrategy(Tower tower) {
        super(tower);
    }

    @Override
    public Enemy select() {
        Float distanceToNearest = null;
        Enemy nearestEnemy = null;
        Array.ArrayIterator<Actor> iterator = new Array.ArrayIterator<>(tower.getStage().getActors());
        while (iterator.hasNext()) {
            Actor actor = iterator.next();
            if (actor instanceof Enemy) {
                Enemy enemy = (Enemy) actor;
                if (enemy.isVisible()) {
                    float distance = new Vector2(tower.getX(), tower.getY()).dst(enemy.getX(), enemy.getY());
                    if (distance < tower.getMaxRange() && (distanceToNearest == null || distance < distanceToNearest)) {
                        nearestEnemy = enemy;
                        distanceToNearest = distance;
                    }
                }
            }
        }
        return nearestEnemy;
    }
}
