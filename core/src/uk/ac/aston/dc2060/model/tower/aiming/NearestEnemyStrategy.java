package uk.ac.aston.dc2060.model.tower.aiming;

import com.badlogic.gdx.math.Vector2;
import uk.ac.aston.dc2060.model.enemy.Enemy;
import uk.ac.aston.dc2060.model.tower.Tower;

import java.util.Collection;

public class NearestEnemyStrategy extends TowerAimingStrategy {

    public NearestEnemyStrategy(Collection<Enemy> enemies) {
        super(enemies);
    }

    @Override
    public void aim(Tower tower) {
        Float distanceToNearest = null;
        Enemy nearestEnemy = null;
        for (Enemy enemy : enemies) {
            float distance = new Vector2(tower.getX(), tower.getY()).dst(enemy.getX(), enemy.getY());
            if (distanceToNearest == null || distance < distanceToNearest) {
                nearestEnemy = enemy;
                distanceToNearest = distance;
            }
        }
        if (nearestEnemy != null) {
            tower.setRotation((float) Math.toDegrees(Math.atan2(nearestEnemy.getY() - tower.getY(), nearestEnemy.getX() - tower.getX())) - 90);
        }
    }
}
