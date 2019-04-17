package uk.ac.aston.dc2060.view;

import uk.ac.aston.dc2060.model.Actor;

public class Sprite {

    private TileID tileID;
    private Actor actor;

    public Sprite(TileID tileID) {
        this.tileID = tileID;
        this.actor = new Actor();
    }

    public Actor getActor() {
        return actor;
    }

    public TileID getTileID() {
        return tileID;
    }
}
