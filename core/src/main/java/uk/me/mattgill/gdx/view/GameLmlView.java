package uk.me.mattgill.gdx.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class GameLmlView extends CustomLmlView {

    public GameLmlView(ViewActions viewActions) {
        super("game");
        getStage().addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (event.getKeyCode() == Input.Keys.ESCAPE) {
                    viewActions.pause();
                    return true;
                }
                return false;
            }
        });
    }
}
