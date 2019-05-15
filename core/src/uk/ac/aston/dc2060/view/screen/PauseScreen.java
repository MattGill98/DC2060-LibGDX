package uk.ac.aston.dc2060.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import static uk.ac.aston.dc2060.TowerDefenceGame.GUI_SKIN;

public class PauseScreen extends CustomScreen {

    public PauseScreen() {
        super(new Stage());

        // Create title
        Label title = new Label("Paused", GUI_SKIN);
        title.setX((Gdx.graphics.getWidth() - title.getWidth()) / 2f);
        title.setY((Gdx.graphics.getHeight() - title.getHeight()) / 2f);
        getStage().addActor(title);
    }
}
