package uk.ac.aston.dc2060.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.removeActor;

/**
 * A class modelling an enemy.
 */
public class Enemy extends DrawableActor {

    private final List<GridPoint2> route;

    /**
     * Create an enemy object.
     *
     * @param texture the texture to draw for the enemy.
     * @param route   the route the enemy should follow.
     */
    private Enemy(TextureRegion texture, List<GridPoint2> route) {
        super(texture, route.get(0).x, route.get(0).y);
        this.route = new ArrayList<>(route);
    }

    /**
     * Create an enemy object.
     *
     * @param tileSet the tileset to fetch the enemy texture from.
     * @param tileID the ID of the tile in the tileset to use for the enemy texture.
     * @param route the route the enemy should follow.
     */
    public Enemy(TiledMapTileSets tileSet, TileID tileID, List<GridPoint2> route) {
        this(tileSet.getTile(tileID.getID()).getTextureRegion(), route);
    }

    /**
     * Moves the enemy along the route, or removes it from the
     * scene if the route is finished.
     */
    public void move() {
        if (!isVisible()) {
            return;
        }
        if (!route.isEmpty()) {

            // Get the next point to move to, and angle in degrees to that point
            GridPoint2 nextPoint = route.remove(0);
            double angle = Math.toDegrees(Math.atan2(nextPoint.y - getY(), nextPoint.x - getX()));

            // Set the positions
            this.setRotation((float) angle);
            this.setX(nextPoint.x);
            this.setY(nextPoint.y);
        } else {
            removeActor(this).act(-1);
        }
    }

}
