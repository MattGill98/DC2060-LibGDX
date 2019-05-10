package uk.ac.aston.dc2060.model.enemy;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import uk.ac.aston.dc2060.model.Disposable;
import uk.ac.aston.dc2060.model.DrawableActor;
import uk.ac.aston.dc2060.model.TileID;
import uk.ac.aston.dc2060.model.health.HealthBar;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.removeActor;

/**
 * A class modelling an enemy.
 */
public abstract class Enemy extends DrawableActor implements Disposable {

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
        this.speed = speed;
        this.healthBar = new HealthBar();
        planRoute();
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        healthBar.draw(batch);
    }

    @Override
    public void act(float delta) {
        if (getHealthBar().getHealth() <= 0.0001f) {
            dispose();
        }
        if (isVisible()) {
            healthBar.setX(getX());
            healthBar.setY(getY() + 0.85f);
        }
        super.act(delta);
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    private void planRoute() {
        SequenceAction movementSteps = new SequenceAction();
        route.forEach(point -> {
            movementSteps.addAction(Actions.moveTo(point.x, point.y, 2f));
        });
        addAction(movementSteps);
    }

    @Override
    public void dispose() {
        dispose(false);
    }

    public void dispose(boolean completed) {
        if (!completed) {
            getStage().increaseScore(1);
        }
        removeActor(this).act(-1);
    }
}
