package uk.ac.aston.dc2060.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import uk.ac.aston.dc2060.controller.listener.DragAndDropListener;
import uk.ac.aston.dc2060.model.tower.TowerIcon;

import java.util.ArrayList;
import java.util.List;

public class GuiStage extends Stage {

    private TowerDefenceStage gameStage;
    private List<TowerIcon> towerIcons;

    public GuiStage(Viewport viewport, TowerDefenceStage gameStage) {
        super(viewport);
        this.gameStage = gameStage;
        this.towerIcons = new ArrayList<>();
    }

    @Override
    public void addActor(Actor actor) {
        if (actor instanceof TowerIcon) {
            TowerIcon newTowerIcon = (TowerIcon) actor;
            newTowerIcon.addListener(new DragAndDropListener(newTowerIcon, this, gameStage));
            newTowerIcon.setY(towerIcons.size());
            towerIcons.add(newTowerIcon);
        }
        super.addActor(actor);
    }
}
