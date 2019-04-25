package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import uk.ac.aston.dc2060.model.TileID;

import java.util.ArrayList;
import java.util.List;

public class GuiView {

    private final float tileSize;
    private final float mapSize;

    private final List<TextureRegion> towerElements;

    private SpriteBatch batch;

    public GuiView(float tileSize, float mapSize) {
        this.tileSize = tileSize;
        this.mapSize = mapSize;
        this.towerElements = new ArrayList<>();
        this.batch = new SpriteBatch();
    }

    public void addTowerTile(TiledMapTileSets tileSet, TileID tileID) {
        TextureRegion texture = tileSet.getTile(tileID.getID()).getTextureRegion();
        towerElements.add(texture);
    }

    public void render() {
        batch.begin();
        for (int i = 0; i < towerElements.size(); i++) {
            batch.draw(towerElements.get(i), mapSize, i * tileSize, tileSize, tileSize);
        }
        batch.end();
    }

}
