package uk.aston.dc2060.model.tower;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import uk.aston.dc2060.controller.tower.TowerSelectedListener;
import uk.aston.dc2060.model.map.GameStage;
import uk.aston.dc2060.model.tower.strategy.TowerStrategy;

public class TowerManager extends Group {

    private final GameStage gameStage;

    private final Tower draggedTower;

    public <T extends Tower> TowerManager(GameStage gameStage, Class<T> towerType, TiledMapTileSets tileSet, int x, int y) {
        this.gameStage = gameStage;

        // Configure dragged tower
        this.draggedTower = Tower.createTower(towerType, tileSet, x, y);
        draggedTower.getColor().a = 0.65f;
        addActor(draggedTower);

        // Configure tower icon
        Tower towerIcon = Tower.createTower(towerType, tileSet, x, y);
        towerIcon.addListener(new ClickListener(0) {

            private boolean dragged;

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
                    Tower tower = Tower.createTower(towerType, tileSet, (int) draggedTower.getX(), (int) draggedTower.getY());
                    gameStage.addActor(tower);
                    tower.addAction(new TowerStrategy(tower));
                    getStage().addListener(new TowerSelectedListener(tower, ((GameStage) getStage()).getContextMenu()));
                    draggedTower.setX(towerIcon.getX());
                    draggedTower.setY(towerIcon.getY());
                    dragged = false;
                }
            }
        });
        addActor(towerIcon);
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
