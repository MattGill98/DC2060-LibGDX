package uk.ac.aston.dc2060.controller;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import uk.ac.aston.dc2060.controller.listener.DragAndDropListener;
import uk.ac.aston.dc2060.model.enemy.BasicEnemy;
import uk.ac.aston.dc2060.model.enemy.Enemy;
import uk.ac.aston.dc2060.model.tower.Tower;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A stage that controls the game scene.
 */
public class TowerDefenceStage extends PollingStage {

    private final TiledMapTileSets tileSet;
    private final int mapWidth;

    private Set<Enemy> enemies;
    private List<Tower> icons;

    private int enemySpawnInterval;
    private int enemySpawnCounter;

    /**
     * Create the game stage.
     *
     * @param viewport    the viewport to use in rendering the stage.
     * @param tileSet     the tileset to fetch textures from.
     * @param mapWidth    the width of the map in world space.
     */
    public TowerDefenceStage(Viewport viewport, TiledMapTileSets tileSet, int mapWidth) {
        super(viewport, 1000);
        this.tileSet = tileSet;
        this.mapWidth = mapWidth;
        this.enemies = new HashSet<>();
        this.icons = new ArrayList<>();
        this.enemySpawnInterval = 4;
    }

    /**
     * @return a set of the enemies in the stage.
     */
    public Set<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * @return the width of the map in world coordinates.
     */
    public int getMapWidth() {
        return mapWidth;
    }

    @Override
    public void addActor(Actor actor) {
        if (actor instanceof Enemy) {
            enemies.add((Enemy) actor);
        }
        if (actor instanceof Tower) {
            Tower tower = (Tower) actor;
            if (!tower.isPlaced()) {
                tower.addListener(new DragAndDropListener(this));
                tower.setY(icons.size());
                tower.setX(mapWidth);
                icons.add(tower);
            }
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
