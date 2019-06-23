package uk.me.mattgill.gdx.view;

import com.github.czyzby.lml.parser.action.ActionContainer;
import com.github.czyzby.lml.util.LmlApplicationListener;

public class ViewActions implements ActionContainer {

    private final LmlApplicationListener app;

    public ViewActions(LmlApplicationListener app) {
        this.app = app;
    }

}
