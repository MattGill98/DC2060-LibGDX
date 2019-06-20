package uk.me.mattgill.gdx.view;

import com.github.czyzby.lml.annotation.LmlAction;
import com.github.czyzby.lml.parser.LmlView;
import com.github.czyzby.lml.parser.action.ActionContainer;
import com.github.czyzby.lml.parser.impl.AbstractLmlView;
import com.github.czyzby.lml.util.LmlApplicationListener;

public class ViewActions implements ActionContainer {

    private final LmlApplicationListener app;

    private AbstractLmlView pausedScreen;

    public ViewActions(LmlApplicationListener app) {
        this.app = app;
    }

    @LmlAction("pause")
    public void pause() {
        app.setView(PauseLmlView.class);
    }

    @LmlAction("quit")
    public void quit() {
        app.setView(MenuLmlView.class);
        if (pausedScreen != null) {
            app.reloadView(pausedScreen);
            pausedScreen.resume();
            pausedScreen = null;
        }
    }

    @LmlAction("play")
    public void play() {
        if (pausedScreen == null) {
            return;
        }
        app.setView(pausedScreen);
        pausedScreen.resume();
        pausedScreen = null;
    }

}
