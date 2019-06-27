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
     * The time after which to cancel the task regardless of return result.
     */
    private float maxDelta;

    /**
     * A running total of the window delta time elapsed.
     */
    private float totalDelta;

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
        this.maxDelta = -1;
    }

    /**
     * Create a stage with a recurring task.
     *
     * @param deltaLimit the limit in ms after which to run the task.
     * @param maxDelta the time after which to cancel the task regardless of return result.
     */
    public TimedAction(float deltaLimit, float maxDelta) {
        this.deltaLimit = deltaLimit;
        this.maxDelta = maxDelta;
    }

    @Override
    public final boolean act(float delta) {
        currentDelta += delta * 1000f;
        totalDelta += delta * 1000f;
        boolean result = false;

        // If the current window has expired
        if (currentDelta > deltaLimit) {
            currentDelta = 0;
            result = act();
        }

        // If the maximum delta has expired
        if (maxDelta != -1 && totalDelta > maxDelta) {
            result = true;
        }
        return result;
    }

    /**
     * The action to take every time the delta limit expires.
     * @return whether the task is completed or not.
     */
    public abstract boolean act();

}
