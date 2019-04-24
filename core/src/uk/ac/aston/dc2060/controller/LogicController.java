package uk.ac.aston.dc2060.controller;

import java.util.HashSet;
import java.util.Set;

public abstract class LogicController {

    private static final Set<LogicController> CONTROLLERS = new HashSet<>();

    protected final float deltaLimit;
    protected float currentDelta;

    public LogicController(int deltaMs) {
        this.deltaLimit = deltaMs / 1000f;
        CONTROLLERS.add(this);
    }

    protected abstract void update();

    public static void pollControllers(float delta) {
        CONTROLLERS.forEach(controller -> controller.poll(delta));
    }

    public void poll(float delta) {
        currentDelta += delta;
        if (currentDelta > deltaLimit) {
            currentDelta = 0;
            update();
        }
    }
}
