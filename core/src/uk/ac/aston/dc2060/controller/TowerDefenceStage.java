package uk.ac.aston.dc2060.controller;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import uk.ac.aston.dc2060.TowerDefenceGame;
import uk.ac.aston.dc2060.controller.spawner.EnemySpawner;
import uk.ac.aston.dc2060.model.TileID;
import uk.ac.aston.dc2060.model.enemy.Enemy;
import uk.ac.aston.dc2060.model.tower.Tower;

import java.util.HashSet;
import java.util.Set;

/**
 * A stage that controls the game scene.
 */
public class TowerDefenceStage extends Stage {

    private final int mapWidth;
    private final int mapHeight;

    private int endpointHealth;
    private int score;
    private int round;

    private EnemySpawner spawner;
    private Set<Tower> towers;
    private Set<Enemy> enemies;

    private Runnable endGameTask;

    public TowerDefenceStage(Viewport viewport, int mapWidth, int mapHeight) {
        super(viewport);
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.enemies = new HashSet<>();
        this.towers = new HashSet<>();
        this.endpointHealth = 20;
        this.round = 1;
        this.spawner = new EnemySpawner(this);
        addAction(spawner);
    }

    public void setEndGameTask(Runnable endGameTask) {
        this.endGameTask = endGameTask;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (getEndpointHealth() <= 0) {
            if (endGameTask != null) {
                endGameTask.run();
            }
        }
    }

    public int getScore() {
        return score;
    }

    public int getRound() {
        return round;
    }

    public int getEndpointHealth() {
        return endpointHealth;
    }

    public void increaseScore(int count) {
        this.score += count;
    }

    public void decreaseEndpointHealth(int amount) {
        this.endpointHealth -= amount;
    }

    /**
     * @return a set of the enemies in the stage.
     */
    public Set<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    public void addActor(Actor actor) {
        if (actor instanceof Tower) {
            towers.add((Tower) actor);
        }
        if (actor instanceof Enemy) {
            enemies.add((Enemy) actor);
        }
        super.addActor(actor);
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
     * Verifies that a 2d vector is a point inside the map.
     *
     * @param worldCoords the world coordinates to bound.
     * @return if a point is within the map.
     * @see #limitToMapCoordinates(Vector2)
     */
    public boolean isWithinMap(Vector2 worldCoords) {
        Vector2 unmodifiedCoords = new Vector2(worldCoords);
        return unmodifiedCoords.equals(limitToMapCoordinates(worldCoords));
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
        MapLayer ground = TowerDefenceGame.TILE_MAP.getLayers().get("Ground");
        if (ground == null) {
            throw new IllegalStateException("'Ground' layer was not found in the tilemap.");
        }
        for (Tower tower : towers) {
            if (tower.isEnabled()) {
                if ((int) tower.getX() == (int) worldCoords.x && (int) tower.getY() == (int) worldCoords.y) {
                    return false;
                }
            }
        }
        return TileID.DIRT.getID() != ((TiledMapTileLayer) ground).getCell((int) worldCoords.x, (int) worldCoords.y).getTile().getId();
    }
}
