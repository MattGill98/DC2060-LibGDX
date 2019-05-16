package uk.ac.aston.dc2060.model.tower;

import uk.ac.aston.dc2060.model.DrawableActor;
import uk.ac.aston.dc2060.model.enemy.Enemy;
import uk.ac.aston.dc2060.model.tower.aiming.TowerAimingStrategy;

/**
 * A class modelling a placed tower.
 */
public abstract class Tower extends DrawableActor {

    protected boolean enabled;
    protected TowerAimingStrategy aimingStrategy;
    protected float range;

    public Tower(float x, float y, float range) {
        super(x, y);
        this.range = range;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public final void setAimingStrategy(TowerAimingStrategy strategy) {
        this.aimingStrategy = strategy;
    }

    @Override
    public final void act(float delta) {
        if (isEnabled()) {
            if (aimingStrategy != null) {
                Enemy target = aimingStrategy.getTarget(this, range);
                if (target != null) {
                    setRotation((float) Math.toDegrees(Math.atan2(target.getY() - getY(), target.getX() - getX())) - 90);
                }
            }
        }
        super.act(delta);
    }

    public abstract Tower clone();
}
