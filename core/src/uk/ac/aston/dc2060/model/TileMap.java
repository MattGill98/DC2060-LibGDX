package uk.ac.aston.dc2060.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class TileMap {

    private TiledMap drawableMap;

    public TileMap(String fileName) {
        this.drawableMap = new TmxMapLoader().load(fileName);
    }

    public TiledMap getDrawableMap() {
        return drawableMap;
    }

    public TextureRegion getSprite(TileID tileID) {
        return drawableMap.getTileSets().getTile(tileID.getID()).getTextureRegion();
    }
}
