package uk.aston.dc2060.model.enemy;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.scenes.scene2d.Actor;
import uk.aston.dc2060.model.tiles.TileID;

public class Soldier extends Actor {

    private final TextureRegion texture;

    /**
     * Create an soldier object.
     *
     * @param tileSet the tileset to fetch the enemy texture from.
     * @param tileID the ID of the tile in the tileset to use for the enemy texture.
     */
    Soldier(TiledMapTileSets tileSet, TileID tileID) {
        this.texture = tileSet.getTile(tileID.getID()).getTextureRegion();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), 0.5f, 0.5f, 1, 1, 1, 1, getRotation());
    }

}
