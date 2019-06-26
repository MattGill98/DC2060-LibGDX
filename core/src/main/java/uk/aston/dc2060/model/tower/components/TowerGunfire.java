package uk.aston.dc2060.model.tower.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.scenes.scene2d.Actor;
import uk.aston.dc2060.model.tiles.TileID;

public class TowerGunfire extends Actor {

    private final TextureRegion texture;

    private static final float OFFSET_Y = 0.9f;

    public TowerGunfire(TiledMapTileSets tileSet, TileID tileID) {
        this(tileSet, tileID, 0);
    }

    public TowerGunfire(TiledMapTileSets tileSet, TileID tileID, float offsetX) {
        texture = tileSet.getTile(tileID.getID()).getTextureRegion();
        setOrigin(0.5f - offsetX, 0.5f - OFFSET_Y);
        setY(OFFSET_Y);
        setX(offsetX);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), 1, 1, getScaleX(), getScaleY(), getRotation());
    }
}
