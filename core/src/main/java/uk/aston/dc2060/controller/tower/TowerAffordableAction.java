package uk.aston.dc2060.controller.tower;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import uk.aston.dc2060.model.stage.GameStage;
import uk.aston.dc2060.model.tower.Tower;

public class TowerAffordableAction extends Action {

    @Override
    public boolean act(float delta) {
        Tower tower = getTarget();
        Color color = tower.getColor();
        if (tower.getCost().get() > ((GameStage) tower.getStage()).getScore()) {
            tower.setTouchable(Touchable.disabled);
            color.r = 1;
            color.g = 0.4f;
            color.b = 0.4f;
        } else {
            tower.setTouchable(Touchable.enabled);
            color.r = 1;
            color.g = 1;
            color.b = 0.8f;
        }
        return false;
    }

    @Override
    public Tower getTarget() {
        return (Tower) super.getTarget();
    }

    @Override
    public void setTarget(Actor target) {
        if (!(target instanceof Tower)) {
            throw new IllegalArgumentException("Unable to add a tower action to an object of type: " + target.getClass());
        }
        super.setTarget(target);
    }
}
