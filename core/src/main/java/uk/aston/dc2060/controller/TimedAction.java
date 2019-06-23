package uk.aston.dc2060.controller;

import com.badlogic.gdx.scenes.scene2d.Action;

/**
 * An event that runs after a specified time limit.
 */
public abstract class TimedAction extends Action {

    /**
     * The window delta after which to run the task.
     */
    protected float deltaLimit;

    /**
     * A running total of the window delta time elapsed.
     */
    private float currentDelta;

    /**
     * Create a stage with a recurring task.
     *
     * @param deltaLimit the limit in ms after which to run the task.
     */
    public TimedAction(float deltaLimit) {
        this.deltaLimit = deltaLimit;
    }

    @Override
    public final boolean act(float delta) {
        currentDelta += delta * 1000f;
        if (currentDelta > deltaLimit) {
            currentDelta = 0;
            return act();
        }
        return false;
    }

    public abstract boolean act();

}
