package uk.aston.dc2060.model.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import uk.aston.dc2060.controller.enemy.EnemySpawner;
import uk.aston.dc2060.model.tiles.TileID;
import uk.aston.dc2060.model.tower.Tower;

public class GameStage extends Stage {

    private final TiledMap tiledMap;

    private final EnemySpawner enemySpawner;

    private int score;
    private int lives;

    public GameStage(TiledMap tiledMap, int worldWidth, int worldHeight) {
        super(new FitViewport(worldWidth, worldHeight));
        this.tiledMap = tiledMap;
        this.enemySpawner = new EnemySpawner(tiledMap.getTileSets(), this::addActor);
        this.lives = 20;
        this.score = 0;
        addAction(enemySpawner);
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int score) {
        this.score += score;
    }

    public int getRound() {
        return enemySpawner.getRound();
    }

    public int getLives() {
        return lives;
    }

    public void decreaseLives(int lives) {
        this.lives -= lives;
    }

    /**
     * Bounds a 2D vector to a point inside the map.
     *
     * @param worldCoords the world coordinates to bound.
     * @return the bound coordinates.
     * @see #isPlaceableCoordinate(Vector2)
     */
    private Vector2 limitToMapCoordinates(Vector2 worldCoords) {
        worldCoords.x = Math.max(0, Math.min(getWidth() - 2, worldCoords.x));
        worldCoords.y = Math.max(0, Math.min(getHeight() - 1, worldCoords.y));
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
        MapLayer ground = tiledMap.getLayers().get("Ground");
        if (ground == null) {
            throw new IllegalStateException("'Ground' layer was not found in the tilemap.");
        }
        for (Actor tower : getActors()) {
            if (tower instanceof Tower) {
                if ((int) tower.getX() == (int) worldCoords.x && (int) tower.getY() == (int) worldCoords.y) {
                    return false;
                }
            }
        }
        return TileID.DIRT.getID() != ((TiledMapTileLayer) ground).getCell((int) worldCoords.x, (int) worldCoords.y).getTile().getId();
    }
}
