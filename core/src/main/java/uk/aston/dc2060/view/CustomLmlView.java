package uk.aston.dc2060.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.czyzby.lml.parser.impl.AbstractLmlView;

/**
 * A custom screen.
 */
public class CustomLmlView extends AbstractLmlView {

    private String viewId;

    private boolean paused;

    CustomLmlView(String viewId, float worldWidth, float worldHeight) {
        super(new Stage(new FitViewport(worldWidth, worldHeight)));
        this.viewId = viewId;
    }

    CustomLmlView(String viewId, Stage stage) {
        super(stage);
        this.viewId = viewId;
    }

    CustomLmlView(String viewId) {
        this(viewId, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public FileHandle getTemplateFile() {
        return Gdx.files.internal("views/" + viewId + ".lml");
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void render() {
        Stage stage = getStage();
        if (!paused) {
            stage.act();
        }
        stage.draw();
    }

    @Override
    public void render(float delta) {
        Stage stage = getStage();
        if (!paused) {
            stage.act(delta);
        }
        stage.draw();
    }

    @Override
    public String getViewId() {
        return viewId;
    }
}
