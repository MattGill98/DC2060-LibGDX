package uk.ac.aston.dc2060.controller;

public abstract class LogicController {

    private final float deltaLimit;
    private float currentDelta;

    public LogicController(int deltaMs) {
        this.deltaLimit = deltaMs / 100f;
    }

    public void poll(float delta) {
        currentDelta += delta;
        if (currentDelta > deltaLimit) {
            currentDelta = 0;
            update();
        }
    }

    protected abstract void update();
}
