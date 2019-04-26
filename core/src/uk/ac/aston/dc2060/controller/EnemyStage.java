package uk.ac.aston.dc2060.controller;

import com.badlogic.gdx.utils.viewport.Viewport;
import uk.ac.aston.dc2060.model.Enemy;

import java.util.HashSet;
import java.util.Set;

public class EnemyStage extends PollingStage {

    private Enemy blueprint;

    private Set<Enemy> enemies;

    private int counter;

    public EnemyStage(Viewport viewport, Enemy blueprint) {
        super(viewport, 1000);
        this.enemies = new HashSet<>();
        this.blueprint = blueprint;
    }

    @Override
    protected void timeout() {
        enemies.forEach(Enemy::move);
        if (counter++ % 4 == 0) {
            Enemy newEnemy = new Enemy(blueprint);
            enemies.add(newEnemy);
            addActor(newEnemy);
        }
    }
}
