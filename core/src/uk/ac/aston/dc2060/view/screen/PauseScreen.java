package uk.ac.aston.dc2060.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static uk.ac.aston.dc2060.TowerDefenceGame.GUI_SKIN;

public class PauseScreen extends CustomScreen {

    public PauseScreen() {
        super(new Stage());

        // Create title
        TextArea title = new TextArea("Paused", GUI_SKIN);
        title.setX((Gdx.graphics.getWidth() - title.getWidth()) / 2f);
        title.setY((Gdx.graphics.getHeight() - title.getHeight()) / 2f);
        getStage().addActor(title);
    }

    private static ClickListener createListener(Runnable task) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                task.run();
                super.clicked(event, x, y);
            }
        };
    }
}
