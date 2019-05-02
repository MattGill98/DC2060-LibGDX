package uk.ac.aston.dc2060.model.enemy;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.math.GridPoint2;
import uk.ac.aston.dc2060.model.DrawableActor;
import uk.ac.aston.dc2060.model.TileID;
import uk.ac.aston.dc2060.model.health.HealthBar;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.removeActor;

/**
 * A class modelling an enemy.
 */
public abstract class Enemy extends DrawableActor {

    private TextureRegion texture;

    private final List<GridPoint2> route;
    private float speed;

    private HealthBar healthBar;

    /**
     * Create an enemy object.
     *
     * @param tileSet the tileset to fetch the enemy texture from.
     * @param tileID the ID of the tile in the tileset to use for the enemy texture.
     * @param speed   the number of tiles per second the enemy should move.
     */
    Enemy(TiledMapTileSets tileSet, TileID tileID, float speed) {
        this.texture = tileSet.getTile(tileID.getID()).getTextureRegion();
        this.route = new ArrayList<>(EnemyRoute.ROUTE);
        setX(route.get(0).x);
        setY(route.get(0).y);
        this.speed = speed;
        this.healthBar = new HealthBar();
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        healthBar.draw(batch);
    }

    @Override
    public void act(float delta) {
        if (isVisible()) {
            move(delta);
            healthBar.setX(getX());
            healthBar.setY(getY() + 0.85f);
        }
        super.act(delta);
    }

    /**
     * Moves the enemy along the route, or removes it from the
     * scene if the route is finished.
     *
     * @param delta the time passed between frames
     */
    private void move(float delta) {
        if (!isVisible()) {
            return;
        }
        if (!route.isEmpty()) {

            // Find out information about the next point
            GridPoint2 nextPoint = route.get(0);
            double distX = nextPoint.x - getX();
            double distY = nextPoint.y - getY();
            double angle = Math.toDegrees(Math.atan2(distY, distX));
            double distance = Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
            double travelDistance = delta * speed;

            // Since the direction of motion is always at right angles,
            // we can cheat this maths step by assuming the enemy can move
            // the entire travel distance on both axes
            if (distance < travelDistance) {
                route.remove(0);
                setX(nextPoint.x);
                setY(nextPoint.y);

                // Then move the remaining distance
                move((float) (delta * (1 - (distance / travelDistance))));
                return;
            }
            float moveX = (float) (travelDistance * Math.signum(distX));
            float moveY = (float) (travelDistance * Math.signum(distY));
            setX(getX() + moveX);
            setY(getY() + moveY);

            // Set the rotation
            this.setRotation((float) angle);
        } else {
            removeActor(this).act(-1);
        }
    }

}
