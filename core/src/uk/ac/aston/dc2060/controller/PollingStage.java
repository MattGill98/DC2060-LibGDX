package uk.ac.aston.dc2060.controller;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * A stage that runs a task after a given time period.
 */
public abstract class PollingStage extends Stage {

    /**
     * The window delta after which to run the task.
     */
    private float deltaLimit;

    /**
     * A running total of the window delta time elapsed.
     */
    private float currentDelta;

    /**
     * Create a stage with a recurring task.
     *
     * @param viewport   the viewport to use in rendering the stage.
     * @param deltaLimit the limit in ms after which to run the task.
     */
    PollingStage(Viewport viewport, float deltaLimit) {
        super(viewport);
        this.deltaLimit = deltaLimit;
    }

    /**
     * Cause all actors in the scene to act. This will be run as usual every frame.
     * @param delta the time elapsed between frames.
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        currentDelta += delta * 1000f;
        if (currentDelta > deltaLimit) {
            currentDelta = 0;
            timeout();
        }
    }

    /**
     * The task to run after each frame when the delta limit has elapsed.
     */
    protected abstract void timeout();

}
