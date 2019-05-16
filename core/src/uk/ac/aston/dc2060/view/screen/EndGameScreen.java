package uk.ac.aston.dc2060.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import static uk.ac.aston.dc2060.TowerDefenceGame.GUI_SKIN;

public class EndGameScreen extends CustomScreen {

    private Label scoreLabel;

    public EndGameScreen() {
        super(new Stage());

        // Create title
        scoreLabel = new Label("Score: ", GUI_SKIN);
        scoreLabel.setX((Gdx.graphics.getWidth() - scoreLabel.getWidth()) / 2f);
        scoreLabel.setY((Gdx.graphics.getHeight() - scoreLabel.getHeight()) / 2f);
        getStage().addActor(scoreLabel);
    }

    public void setScore(int score) {
        this.scoreLabel.setText("Score: " + Integer.toString(score));
    }
}
