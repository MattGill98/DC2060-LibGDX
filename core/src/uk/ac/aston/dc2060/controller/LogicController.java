package uk.ac.aston.dc2060.controller;

import java.util.HashSet;
import java.util.Set;

public abstract class LogicController {

    private static final Set<LogicController> CONTROLLERS = new HashSet<>();

    private final float deltaLimit;
    private float currentDelta;

    LogicController(int deltaMs) {
        this.deltaLimit = deltaMs / 1000f;
    }

    private void poll(float delta) {
        currentDelta += delta;
        if (currentDelta > deltaLimit) {
            currentDelta = 0;
            update();
        }
    }

    protected abstract void update();

    public static void registerController(LogicController controller) {
        CONTROLLERS.add(controller);
    }

    public static void pollControllers(float delta) {
        CONTROLLERS.forEach(controller -> controller.poll(delta));
    }
}
