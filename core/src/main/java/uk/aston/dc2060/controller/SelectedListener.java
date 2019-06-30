package uk.aston.dc2060.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public abstract class SelectedListener<T extends Actor> extends ClickListener {

    protected final T target;

    private boolean selected;

    protected SelectedListener(T target) {
        this.target = target;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if (event.getTarget().isDescendantOf(target)) {
            if (!selected) {
                selected = true;
                selected(event, x, y);
            }
        } else if (selected) {
            selected = false;
            deselected(event, x, y);
        }
    }

    protected abstract void selected(InputEvent event, float x, float y);

    protected abstract void deselected(InputEvent event, float x, float y);
}
