package uk.ac.aston.dc2060.controller;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
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

    private final TiledMap tileMap;
    private final int mapWidth;
    private final int mapHeight;

    private Set<Enemy> enemies;
    private List<TowerIcon> icons;

    private int enemySpawnInterval;
    private int enemySpawnCounter;

    /**
     * Create the game stage.
     *
     * @param viewport    the viewport to use in rendering the stage.
     * @param tileMap     the tilemap to fetch textures from.
     * @param mapWidth    the width of the map in world space.
     * @param mapHeight    the height of the map in world space.
     */
    public TowerDefenceStage(Viewport viewport, TiledMap tileMap, int mapWidth, int mapHeight) {
        super(viewport, 1000);
        this.tileMap = tileMap;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
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
        if (enemySpawnCounter++ % enemySpawnInterval == 0) {
            Enemy newEnemy = new Enemy(tileMap.getTileSets(), TileID.SOLDIER, EnemyRoute.ROUTE, 1);
            addActor(newEnemy);
        }
    }

    /**
     * Bounds a 2D vector to a point inside the map.
     *
     * @param worldCoords the world coordinates to bound.
     * @return the bound coordinates.
     * @see #isPlaceableCoordinate(Vector2)
     */
    private Vector2 limitToMapCoordinates(Vector2 worldCoords) {
        worldCoords.x = Math.max(0, Math.min(mapWidth - 1, worldCoords.x));
        worldCoords.y = Math.max(0, Math.min(mapHeight - 1, worldCoords.y));
        return worldCoords;
    }

    /**
     * Bounds a 2D vector to a point inside the map, and then verifies if the tower can be placed on that tile.
     *
     * @param worldCoords the world coordinates to bound.
     * @return the bound coordinates.
     * @see #limitToMapCoordinates(Vector2)
     */
    public boolean isPlaceableCoordinate(Vector2 worldCoords) {
        limitToMapCoordinates(worldCoords);
        MapLayer ground = tileMap.getLayers().get("Ground");
        if (ground == null) {
            throw new IllegalStateException("'Ground' layer was not found in the tilemap.");
        }
        return TileID.DIRT.getID() != ((TiledMapTileLayer) ground).getCell((int) worldCoords.x, (int) worldCoords.y).getTile().getId();
    }
}
