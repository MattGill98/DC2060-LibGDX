package uk.ac.aston.dc2060;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameTileTexture extends TextureRegion {
    
    public GameTileTexture(Texture tileSheet, int tileSize, GameTile tile) {
        super(tileSheet, tile.getColumn() * tileSize, tile.getRow() * tileSize, tileSize, tileSize);
    }
    
}
