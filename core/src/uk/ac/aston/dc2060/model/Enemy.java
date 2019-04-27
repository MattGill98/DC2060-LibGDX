package uk.ac.aston.dc2060.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.removeActor;

public class Enemy extends DrawableActor {

    private final List<GridPoint2> route;

    private Enemy(TextureRegion texture, List<GridPoint2> route) {
        super(texture, route.get(0).x, route.get(0).y);
        this.route = new ArrayList<>(route);
    }

    public Enemy(TiledMapTileSets tileSet, TileID tileID, List<GridPoint2> route) {
        this(tileSet.getTile(tileID.getID()).getTextureRegion(), route);
    }

    /**
     * Moves the enemy along the route.
     */
    public void move() {
        if (!isVisible()) {
            return;
        }
        if (!route.isEmpty()) {
            GridPoint2 nextPoint = route.remove(0);
            this.setX(nextPoint.x);
            this.setY(nextPoint.y);
        } else {
            removeActor(this).act(-1);
        }
    }

}
