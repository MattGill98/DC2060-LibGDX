package uk.ac.aston.dc2060.model.tower;

import uk.ac.aston.dc2060.model.DrawableActor;
import uk.ac.aston.dc2060.model.tower.aiming.TowerAimingStrategy;
import uk.ac.aston.dc2060.model.tower.state.TowerState;

/**
 * A class modelling a placed tower.
 */
public abstract class Tower extends DrawableActor {

    protected boolean placed;
    private TowerAimingStrategy aimingStrategy;

    public Tower(float x, float y) {
        super(x, y);
        setState(TowerState.ICON);
    }

    public Tower() {
        this(-1, -1);
    }

    public void setState(TowerState state) {
        if (state == null) {
            return;
        }
        switch (state) {
            case ICON:
                break;
            case DRAGGING:
                setAlpha(0.65f);
                break;
            case PLACED:
                setAlpha(1f);
                placed = true;
                break;
            default:
                throw new IllegalArgumentException("Unrecognised state: " + state.name());
        }
    }

    public boolean isPlaced() {
        return placed;
    }

    public final void setAimingStrategy(TowerAimingStrategy strategy) {
        this.aimingStrategy = strategy;
    }

    @Override
    public final void act(float delta) {
        if (placed) {
            if (aimingStrategy != null) {
                aimingStrategy.aim(this);
            }
        }
        super.act(delta);
    }

    public abstract Tower clone();
}
