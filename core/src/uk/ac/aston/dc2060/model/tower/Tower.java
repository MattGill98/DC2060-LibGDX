package uk.ac.aston.dc2060.model.tower;

import uk.ac.aston.dc2060.model.DrawableActor;
import uk.ac.aston.dc2060.model.TimedEvent;
import uk.ac.aston.dc2060.model.enemy.Enemy;
import uk.ac.aston.dc2060.model.tower.aiming.TowerAimingStrategy;

import java.util.Collection;
import java.util.HashSet;

/**
 * A class modelling a placed tower.
 */
public abstract class Tower extends DrawableActor {

    protected boolean enabled;
    protected TowerAimingStrategy aimingStrategy;
    private Collection<TimedEvent> timers;

    public Tower(float x, float y) {
        super(x, y);
        this.timers = new HashSet<>();
    }

    public Tower() {
        this(-1, -1);
    }

    protected void addTimedEvent(TimedEvent event) {
        timers.add(event);
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
                Enemy target = aimingStrategy.getTarget(this);
                if (target != null) {
                    setRotation((float) Math.toDegrees(Math.atan2(target.getY() - getY(), target.getX() - getX())) - 90);
                }
            }
            timers.forEach(timer -> timer.test(delta));
        }
        super.act(delta);
    }

    public abstract Tower clone();
}
