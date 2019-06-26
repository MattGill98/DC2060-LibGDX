package uk.aston.dc2060.controller.enemy;

import com.badlogic.gdx.scenes.scene2d.actions.RemoveActorAction;
import uk.aston.dc2060.model.enemy.Enemy;

public class RemoveWhenDeadAction extends RemoveActorAction {

    public RemoveWhenDeadAction(Enemy enemy) {
        setTarget(enemy);
    }

    @Override
    public boolean act(float delta) {
        if (target instanceof Enemy && ((Enemy)target).isDead()) {
            return super.act(delta);
        }
        return false;
    }
}
