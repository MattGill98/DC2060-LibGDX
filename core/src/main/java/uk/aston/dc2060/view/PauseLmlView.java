package uk.aston.dc2060.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * The screen used to show the pause screen.
 */
public class PauseLmlView extends CustomLmlView {

    public static final String ID = "pause";

    public PauseLmlView(ViewActions viewActions) {
        super(ID);
        getStage().addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (event.getKeyCode() == Input.Keys.ESCAPE) {
                    viewActions.resume();
                    return true;
                }
                return false;
            }
        });
    }
}
