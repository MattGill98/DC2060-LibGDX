package uk.ac.aston.dc2060.model.tower;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import uk.ac.aston.dc2060.model.DrawableActor;
import uk.ac.aston.dc2060.model.TileID;

/**
 * A class modelling a tower in the GUI.
 */
public class TowerIcon extends DrawableActor {

    /**
     * Create a tower icon from a given texture.
     *
     * @param texture the texture to use for the icon.
     */
    private TowerIcon(TextureRegion texture) {
        super(texture);
    }

    /**
     * Create a tower icon from a given texture.
     *
     * @param tileSet the tileset to take the texture from.
     * @param tileID  the ID of the tile to take the texture from.
     */
    public TowerIcon(TiledMapTileSets tileSet, TileID tileID) {
        this(tileSet.getTile(tileID.getID()).getTextureRegion());
    }

}
