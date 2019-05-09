package uk.ac.aston.dc2060.model;

/**
 * An event that runs after a specified time limit.
 */
public class TimedEvent {

    /**
     * The window delta after which to run the task.
     */
    private float deltaLimit;

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
     * @param task       the task to run after the specified time limit elapses.
     */
    public TimedEvent(float deltaLimit, Runnable task) {
        this.deltaLimit = deltaLimit;
        this.task = task;
    }

    /**
     * Cause all actors in the scene to act. This will be run as usual every frame.
     *
     * @param delta the time elapsed between frames.
     */
    public void test(float delta) {
        currentDelta += delta * 1000f;
        if (currentDelta > deltaLimit) {
            currentDelta = 0;
            task.run();
        }
    }

}
