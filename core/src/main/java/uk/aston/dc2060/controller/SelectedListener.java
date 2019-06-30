package uk.aston.dc2060.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Listener to see if an object has been selected.
 *
 * @param <T> the type of the actor to check.
 */
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

    /**
     * Method called when the object is selected for the first time.
     *
     * @param event the event that triggered the selection.
     * @param x     the X coordinate of the cursor.
     * @param y     the Y coordinate of the cursor.
     */
    protected abstract void selected(InputEvent event, float x, float y);

    /**
     * Method called when the object is deselected.
     *
     * @param event the event that triggered the deselection.
     * @param x     the X coordinate of the cursor.
     * @param y     the Y coordinate of the cursor.
     */
    protected abstract void deselected(InputEvent event, float x, float y);
}
