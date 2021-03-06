package uk.aston.dc2060.model.tower.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.scenes.scene2d.Actor;
import uk.aston.dc2060.model.tiles.TileID;

/**
 * The turret model of a tower.
 */
public class TowerTurret extends Actor {

    private final TextureRegion texture;

    private float offsetY = 0.17f;

    public TowerTurret(TiledMapTileSets tileSet, TileID tileID) {
        texture = tileSet.getTile(tileID.getID()).getTextureRegion();
        setOrigin(0.5f, 0.5f - offsetY);
        setY(offsetY);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color parentColor = getParent().getColor();
        Color color = getColor();
        batch.setColor(parentColor.r * color.r, parentColor.g * color.g, parentColor.b * color.b, color.a * parentAlpha);
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), 1, 1, getScaleX(), getScaleY(), getRotation());
    }
}
