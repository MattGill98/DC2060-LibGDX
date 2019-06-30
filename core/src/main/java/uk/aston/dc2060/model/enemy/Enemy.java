package uk.aston.dc2060.model.enemy;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import uk.aston.dc2060.controller.enemy.RemoveWhenDeadAction;
import uk.aston.dc2060.model.health.HealthBar;
import uk.aston.dc2060.model.stage.GameStage;
import uk.aston.dc2060.model.tiles.TileID;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateTo;

/**
 * A class modelling an enemy.
 */
public abstract class Enemy extends Group {

    private final float speed;

    private final Soldier soldier;
    private final HealthBar healthBar;

    /**
     * Create an enemy object.
     *
     * @param tileSet the tileset to fetch the enemy texture from.
     * @param tileID the ID of the tile in the tileset to use for the enemy texture.
     * @param speed   the number of tiles per second the enemy should move.
     */
    public Enemy(TiledMapTileSets tileSet, TileID tileID, float speed, float health) {
        this.speed = speed;
        this.healthBar = new HealthBar(health);
        addActor(healthBar);
        this.soldier = new Soldier(tileSet, tileID);
        addActor(soldier);
        planRoute();
        addAction(new RemoveWhenDeadAction(this));
    }

    public void modifyHealth(float delta) {
        healthBar.modifyHealth(delta);
    }

    @Override
    public boolean remove() {
        if (isDead()) {
            ((GameStage)getStage()).increaseScore(1);
        } else {
            ((GameStage)getStage()).decreaseLives(1);
        }
        return super.remove();
    }

    public boolean isDead() {
        return healthBar.getHealth() < 0.001f;
    }

    @Override
    public void setRotation(float degrees) {
        soldier.setRotation(degrees);
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
        movementSteps.addAction(Actions.removeActor());
        addAction(movementSteps);
    }
}
