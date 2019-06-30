package uk.aston.dc2060.controller.tower;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import uk.aston.dc2060.model.stage.GameStage;
import uk.aston.dc2060.model.tower.Tower;

import java.util.concurrent.atomic.AtomicInteger;

public class TowerDragListener extends ClickListener {

    private final GameStage gameStage;

    private final TiledMapTileSets tileSet;

    private final Tower draggedTower;
    private final float draggedTowerResetPositionX;
    private final float draggedTowerResetPositionY;

    private final AtomicInteger towerCost;

    private boolean dragged;

    public TowerDragListener(GameStage gameStage, TiledMapTileSets tileSet, Tower draggedTower, AtomicInteger towerCost) {
        this.gameStage = gameStage;
        this.tileSet = tileSet;
        this.draggedTower = draggedTower;
        this.draggedTowerResetPositionX = draggedTower.getX();
        this.draggedTowerResetPositionY = draggedTower.getY();
        this.towerCost = towerCost;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        super.touchDragged(event, x, y, pointer);
        Vector2 worldCoords = new Vector2(event.getStageX(), event.getStageY());
        moveDraggedTowerToNearestAvailableTile(worldCoords);
        dragged = true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        if (dragged) {
            Tower tower = Tower.createTower(draggedTower.getClass(), tileSet, (int) draggedTower.getX(), (int) draggedTower.getY(), towerCost.get());
            gameStage.increaseScore(-towerCost.getAndAdd(10));
            gameStage.addActor(tower);
            tower.enable();
            draggedTower.setX(draggedTowerResetPositionX);
            draggedTower.setY(draggedTowerResetPositionY);
            dragged = false;
        }
    }

    /**
     * Moves the placed tower to the available tile closest to the passed point.
     *
     * @param worldCoords the point to move the tower close to.
     */
    private void moveDraggedTowerToNearestAvailableTile(Vector2 worldCoords) {
        if (draggedTower != null && worldCoords != null) {
            worldCoords.x = (int) worldCoords.x;
            worldCoords.y = (int) worldCoords.y;
            if (gameStage.isPlaceableCoordinate(worldCoords)) {
                draggedTower.setX((int) worldCoords.x);
                draggedTower.setY((int) worldCoords.y);
            } else if (gameStage.isPlaceableCoordinate(new Vector2(worldCoords.x, draggedTower.getY()))) {
                // If the immediate square isn't placeable, try moving just down the X axis
                draggedTower.setX((int) worldCoords.x);
            } else if (gameStage.isPlaceableCoordinate(new Vector2(draggedTower.getX(), worldCoords.y))) {
                // If still not placeable, try moving just down the Y axis
                draggedTower.setY((int) worldCoords.y);
            } else {
                // If all else fails try all adjacent squares
                for (int yOffset = 0; yOffset < 3; yOffset++) {
                    for (int xOffset = 0; xOffset < 3; xOffset++) {
                        Vector2 adjacentCoordinate = new Vector2(worldCoords.x + xOffset, worldCoords.y + xOffset);
                        if (gameStage.isPlaceableCoordinate(adjacentCoordinate)) {
                            draggedTower.setX(adjacentCoordinate.x);
                            draggedTower.setY(adjacentCoordinate.y);
                            return;
                        }
                    }
                }
            }
        }
    }
}
