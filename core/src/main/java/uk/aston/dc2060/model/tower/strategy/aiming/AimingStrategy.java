package uk.aston.dc2060.model.tower.strategy.aiming;

import com.badlogic.gdx.scenes.scene2d.Action;
import uk.aston.dc2060.model.enemy.Enemy;
import uk.aston.dc2060.model.tower.Tower;

public abstract class AimingStrategy extends Action {

    final Tower tower;

    private Enemy selectedEnemy;

    AimingStrategy(Tower tower) {
        this.tower = tower;
    }

    @Override
    public final boolean act(float delta) {
        selectedEnemy = select();
        if (selectedEnemy != null) {
            tower.setRotation((float) Math.toDegrees(Math.atan2(selectedEnemy.getY() - tower.getY(), selectedEnemy.getX() - tower.getX())) - 90);
        }
        return false;
    }

    public Enemy getSelectedEnemy() {
        return selectedEnemy;
    }

    public abstract Enemy select();
}
