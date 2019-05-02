package uk.ac.aston.dc2060.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.removeActor;

/**
 * A class modelling an enemy.
 */
public class Enemy extends DrawableActor {

    /**
     * Map representing below which value the enemy health
     * needs to be to correspond to a particular color.
     */
    private static Map<Float, Color> HEALTH_BAR_COLOR_MAP = new LinkedHashMap<>();

    static {
        HEALTH_BAR_COLOR_MAP.put(0.2f, new Color(1, 0, 0, 1));
        HEALTH_BAR_COLOR_MAP.put(0.5f, new Color(1, 0.9f, 0.3f, 1));
        HEALTH_BAR_COLOR_MAP.put(1f, new Color(0, 1, 0, 1));
    }

    private float health;
    private Pixmap healthBar;

    private final List<GridPoint2> route;
    private float speed;

    /**
     * Create an enemy object.
     *
     * @param texture the texture to draw for the enemy.
     * @param route   the route the enemy should follow.
     * @param speed   the number of tiles per second the enemy should move.
     */
    private Enemy(TextureRegion texture, List<GridPoint2> route, float speed) {
        super(texture, route.get(0).x, route.get(0).y);
        this.route = new ArrayList<>(route);
        this.speed = speed;

        // Setup health variables
        this.health = 1f;
        this.healthBar = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    }

    /**
     * Create an enemy object.
     *
     * @param tileSet the tileset to fetch the enemy texture from.
     * @param tileID the ID of the tile in the tileset to use for the enemy texture.
     * @param route the route the enemy should follow.
     * @param speed   the number of tiles per second the enemy should move.
     */
    public Enemy(TiledMapTileSets tileSet, TileID tileID, List<GridPoint2> route, float speed) {
        this(tileSet.getTile(tileID.getID()).getTextureRegion(), route, speed);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);

        // Draw health bar
        float barWidth = 0.5f * health;
        batch.draw(new Texture(healthBar), getX() + 0.5f - (barWidth / 2f), getY() + 0.85f, barWidth, 0.04f);
    }

    @Override
    public void act(float delta) {
        if (isVisible()) {
            move(delta);
            calculateHealthBar();
        }
        super.act(delta);
    }

    private void calculateHealthBar() {
        for (Map.Entry<Float, Color> colorEntry : HEALTH_BAR_COLOR_MAP.entrySet()) {
            if (health <= colorEntry.getKey()) {
                healthBar.setColor(colorEntry.getValue());
                break;
            }
        }
        healthBar.fill();
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
