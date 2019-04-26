package uk.ac.aston.dc2060.controller;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class PollingStage extends Stage {

    private float deltaLimit;
    private float currentDelta;

    PollingStage(Viewport viewport, float deltaLimit) {
        super(viewport);
        this.deltaLimit = deltaLimit;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        currentDelta += delta * 1000f;
        if (currentDelta > deltaLimit) {
            currentDelta = 0;
            timeout();
        }
    }

    protected abstract void timeout();

}
