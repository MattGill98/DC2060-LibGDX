package uk.aston.dc2060.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class PauseLmlView extends CustomLmlView {

    public PauseLmlView(ViewActions viewActions) {
        super("pause");
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
