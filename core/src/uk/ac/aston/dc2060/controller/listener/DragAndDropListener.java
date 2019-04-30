package uk.ac.aston.dc2060.controller.listener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import uk.ac.aston.dc2060.controller.TowerDefenceStage;
import uk.ac.aston.dc2060.model.tower.Tower;
import uk.ac.aston.dc2060.model.tower.TowerIcon;

/**
 * A tower icon listener that controls what happens when an icon is dragged.
 */
public class DragAndDropListener extends ClickListener {

    private TowerIcon icon;

    private TowerDefenceStage gameStage;

    private Tower placedTower;

    /**
     * Create a drag and drop listener.
     *
     * @param icon      the icon on which to listen.
     * @param gameStage the game stage to use for handling events.
     */
    public DragAndDropListener(TowerIcon icon, TowerDefenceStage gameStage) {
        this.icon = icon;
        this.gameStage = gameStage;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        // If the drag has started, create a tower to be placed
        if (placedTower == null) {
            placedTower = new Tower(icon);
            gameStage.addActor(placedTower);
        }

        // Map the object to the tile under the cursor
        moveToNearestAvailableTile(new Vector2(event.getStageX(), event.getStageY()));

        super.touchDragged(event, x, y, pointer);
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

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        // Place the tower, and reset the temporary object
        if (placedTower != null) {
            placedTower.realise();
            placedTower = null;
        }

        super.touchUp(event, x, y, pointer, button);
    }
}
