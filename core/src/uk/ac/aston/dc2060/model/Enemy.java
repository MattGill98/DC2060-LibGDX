package uk.ac.aston.dc2060.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Actor {

    private final List<GridPoint2> route;

    public Enemy(TextureRegion textureRegion, List<GridPoint2> route) {
        super(textureRegion, route.get(0).x, route.get(0).y);
        this.route = new ArrayList<>(route);
    }

    public Enemy(TiledMapTileSets tileSet, TileID tileID, List<GridPoint2> route) {
        super(tileSet, tileID, route.get(0).x, route.get(0).y);
        this.route = new ArrayList<>(route);
    }

    /**
     * Moves the enemy along the route.
     *
     * @return true if the enemy has a square to move to,
     * or false otherwise.
     */
    public boolean move() {
        if (!route.isEmpty()) {
            GridPoint2 nextPoint = route.remove(0);
            this.setX(nextPoint.x);
            this.setY(nextPoint.y);
            return true;
        }
        return false;
    }

    public Enemy clone() {
        return new Enemy(textureRegion, route);
    }
}
