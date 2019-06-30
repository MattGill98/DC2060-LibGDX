package uk.aston.dc2060.model.tower.strategy.firing;

import uk.aston.dc2060.controller.TimedAction;
import uk.aston.dc2060.model.enemy.Enemy;
import uk.aston.dc2060.model.tower.Tower;

/**
 * The action used to shoot an enemy.
 */
public class FiringStrategy extends TimedAction {

    private final Tower tower;

    private Enemy selectedEnemy;

    public FiringStrategy(Tower tower) {
        super(tower.getFireRate());
        this.tower = tower;
    }

    public void selectEnemy(Enemy selectedEnemy) {
        this.selectedEnemy = selectedEnemy;
    }

    @Override
    public boolean act() {
        if (selectedEnemy != null) {
            tower.shoot(selectedEnemy);
        }
        return false;
    }
}
