package uk.aston.dc2060.model.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.github.czyzby.lml.util.LmlApplicationListener;
import uk.aston.dc2060.controller.tower.TowerAffordableAction;
import uk.aston.dc2060.controller.tower.TowerDragListener;
import uk.aston.dc2060.model.stage.GameStage;

public class TowerManager extends Group {

    public <T extends Tower> TowerManager(GameStage gameStage, Class<T> towerType, TiledMapTileSets tileSet, int x, int y) {
        // Configure dragged tower
        Tower draggedTower = Tower.createTower(towerType, tileSet, x, y);
        draggedTower.getColor().a = 0.65f;
        addActor(draggedTower);

        // Configure tower icon
        Tower towerIcon = Tower.createTower(towerType, tileSet, x, y);
        towerIcon.addAction(new TowerAffordableAction());
        towerIcon.addListener(new TowerDragListener(gameStage, tileSet, draggedTower, towerIcon.getCost()));
        addActor(towerIcon);

        // Configure cost label
        Label costLabel = new Label(towerIcon.getCost().toString(), ((LmlApplicationListener) Gdx.app.getApplicationListener()).getParser().getData().getDefaultSkin());
        Container<Label> costContainer = new Container<>(costLabel);
        costContainer.setTransform(true);
        costContainer.setScale(0.007f);
        costContainer.setX(16.5f);
        costContainer.setY(towerIcon.getY() + 0.5f);
        costContainer.setTouchable(Touchable.disabled);
        costLabel.addAction(Actions.forever(Actions.run(() -> costLabel.setText(towerIcon.getCost().toString()))));
        addActor(costContainer);
    }
}
