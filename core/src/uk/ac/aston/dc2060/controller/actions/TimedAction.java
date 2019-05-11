package uk.ac.aston.dc2060.controller.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

/**
 * An event that runs after a specified time limit.
 */
public class TimedAction extends Action {

    /**
     * The window delta after which to run the task.
     */
    private float deltaLimit;

    /**
     * Whether the action should repeat after executing once.
     */
    private boolean repeat;

    /**
     * A running total of the window delta time elapsed.
     */
    private float currentDelta;

    /**
     * The task to run after the specified time limit elapses.
     */
    private Runnable task;

    /**
     * Create a stage with a recurring task.
     *
     * @param deltaLimit the limit in ms after which to run the task.
     * @param repeat whether to repeat the task after first execution;
     * @param task       the task to run after the specified time limit elapses.
     */
    public TimedAction(float deltaLimit, boolean repeat, Runnable task) {
        this.deltaLimit = deltaLimit;
        this.repeat = repeat;
        this.task = task;
    }

    @Override
    public boolean act(float delta) {
        currentDelta += delta * 1000f;
        if (currentDelta > deltaLimit) {
            currentDelta = 0;
            task.run();
            return !repeat;
        }
        return false;
    }

}
