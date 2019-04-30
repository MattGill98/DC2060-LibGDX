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

    /**
     * Create an enemy object.
     *
     * @param texture the texture to draw for the enemy.
     * @param route   the route the enemy should follow.
     */
    private Enemy(TextureRegion texture, List<GridPoint2> route) {
        super(texture, route.get(0).x, route.get(0).y);
        this.route = new ArrayList<>(route);

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
     */
    public Enemy(TiledMapTileSets tileSet, TileID tileID, List<GridPoint2> route) {
        this(tileSet.getTile(tileID.getID()).getTextureRegion(), route);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        // Draw health bar
        float barWidth = 0.5f * health;
        batch.draw(new Texture(healthBar), getX() + 0.5f - (barWidth / 2f), getY() + 0.85f, barWidth, 0.04f);
    }

    @Override
    public void act(float delta) {

        // Calculate new health bar values
        for (Map.Entry<Float, Color> colorEntry : HEALTH_BAR_COLOR_MAP.entrySet()) {
            if (health <= colorEntry.getKey()) {
                healthBar.setColor(colorEntry.getValue());
                break;
            }
        }
        healthBar.fill();

        super.act(delta);
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
