package uk.ac.aston.dc2060.model.tower;

import com.badlogic.gdx.math.Vector2;
import uk.ac.aston.dc2060.model.DrawableActor;
import uk.ac.aston.dc2060.model.Enemy;

import java.util.Collection;

/**
 * A class modelling a placed tower.
 */
public class Tower extends DrawableActor {

    private Collection<Enemy> enemies;

    private boolean enabled;

    /**
     * Create a tower from a given icon.
     *
     * @param icon the icon to use to create the tower.
     */
    public Tower(TowerIcon icon, Collection<Enemy> enemies) {
        super(icon.getTexture());
        this.enemies = enemies;
        setAlpha(0.65f);
    }

    @Override
    public void act(float delta) {
        if (enabled) {
            pointTowardNearestEnemy();
        }
        super.act(delta);
    }

    /**
     * Rotates the tower until it is pointing the nearest enemy.
     */
    private void pointTowardNearestEnemy() {
        Float distanceToNearest = null;
        Enemy nearestEnemy = null;
        for (Enemy enemy : enemies) {
            float distance = new Vector2(getX(), getY()).dst(enemy.getX(), enemy.getY());
            if (distanceToNearest == null || distance < distanceToNearest) {
                nearestEnemy = enemy;
                distanceToNearest = distance;
            }
        }
        if (nearestEnemy != null) {
            setRotation((float) Math.toDegrees(Math.atan2(nearestEnemy.getY() - getY(), nearestEnemy.getX() - getX())) - 90);
        }
    }

    /**
     * Called when the tower has been placed.
     */
    public void realise() {
        setAlpha(1f);
        enabled = true;
    }
}
