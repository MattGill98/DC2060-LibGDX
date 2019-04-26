package uk.ac.aston.dc2060.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;

public class Tower extends DrawableActor {

    private Tower(TextureRegion texture) {
        super(texture);
    }

    public Tower(TiledMapTileSets tileSet, TileID tileID) {
        this(tileSet.getTile(tileID.getID()).getTextureRegion());
    }

    public Tower(Tower tower) {
        this(tower.texture);
    }
}
