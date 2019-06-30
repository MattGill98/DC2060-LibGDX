package uk.aston.dc2060.controller.enemy;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.scenes.scene2d.Actor;
import uk.aston.dc2060.controller.TimedAction;
import uk.aston.dc2060.model.enemy.HeavyEnemy;
import uk.aston.dc2060.model.enemy.SimpleEnemy;

import java.util.Random;
import java.util.function.Consumer;

/**
 * Timed action to spawn enemies on the map. The duration of this action should be 1 round.
 */
public class EnemySpawner extends TimedAction {

    private static final int INITIAL_SPAWN_TIME = 2000;

    private final Random random;

    private final TiledMapTileSets tileSet;

    private final Consumer<Actor> addActorFunction;

    private final int round;

    public EnemySpawner(TiledMapTileSets tileSet, int round, float roundLength, Consumer<Actor> addActorFunction) {
        super(0, roundLength);
        this.random = new Random();
        this.tileSet = tileSet;
        this.addActorFunction = addActorFunction;
        this.round = round;
    }

    @Override
    public boolean act() {
        // Vary the spawn time by 0.5 seconds either way
        float variation = (random.nextFloat() / 2f) - 1;

        // Reconfigure the time to wait before spawning another enemy.
        this.deltaLimit = 1000 * Math.max(0.1f, INITIAL_SPAWN_TIME + variation - (0.1f * round));

        spawnEnemy();
        return false;
    }

    /**
     * Spawn an enemy. In round 1 there is a 0% chance of spawning heavy enemies. In subsequent rounds this chance will increase by 10%.
     */
    private void spawnEnemy() {
        float randomEnemy = (float) Math.random();
        if (randomEnemy < (round - 1) * 0.1) {
            addActorFunction.accept(new HeavyEnemy(tileSet));
        } else {
            addActorFunction.accept(new SimpleEnemy(tileSet));
        }
    }
}
