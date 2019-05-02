package uk.ac.aston.dc2060.model.tower;

import uk.ac.aston.dc2060.model.DrawableActor;
import uk.ac.aston.dc2060.model.tower.strategy.TowerStrategy;

/**
 * A class modelling a placed tower.
 */
public abstract class Tower extends DrawableActor {

    private TowerStrategy strategy;

    public final void setStrategy(TowerStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public final void act(float delta) {
        if (strategy != null) {
            strategy.act(this);
        }
        super.act(delta);
    }
}
