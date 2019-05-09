package uk.ac.aston.dc2060.model.tower.aiming;

import uk.ac.aston.dc2060.model.enemy.Enemy;
import uk.ac.aston.dc2060.model.tower.Tower;

import java.util.Collection;

public abstract class TowerAimingStrategy {

    protected Collection<Enemy> enemies;

    public TowerAimingStrategy(Collection<Enemy> enemies) {
        this.enemies = enemies;
    }

    public abstract Enemy getTarget(Tower tower);

}
