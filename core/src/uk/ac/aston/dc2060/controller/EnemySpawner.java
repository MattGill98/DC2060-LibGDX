package uk.ac.aston.dc2060.controller;

import uk.ac.aston.dc2060.model.Enemy;

import java.util.Collection;

public class EnemySpawner extends LogicController {

    private final Collection<Enemy> enemies;
    private final Enemy blueprint;

    private boolean alternate;

    public EnemySpawner(Collection<Enemy> enemies, Enemy blueprint) {
        super(1000);
        this.enemies = enemies;
        this.blueprint = blueprint;
    }

    @Override
    protected void update() {
        if (alternate = !alternate) {
            enemies.add(blueprint.clone());
        }
    }
}
