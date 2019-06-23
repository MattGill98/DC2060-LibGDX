package uk.me.mattgill.gdx.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.github.czyzby.lml.annotation.LmlActor;

public class GameLmlView extends CustomLmlView {

    @LmlActor("pauseWindow")
    private Window window;

    public GameLmlView() {
        super("game");
        getStage().addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (event.getKeyCode() == Input.Keys.ESCAPE) {
                    if (window.isVisible()) {
                        resume();
                    } else {
                        pause();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void pause() {
        window.setVisible(true);
    }

    @Override
    public void resume() {
        window.setVisible(false);
    }

    @Override
    public void clear() {
        resume();
    }
}
