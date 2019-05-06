package uk.ac.aston.dc2060.controller;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import uk.ac.aston.dc2060.model.enemy.BasicEnemy;
import uk.ac.aston.dc2060.model.enemy.Enemy;

import java.util.HashSet;
import java.util.Set;

/**
 * A stage that controls the game scene.
 */
public class TowerDefenceStage extends PollingStage {

    private final TiledMapTileSets tileSet;

    private Set<Enemy> enemies;

    private int enemySpawnInterval;
    private int enemySpawnCounter;

    /**
     * Create the game stage.
     *
     * @param viewport    the viewport to use in rendering the stage.
     * @param tileSet     the tileset to fetch textures from.
     */
    public TowerDefenceStage(Viewport viewport, TiledMapTileSets tileSet) {
        super(viewport, 1000);
        this.tileSet = tileSet;
        this.enemies = new HashSet<>();
        this.enemySpawnInterval = 4;
    }

    /**
     * @return a set of the enemies in the stage.
     */
    public Set<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    public void addActor(Actor actor) {
        if (actor instanceof Enemy) {
            enemies.add((Enemy) actor);
        }
        super.addActor(actor);
    }

    @Override
    protected void timeout() {
        if (enemySpawnCounter++ % enemySpawnInterval == 0) {
            Enemy newEnemy = new BasicEnemy(tileSet);
            addActor(newEnemy);
        }
    }
}
