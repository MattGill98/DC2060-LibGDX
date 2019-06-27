package uk.aston.dc2060.controller.enemy;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.scenes.scene2d.Actor;
import uk.aston.dc2060.controller.TimedAction;
import uk.aston.dc2060.model.enemy.HeavyEnemy;
import uk.aston.dc2060.model.enemy.SimpleEnemy;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class EnemySpawner extends TimedAction {

    private final Random random;

    private final TiledMapTileSets tileSet;

    private final Consumer<Actor> addActorFunction;

    private final int round;

    public EnemySpawner(TiledMapTileSets tileSet, int round, float roundLength, Consumer<Actor> addActorFunction) {
        super(2, roundLength);
        this.random = new Random();
        this.tileSet = tileSet;
        this.addActorFunction = addActorFunction;
        this.round = round;
    }

    @Override
    public boolean act() {
        // Random amount of time for next spawn
        float variation = (random.nextFloat() / 2f) - 1;
        this.deltaLimit = 1000 * Math.max(2f + variation - (0.1f * round), 0.1f);

        spawnEnemy();
        return false;
    }

    private void spawnEnemy() {
        float randomEnemy = (float) Math.random();
        if (randomEnemy < round * 0.1) {
            addActorFunction.accept(new HeavyEnemy(tileSet));
        } else {
            addActorFunction.accept(new SimpleEnemy(tileSet));
        }
    }
}
