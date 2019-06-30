package uk.aston.dc2060.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import uk.aston.dc2060.model.tower.Tower;

/**
 * The context menu showing what you can do with a tower.
 */
public class ContextMenu {

    private Actor contextMenu;
    private Actor deleteButton;

    private Tower target;

    ContextMenu(Actor contextMenu, Actor deleteButton) {
        this.contextMenu = contextMenu;
        this.deleteButton = deleteButton;
        configureActions();
    }

    private void configureActions() {
        deleteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                target.remove();
            }
        });
    }

    public void setTarget(Tower target) {
        this.target = target;
    }

    public Actor getRoot() {
        return contextMenu;
    }

    public Actor getDeleteButton() {
        return deleteButton;
    }
}
