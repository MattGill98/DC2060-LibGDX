package uk.ac.aston.dc2060.model.tower;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import uk.ac.aston.dc2060.model.DrawableActor;
import uk.ac.aston.dc2060.model.TileID;

/**
 * A class modelling a tower in the GUI.
 */
public class TowerIcon extends DrawableActor {

    protected final TiledMapTileSets tileSet;

    /**
     * Create a tower icon from a given texture.
     *
     * @param tileSet the tileset to take the texture from.
     * @param tileID  the ID of the tile to take the texture from.
     */
    public TowerIcon(TiledMapTileSets tileSet, TileID tileID) {
        super(tileSet.getTile(tileID.getID()).getTextureRegion());
        this.tileSet = tileSet;
    }

}
