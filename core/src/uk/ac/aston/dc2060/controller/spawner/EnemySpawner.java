package uk.ac.aston.dc2060.controller.spawner;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import uk.ac.aston.dc2060.controller.actions.TimedAction;

public class EnemySpawner extends TimedAction {

    private final Stage stage;

    private int round;

    public EnemySpawner(Stage stage) {
        this.stage = stage;
        this.round = 1;
        super.deltaLimit = 2000;
        super.repeat = true;
        super.task = this::spawn;
    }

    private void spawn() {
        // Random amount of time for next spawn
        this.deltaLimit = ((float) Math.random() * 2f) + 0.1f;

        float randomEnemy = (float) Math.random();
        if ()
    }

    public void setRound(int round) {
        this.round = round;
    }
}
