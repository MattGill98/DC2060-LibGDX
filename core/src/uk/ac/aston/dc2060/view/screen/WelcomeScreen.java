package uk.ac.aston.dc2060.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static uk.ac.aston.dc2060.TowerDefenceGame.GUI_BUTTON_STYLE;

public class WelcomeScreen implements Screen {

    private Stage stage;
    private Button playButton;
    private Button exitButton;

    private boolean enabled;

    public WelcomeScreen() {
        // Create play button
        playButton = new TextButton("Play", GUI_BUTTON_STYLE);
        playButton.setX((Gdx.graphics.getWidth() - playButton.getWidth()) / 2f);
        playButton.setY((Gdx.graphics.getHeight() - playButton.getHeight()) / 2f + playButton.getHeight());

        // Create exit button
        exitButton = new TextButton("Exit", GUI_BUTTON_STYLE);
        exitButton.setX((Gdx.graphics.getWidth() - exitButton.getWidth()) / 2f);
        exitButton.setY((Gdx.graphics.getHeight() - exitButton.getHeight()) / 2f - exitButton.getHeight());

        // Create stage
        this.stage = new Stage();
        this.stage.addActor(playButton);
        this.stage.addActor(exitButton);
    }

    public void onPlayButtonPressed(Runnable task) {
        playButton.addListener(createListener(task));
    }

    public void onExitButtonPressed(Runnable task) {
        exitButton.addListener(createListener(task));
    }

    @Override
    public void show() {
        enabled = true;
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        if (enabled) {
            // Clear the window
            Gdx.gl.glClearColor(0.5804f, 0.6941f, 0.7059f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            stage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        throw new UnsupportedOperationException("Resizing not supported.");
    }

    @Override
    public void pause() {
        throw new UnsupportedOperationException("Pausing not supported.");
    }

    @Override
    public void resume() {
        throw new UnsupportedOperationException("Resuming not supported.");
    }

    @Override
    public void hide() {
        enabled = false;
    }

    @Override
    public void dispose() {
        stage.dispose();
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
