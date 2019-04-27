package uk.ac.aston.dc2060.controller.listener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import uk.ac.aston.dc2060.controller.GuiStage;
import uk.ac.aston.dc2060.controller.TowerDefenceStage;
import uk.ac.aston.dc2060.model.tower.Tower;
import uk.ac.aston.dc2060.model.tower.TowerIcon;

public class DragAndDropListener extends ClickListener {

    private TowerIcon icon;

    private GuiStage gui;
    private TowerDefenceStage gameStage;

    private Tower placedTower;

    public DragAndDropListener(TowerIcon icon, GuiStage gui, TowerDefenceStage gameStage) {
        this.icon = icon;
        this.gui = gui;
        this.gameStage = gameStage;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        if (placedTower == null) {
            placedTower = new Tower(icon);
            gameStage.addActor(placedTower);
        }
        Vector2 screenCoords = gui.stageToScreenCoordinates(new Vector2(x + icon.getX() - 1, y + icon.getY()));
        Vector2 placeCoords = gameStage.screenToStageCoordinates(screenCoords);
        placedTower.setX(placeCoords.x);
        placedTower.setY(placeCoords.y);
        super.touchDragged(event, x, y, pointer);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        placedTower.realise();
        placedTower = null;
        super.touchUp(event, x, y, pointer, button);
    }
}
