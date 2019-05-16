package uk.ac.aston.dc2060.controller.spawner;

import com.badlogic.gdx.scenes.scene2d.Stage;
import uk.ac.aston.dc2060.controller.actions.TimedAction;
import uk.ac.aston.dc2060.model.enemy.BasicEnemy;
import uk.ac.aston.dc2060.model.enemy.HeavyEnemy;

public class EnemySpawner extends TimedAction {

    private final Stage stage;

    private int round;

    public EnemySpawner(Stage stage, int round) {
        this.stage = stage;
        super.deltaLimit = 2000;
        super.repeat = true;
        super.task = this::spawn;
    }

    private void spawn() {
        // Random amount of time for next spawn
        float variation = ((float) Math.random() / 2f) - 1;
        this.deltaLimit = 1000 * Math.max(2f + variation - (0.1f * round), 0.1f);

        float randomEnemy = (float) Math.random();
        if (randomEnemy < round * 0.1) {
            stage.addActor(new HeavyEnemy());
        } else {
            stage.addActor(new BasicEnemy());
        }
    }

    public void setRound(int round) {
        this.round = round;
    }
}
