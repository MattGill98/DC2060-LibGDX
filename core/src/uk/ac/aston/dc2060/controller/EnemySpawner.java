package uk.ac.aston.dc2060.controller;

import uk.ac.aston.dc2060.model.Enemy;

import java.util.Collection;

public class EnemySpawner implements PolledController {

    private final Collection<Enemy> enemies;
    private final Enemy blueprint;

    private int counter;

    EnemySpawner(Collection<Enemy> enemies, Enemy blueprint) {
        this.enemies = enemies;
        this.blueprint = blueprint;
    }

    @Override
    public void update() {
        if (counter++ % 4 == 0) {
            enemies.add(blueprint.clone());
        }
    }

    @Override
    public int getUpdateFrequencyMs() {
        return 1000;
    }
}
