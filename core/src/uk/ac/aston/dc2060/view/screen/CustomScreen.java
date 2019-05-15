package uk.ac.aston.dc2060.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class CustomScreen implements Screen {

    private Stage stage;

    private boolean visible;
    private boolean paused;

    CustomScreen(Stage stage) {
        this.stage = stage;
    }

    CustomScreen() {
        this(new Stage());
    }

    Stage getStage() {
        return stage;
    }

    public void onKeyPress(int keycode, Runnable task) {
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int code) {
                if (code == keycode) {
                    task.run();
                }
                return false;
            }
        });
    }

    @Override
    public final void render(float delta) {
        if (!isPaused()) {
            stage.act(delta);
        }
        if (isVisible()) {
            doRender();
        }
    }

    public void grabInput() {
        Gdx.input.setInputProcessor(getStage());
    }

    public void doRender() {
        stage.draw();
    }

    boolean isVisible() {
        return visible;
    }

    @Override
    public void hide() {
        this.visible = false;
    }

    @Override
    public void show() {
        this.visible = true;
    }

    boolean isPaused() {
        return paused;
    }

    @Override
    public void pause() {
        this.paused = true;
    }

    @Override
    public void resume() {
        this.paused = false;
    }

    @Override
    public void resize(int width, int height) {
        throw new UnsupportedOperationException("Resizing not supported.");
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
