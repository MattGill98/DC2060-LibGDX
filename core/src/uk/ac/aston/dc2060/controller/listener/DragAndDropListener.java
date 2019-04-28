package uk.ac.aston.dc2060.controller.listener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import uk.ac.aston.dc2060.controller.GuiStage;
import uk.ac.aston.dc2060.controller.TowerDefenceStage;
import uk.ac.aston.dc2060.model.tower.Tower;
import uk.ac.aston.dc2060.model.tower.TowerIcon;

/**
 * A tower icon listener that controls what happens when an icon is dragged.
 */
public class DragAndDropListener extends ClickListener {

    private TowerIcon icon;

    private GuiStage gui;
    private TowerDefenceStage gameStage;

    private Tower placedTower;

    /**
     * Create a drag and drop listener.
     *
     * @param icon      the icon on which to listen.
     * @param gui       the GUI to from which to draw icon coordinates.
     * @param gameStage the game stage to which to map tower coordinates.
     */
    public DragAndDropListener(TowerIcon icon, GuiStage gui, TowerDefenceStage gameStage) {
        this.icon = icon;
        this.gui = gui;
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
        Vector2 screenCoords = gui.stageToScreenCoordinates(new Vector2(x + icon.getX() - 1, y + icon.getY()));
        Vector2 placeCoords = gameStage.screenToStageCoordinates(screenCoords);
        placedTower.setX(placeCoords.x);
        placedTower.setY(placeCoords.y);

        super.touchDragged(event, x, y, pointer);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        // Place the tower, and reset the temporary object
        placedTower.realise();
        placedTower = null;

        super.touchUp(event, x, y, pointer, button);
    }
}
