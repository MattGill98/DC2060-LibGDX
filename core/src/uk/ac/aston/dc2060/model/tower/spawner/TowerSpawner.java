package uk.ac.aston.dc2060.model.tower.spawner;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import uk.ac.aston.dc2060.controller.TowerDefenceStage;
import uk.ac.aston.dc2060.model.tower.Tower;
import uk.ac.aston.dc2060.model.tower.aiming.NearestEnemyStrategy;
import uk.ac.aston.dc2060.model.tower.state.TowerState;

public class TowerSpawner extends ClickListener {

    private Tower placedTower;

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        // If the drag has started, create a tower to be placed
        if (placedTower == null && event.getTarget() instanceof Tower) {
            Tower selectedTower = (Tower) event.getTarget();
            if (!selectedTower.isPlaced()) {
                placedTower = selectedTower.clone();
                placedTower.setState(TowerState.DRAGGING);
                event.getStage().addActor(placedTower);
            }
        }

        if (placedTower != null) {
            // Map the object to the tile under the cursor
            placedTower.setX((int) event.getStageX());
            placedTower.setY((int) event.getStageY());
        }

        super.touchDragged(event, x, y, pointer);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        // Place the tower, and reset the temporary object
        if (placedTower != null) {
            placedTower.setState(TowerState.PLACED);
            placedTower.setAimingStrategy(new NearestEnemyStrategy(((TowerDefenceStage) event.getStage()).getEnemies()));
            placedTower = null;
        }

        super.touchUp(event, x, y, pointer, button);
    }

}
