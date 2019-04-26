package uk.ac.aston.dc2060.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import uk.ac.aston.dc2060.model.Tower;

import java.util.ArrayList;
import java.util.List;

public class GuiStage extends Stage {

    private List<Tower> towers;

    public GuiStage(Viewport viewport) {
        super(viewport);
        this.towers = new ArrayList<>();
    }

    @Override
    public void addActor(Actor actor) {
        if (actor instanceof Tower) {
            Tower newTower = (Tower) actor;
            newTower.setY(towers.size());
            towers.add(newTower);
        }
        super.addActor(actor);
    }
}
