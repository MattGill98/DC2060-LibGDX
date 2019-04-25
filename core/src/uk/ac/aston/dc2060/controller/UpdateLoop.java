package uk.ac.aston.dc2060.controller;

import java.util.HashSet;
import java.util.Set;

public class UpdateLoop {

    private final Set<TimedObject> timers;

    public UpdateLoop() {
        this.timers = new HashSet<>();
    }

    public void update(float delta) {
        timers.forEach(timer -> timer.update(delta));
    }

    public void register(PolledController controller) {
        timers.add(new TimedObject(controller));
    }

    private static class TimedObject {

        private final PolledController controller;

        private float currentDelta;

        private TimedObject(PolledController controller) {
            this.controller = controller;
        }

        private void update(float delta) {
            currentDelta += delta * 1000f;
            if (currentDelta > controller.getUpdateFrequencyMs()) {
                currentDelta = 0;
                controller.update();
            }
        }
    }
}
