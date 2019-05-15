package uk.ac.aston.dc2060.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class CustomScreen implements Screen {

    private Stage stage;

    private boolean visible;
    private boolean paused;

    CustomScreen(Stage stage) {
        this.stage = stage;
        this.paused = true;
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
            // Clear the window
            Gdx.gl.glClearColor(0.5804f, 0.6941f, 0.7059f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

            doRender();
        }
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
        Gdx.input.setInputProcessor(stage);
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
