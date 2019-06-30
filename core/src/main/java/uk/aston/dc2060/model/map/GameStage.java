package uk.aston.dc2060.model.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import uk.aston.dc2060.controller.enemy.EnemySpawner;
import uk.aston.dc2060.controller.enemy.WaitUntilNoEnemiesAction;
import uk.aston.dc2060.model.tiles.TileID;
import uk.aston.dc2060.model.tower.Tower;
import uk.aston.dc2060.view.ContextMenu;

import java.util.Iterator;

public class GameStage extends Stage {

    private final TiledMap tiledMap;

    private int round;
    private int score;
    private int lives;

    private final Runnable roundTask;

    private ContextMenu contextMenu;

    public GameStage(TiledMap tiledMap, int worldWidth, int worldHeight) {
        super(new FitViewport(worldWidth, worldHeight));
        this.tiledMap = tiledMap;
        this.lives = 20;
        this.score = 0;
        this.round = 1;
        roundTask = () -> addAction(
                Actions.sequence(
                        // Wait 3 seconds
                        Actions.delay(3),
                        // Spawn enemies for 30 seconds
                        new EnemySpawner(tiledMap.getTileSets(), round, 30000, this::addActor),
                        // Wait until the enemies disappear
                        new WaitUntilNoEnemiesAction(getActors()),
                        // Increment the round
                        Actions.run(() -> round++),
                        // Re-queue the task.
                        new Action() {
                            @Override
                            public boolean act(float delta) {
                                roundTask.run();
                                return true;
                            }
                        }
                )
        );
        roundTask.run();
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public void setContextMenu(ContextMenu contextMenu) {
        this.contextMenu = contextMenu;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public int getRound() {
        return round;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int score) {
        this.score += score;
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
        Iterator<Actor> iterator = new Array.ArrayIterator<>(getActors());
        while (iterator.hasNext()) {
            Actor tower = iterator.next();
            if (tower instanceof Tower) {
                if ((int) tower.getX() == (int) worldCoords.x && (int) tower.getY() == (int) worldCoords.y) {
                    return false;
                }
            }
        }
        return TileID.DIRT.getID() != ((TiledMapTileLayer) ground).getCell((int) worldCoords.x, (int) worldCoords.y).getTile().getId();
    }
}
