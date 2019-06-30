package uk.aston.dc2060.controller.tower;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import uk.aston.dc2060.controller.SelectedListener;
import uk.aston.dc2060.model.tower.Tower;
import uk.aston.dc2060.view.ContextMenu;

public class TowerSelectedListener extends SelectedListener<Tower> {

    private final ContextMenu contextMenu;

    public TowerSelectedListener(Tower target, ContextMenu contextMenu) {
        super(target);
        this.contextMenu = contextMenu;
    }

    @Override
    protected void selected(InputEvent event, float x, float y) {
        contextMenu.getRoot().setVisible(true);
        contextMenu.setTarget(target);
        target.getColor().a = 0.75f;
    }

    @Override
    protected void deselected(InputEvent event, float x, float y) {
        contextMenu.getRoot().setVisible(false);
        target.getColor().a = 1;
    }
}
