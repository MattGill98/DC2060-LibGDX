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

public class WelcomeScreen implements Screen {

    private Stage stage;
    private Button playButton;
    private Button exitButton;

    private boolean enabled;

    public WelcomeScreen() {

        // Initialise button textures
        Skin buttonSkin = new Skin();
        buttonSkin.addRegions(new TextureAtlas(Gdx.files.internal("theme/Holo-dark-xhdpi.atlas")));
        BitmapFont font = new BitmapFont(Gdx.files.internal("theme/Roboto-xhdpi.fnt"), false);
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = buttonSkin.getDrawable("btn_default_normal");
        buttonStyle.down = buttonSkin.getDrawable("btn_default_pressed");
        buttonStyle.focused = buttonSkin.getDrawable("btn_default_focused");
        buttonStyle.font = font;

        // Create play button
        playButton = new TextButton("Play", buttonStyle);
        playButton.setX((Gdx.graphics.getWidth() - playButton.getWidth()) / 2f);
        playButton.setY((Gdx.graphics.getHeight() - playButton.getHeight()) / 2f + playButton.getHeight());

        // Create exit button
        exitButton = new TextButton("Exit", buttonStyle);
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
