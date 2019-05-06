package uk.ac.aston.dc2060.controller;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import uk.ac.aston.dc2060.model.TimedEvent;

/**
 * A stage that runs a task after a given time period.
 */
public abstract class PollingStage extends Stage {

    private TimedEvent event;

    /**
     * Create a stage with a recurring task.
     *
     * @param viewport   the viewport to use in rendering the stage.
     * @param deltaLimit the limit in ms after which to run the task.
     */
    PollingStage(Viewport viewport, float deltaLimit) {
        super(viewport);
        this.event = new TimedEvent(deltaLimit, this::timeout);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        event.test(delta);
    }

    /**
     * The task to run after each frame when the delta limit has elapsed.
     */
    protected abstract void timeout();

}
