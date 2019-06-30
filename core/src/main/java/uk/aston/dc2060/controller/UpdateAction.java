package uk.aston.dc2060.controller;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.function.Supplier;

/**
 * An action used to update a Label with a certain value.
 */
public class UpdateAction extends Action {

    private final Supplier<Object> supplier;

    public UpdateAction(Supplier<Object> supplier) {
        this.supplier = supplier;
    }

    @Override
    public void setActor(Actor actor) {
        if (!(actor instanceof Label)) {
            throw new IllegalArgumentException("Cannot add UpdateAction to non label.");
        }
        super.setActor(actor);
    }

    @Override
    public boolean act(float delta) {
        Object value = supplier.get();
        ((Label)actor).setText(value.toString());
        return false;
    }
}
