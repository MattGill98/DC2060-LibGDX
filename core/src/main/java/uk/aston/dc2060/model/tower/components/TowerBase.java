package uk.aston.dc2060.model.tower.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.scenes.scene2d.Actor;
import uk.aston.dc2060.model.tiles.TileID;

public class TowerBase extends Actor {

    private final TextureRegion texture;

    public TowerBase(TiledMapTileSets tileSet, TileID tileID) {
        texture = tileSet.getTile(tileID.getID()).getTextureRegion();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, 0, 0, 1, 1);
    }
}
