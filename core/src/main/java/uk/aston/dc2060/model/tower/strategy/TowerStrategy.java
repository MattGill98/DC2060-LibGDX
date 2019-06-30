package uk.aston.dc2060.model.tower.strategy;

import com.badlogic.gdx.scenes.scene2d.Action;
import uk.aston.dc2060.model.enemy.Enemy;
import uk.aston.dc2060.model.tower.Tower;
import uk.aston.dc2060.model.tower.strategy.aiming.AimingStrategy;
import uk.aston.dc2060.model.tower.strategy.aiming.NearestAimingStrategy;
import uk.aston.dc2060.model.tower.strategy.firing.FiringStrategy;

/**
 * The action that a tower uses.
 */
public class TowerStrategy extends Action {

    private final AimingStrategy aimingStrategy;
    private final FiringStrategy firingStrategy;

    public TowerStrategy(Tower tower) {
        this.aimingStrategy = new NearestAimingStrategy(tower);
        this.firingStrategy = new FiringStrategy(tower);
    }

    @Override
    public boolean act(float delta) {
        aimingStrategy.act(delta);
        Enemy selectedEnemy = aimingStrategy.getSelectedEnemy();
        firingStrategy.selectEnemy(selectedEnemy);
        firingStrategy.act(delta);
        return false;
    }
}
