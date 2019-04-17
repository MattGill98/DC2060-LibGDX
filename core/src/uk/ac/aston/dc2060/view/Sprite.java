package uk.ac.aston.dc2060.view;

import uk.ac.aston.dc2060.model.Actor;

public class Sprite {

    private TileID tileID;
    private Actor actor;

    public Sprite(TileID tileID) {
        this.tileID = tileID;
        this.actor = new Actor();
    }

    public Sprite(TileID tileID, int x, int y) {
        this.tileID = tileID;
        this.actor = new Actor();
        this.actor.setX(x);
        this.actor.setY(y);
    }

    public Actor getActor() {
        return actor;
    }

    public TileID getTileID() {
        return tileID;
    }
}
