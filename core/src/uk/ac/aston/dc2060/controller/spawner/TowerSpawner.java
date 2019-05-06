package uk.ac.aston.dc2060.controller.spawner;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import uk.ac.aston.dc2060.controller.TowerDefenceStage;
import uk.ac.aston.dc2060.model.tower.Tower;
import uk.ac.aston.dc2060.model.tower.aiming.NearestEnemyStrategy;

public class TowerSpawner extends ClickListener {

    private TowerDefenceStage gameStage;

    private Tower placedTower;

    public TowerSpawner(TowerDefenceStage gameStage) {
        this.gameStage = gameStage;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        // If the drag has started, create a tower to be placed
        if (placedTower == null && event.getTarget() instanceof Tower) {
            Tower selectedTower = (Tower) event.getTarget();
            if (!selectedTower.isEnabled()) {
                placedTower = selectedTower.clone();
                placedTower.setAlpha(0.65f);
                event.getStage().addActor(placedTower);
            }
        }

        if (placedTower != null) {
            // Map the object to the most appropriate tile near the cursor
            moveToNearestAvailableTile(new Vector2(event.getStageX(), event.getStageY()));
        }

        super.touchDragged(event, x, y, pointer);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        // Place the tower, and reset the temporary object
        if (placedTower != null) {
            placedTower.setAimingStrategy(new NearestEnemyStrategy(((TowerDefenceStage) event.getStage()).getEnemies()));
            placedTower.setAlpha(1f);
            placedTower.setEnabled(true);
            placedTower = null;
        }

        super.touchUp(event, x, y, pointer, button);
    }

    /**
     * Moves the placed tower to the available tile closest to the passed point.
     *
     * @param worldCoords the point to move the tower close to.
     */
    private void moveToNearestAvailableTile(Vector2 worldCoords) {
        if (placedTower != null && worldCoords != null) {
            if (gameStage.isPlaceableCoordinate(worldCoords)) {
                placedTower.setX((int) worldCoords.x);
                placedTower.setY((int) worldCoords.y);
            } else if (gameStage.isPlaceableCoordinate(new Vector2(worldCoords.x, placedTower.getY()))) {
                // If the immediate square isn't placeable, try moving just down the X axis
                placedTower.setX((int) worldCoords.x);
            } else if (gameStage.isPlaceableCoordinate(new Vector2(placedTower.getX(), worldCoords.y))) {
                // If the still not placeable, try moving just down the Y axis
                placedTower.setY((int) worldCoords.y);
            } else {
                // If all else fails try all adjacent squares
                for (int yOffset = 0; yOffset < 3; yOffset++) {
                    for (int xOffset = 0; xOffset < 3; xOffset++) {
                        Vector2 adjacentCoordinate = new Vector2(worldCoords.x + xOffset, worldCoords.y + xOffset);
                        if (gameStage.isPlaceableCoordinate(adjacentCoordinate)) {
                            placedTower.setX(adjacentCoordinate.x);
                            placedTower.setY(adjacentCoordinate.y);
                            return;
                        }
                    }
                }
            }
        }
    }

}
