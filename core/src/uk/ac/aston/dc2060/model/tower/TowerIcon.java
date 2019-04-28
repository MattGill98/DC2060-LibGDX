package uk.ac.aston.dc2060.model.tower;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import uk.ac.aston.dc2060.model.DrawableActor;
import uk.ac.aston.dc2060.model.TileID;

public class TowerIcon extends DrawableActor {

    TowerIcon(TextureRegion texture) {
        super(texture);
    }

    public TowerIcon(TiledMapTileSets tileSet, TileID tileID) {
        this(tileSet.getTile(tileID.getID()).getTextureRegion());
    }

}
