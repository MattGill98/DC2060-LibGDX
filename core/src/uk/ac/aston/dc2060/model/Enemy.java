package uk.ac.aston.dc2060.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;

public class Enemy extends Actor {

    public Enemy(TextureRegion textureRegion, int x, int y) {
        super(textureRegion, x, y);
    }

    public Enemy(TiledMapTileSets tileSet, TileID tileID, int x, int y) {
        super(tileSet, tileID, x, y);
    }

    public Enemy clone() {
        return new Enemy(textureRegion, x, y);
    }
}
