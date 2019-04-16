package uk.ac.aston.dc2060.view;

import uk.ac.aston.dc2060.model.Tile;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileTexture extends TextureRegion {
    
    public TileTexture(Texture tileSheet, int tileSize, Tile tile) {
        super(tileSheet, tile.getColumn() * tileSize, tile.getRow() * tileSize, tileSize, tileSize);
    }
    
}
