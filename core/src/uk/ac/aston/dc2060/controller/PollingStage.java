package uk.ac.aston.dc2060.controller;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * A stage that runs a task after a given time period.
 */
public abstract class PollingStage extends Stage {

    /**
     * Create a stage with a recurring task.
     *
     * @param viewport   the viewport to use in rendering the stage.
     * @param deltaLimit the limit in ms after which to run the task.
     */
    PollingStage(Viewport viewport, float deltaLimit) {
        super(viewport);
        addAction(new TimedAction(deltaLimit, this::timeout));
    }

    /**
     * The task to run after each frame when the delta limit has elapsed.
     */
    protected abstract void timeout();

}
