package uk.ac.aston.dc2060.controller;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import uk.ac.aston.dc2060.controller.listener.DragAndDropListener;
import uk.ac.aston.dc2060.model.Enemy;
import uk.ac.aston.dc2060.model.EnemyRoute;
import uk.ac.aston.dc2060.model.TileID;
import uk.ac.aston.dc2060.model.tower.TowerIcon;

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
    private List<TowerIcon> icons;

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

    @Override
    public void addActor(Actor actor) {
        if (actor instanceof Enemy) {
            enemies.add((Enemy) actor);
        }
        if (actor instanceof TowerIcon) {
            TowerIcon newTowerIcon = (TowerIcon) actor;
            newTowerIcon.addListener(new DragAndDropListener(newTowerIcon, this));
            newTowerIcon.setY(icons.size());
            newTowerIcon.setX(mapWidth);
            icons.add(newTowerIcon);
        }
        super.addActor(actor);
    }

    @Override
    protected void timeout() {
        enemies.forEach(Enemy::move);
        if (enemySpawnCounter++ % enemySpawnInterval == 0) {
            Enemy newEnemy = new Enemy(tileSet, TileID.SOLDIER, EnemyRoute.ROUTE);
            addActor(newEnemy);
        }
    }
}
