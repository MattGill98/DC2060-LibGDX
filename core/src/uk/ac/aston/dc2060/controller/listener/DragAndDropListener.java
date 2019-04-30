package uk.ac.aston.dc2060.controller.listener;

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
            placedTower = new Tower(icon, gameStage.getEnemies());
            gameStage.addActor(placedTower);
        }

        // Map the object to the tile under the cursor
        placedTower.setX((int) event.getStageX());
        placedTower.setY((int) event.getStageY());

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
