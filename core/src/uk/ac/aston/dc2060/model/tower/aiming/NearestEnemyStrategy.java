package uk.ac.aston.dc2060.model.tower.aiming;

import com.badlogic.gdx.math.Vector2;
import uk.ac.aston.dc2060.model.enemy.Enemy;
import uk.ac.aston.dc2060.model.tower.Tower;

import java.util.Collection;

public class NearestEnemyStrategy extends TowerAimingStrategy {

    public NearestEnemyStrategy(Collection<Enemy> enemies) {
        super(enemies);
    }

    public Enemy getTarget(Tower tower) {
        Float distanceToNearest = null;
        Enemy nearestEnemy = null;
        for (Enemy enemy : enemies) {
            if (enemy.isVisible()) {
                float distance = new Vector2(tower.getX(), tower.getY()).dst(enemy.getX(), enemy.getY());
                if (distanceToNearest == null || distance < distanceToNearest) {
                    nearestEnemy = enemy;
                    distanceToNearest = distance;
                }
            }
        }
        return nearestEnemy;
    }
}
