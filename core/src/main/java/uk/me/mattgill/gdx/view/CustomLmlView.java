package uk.me.mattgill.gdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.czyzby.lml.parser.impl.AbstractLmlView;

public class CustomLmlView extends AbstractLmlView {

    private String viewId;

    CustomLmlView(String viewId) {
        super(new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
        this.viewId = viewId;
    }

    @Override
    public FileHandle getTemplateFile() {
        return Gdx.files.internal("views/" + viewId + ".lml");
    }

    @Override
    public String getViewId() {
        return viewId;
    }
}
