package uk.aston.dc2060.view;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.github.czyzby.lml.annotation.LmlAction;
import com.github.czyzby.lml.parser.action.ActionContainer;
import uk.aston.dc2060.GdxApplication;

public class ViewActions implements ActionContainer {

    private final GdxApplication app;

    public ViewActions(GdxApplication app) {
        this.app = app;
    }

    public void pause() {
        app.setView(PauseLmlView.class);
    }

    public void resume() {
        app.setView(GameLmlView.class);
    }

    public void finishGame() {
        app.setView(SummaryLmlView.class);
    }

    @LmlAction("startGame")
    public void startGame() {
        app.setView(GameLmlView.class);
    }

    @LmlAction("quitGame")
    public void quitGame() {
        app.setView(MenuLmlView.class, new Action() {
            @Override
            public boolean act(float delta) {
                app.clearView(GameLmlView.class);
                app.initiateView(new GameLmlView(ViewActions.this));
                return true;
            }
        });
    }

}
