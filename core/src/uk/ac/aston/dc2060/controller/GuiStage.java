package uk.ac.aston.dc2060.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import uk.ac.aston.dc2060.controller.listener.DragAndDropListener;
import uk.ac.aston.dc2060.model.tower.TowerIcon;

import java.util.ArrayList;
import java.util.List;

/**
 * A stage that controls the GUI.
 */
public class GuiStage extends Stage {

    private TowerDefenceStage gameStage;
    private List<TowerIcon> towerIcons;

    /**
     * Create the GUI stage.
     *
     * @param viewport  the viewport to use in rendering the stage.
     * @param gameStage the stage for the game.
     */
    public GuiStage(Viewport viewport, TowerDefenceStage gameStage) {
        super(viewport);
        this.gameStage = gameStage;
        this.towerIcons = new ArrayList<>();
    }

    @Override
    public void addActor(Actor actor) {

        // If an icon is being added, configure it
        if (actor instanceof TowerIcon) {
            TowerIcon newTowerIcon = (TowerIcon) actor;
            newTowerIcon.addListener(new DragAndDropListener(newTowerIcon, this, gameStage));
            newTowerIcon.setY(towerIcons.size());
            towerIcons.add(newTowerIcon);
        }

        super.addActor(actor);
    }
}
