package uk.ac.aston.dc2060.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;

public class Tower extends DrawableActor {

    private Tower(TextureRegion texture) {
        super(texture);
        System.out.println(this);
    }

    public Tower(TiledMapTileSets tileSet, TileID tileID) {
        this(tileSet.getTile(tileID.getID()).getTextureRegion());
    }

}
