package uk.ac.aston.dc2060.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;

public class Actor {
    private final TextureRegion textureRegion;
    private int x;
    private int y;

    public Actor(TextureRegion textureRegion, int x, int y) {
        this.textureRegion = textureRegion;
        this.x = x;
        this.y = y;
    }

    public Actor(TiledMapTileSets tileSet, TileID tileID, int x, int y) {
        this(tileSet.getTile(tileID.getID()).getTextureRegion(), x, y);
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
