package uk.ac.aston.dc2060.model.tower.strategy;

import uk.ac.aston.dc2060.model.enemy.Enemy;
import uk.ac.aston.dc2060.model.tower.Tower;

import java.util.Collection;

public abstract class TowerStrategy {

    protected Collection<Enemy> enemies;

    public TowerStrategy(Collection<Enemy> enemies) {
        this.enemies = enemies;
    }

    public abstract void act(Tower tower);

}
