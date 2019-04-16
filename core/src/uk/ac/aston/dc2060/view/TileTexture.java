package uk.ac.aston.dc2060.view;

import uk.ac.aston.dc2060.model.TileName;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import uk.ac.aston.dc2060.model.TileName;

public class TileTexture extends TextureRegion {
    
    public TileTexture(Texture tileSheet, int tileSize, TileName tile) {
        super(tileSheet, tile.getColumn() * tileSize, tile.getRow() * tileSize, tileSize, tileSize);
    }
    
}
