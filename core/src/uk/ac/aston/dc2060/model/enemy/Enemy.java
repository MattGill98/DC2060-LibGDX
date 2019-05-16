package uk.ac.aston.dc2060.model.enemy;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import uk.ac.aston.dc2060.model.Disposable;
import uk.ac.aston.dc2060.model.DrawableActor;
import uk.ac.aston.dc2060.model.TileID;
import uk.ac.aston.dc2060.model.health.HealthBar;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static uk.ac.aston.dc2060.TowerDefenceGame.*;

/**
 * A class modelling an enemy.
 */
public abstract class Enemy extends DrawableActor implements Disposable {

    private TextureRegion texture;

    private float speed;

    private final int enemyValue;

    private HealthBar healthBar;

    /**
     * Create an enemy object.
     *
     * @param tileSet the tileset to fetch the enemy texture from.
     * @param tileID the ID of the tile in the tileset to use for the enemy texture.
     * @param speed   the number of tiles per second the enemy should move.
     * @param enemyValue   the amount to remove from the endpoint health when this enemy completes it's route.
     */
    Enemy(TileID tileID, float speed, int enemyValue) {
        this.texture = TILE_MAP.getTileSets().getTile(tileID.getID()).getTextureRegion();
        this.speed = speed;
        this.enemyValue = enemyValue;
        this.healthBar = new HealthBar();
        planRoute();
    }

    @Override
    public void draw(Batch batch) {
        if (isVisible()) {
            batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
            healthBar.draw(batch);
        }
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

        // Find the enemy route
        List<GridPoint2> enemyRoute = new ArrayList<>(EnemyRoute.ROUTE);

        // Start the enemy on the route
        GridPoint2 startingPoint = enemyRoute.get(0);
        setX(startingPoint.x);
        setY(startingPoint.y);

        // Plan the rest of the route
        assert enemyRoute.size() > 2;
        SequenceAction movementSteps = new SequenceAction();
        GridPoint2 previousPoint = enemyRoute.get(0);
        for (int i = 1; i < enemyRoute.size(); i++) {
            GridPoint2 nextPoint = enemyRoute.get(i);

            float distance = previousPoint.dst(nextPoint);
            double angle = Math.toDegrees(Math.atan2(nextPoint.y - previousPoint.y, nextPoint.x - previousPoint.x));

            ParallelAction nextAction = new ParallelAction();
            nextAction.addAction(rotateTo((float) angle));
            nextAction.addAction(moveTo(nextPoint.x, nextPoint.y, distance / speed));
            movementSteps.addAction(nextAction);
            previousPoint = nextPoint;
        }
        movementSteps.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                dispose(false);
                return true;
            }
        });
        addAction(movementSteps);
    }

    @Override
    public void dispose() {
        dispose(true);
    }

    public void dispose(boolean killed) {
        if (killed) {
            getStage().increaseScore(1);
        } else {
            getStage().decreaseEndpointHealth(enemyValue);
        }
        removeActor(this).act(-1);
    }
}
