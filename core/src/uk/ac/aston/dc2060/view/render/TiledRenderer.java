package uk.ac.aston.dc2060.view.render;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import uk.ac.aston.dc2060.view.Sprite;

import java.util.HashSet;
import java.util.Set;

public class TiledRenderer extends ApplicationAdapter {

    private final int tileSize;

    private TiledMap tiledMap;

    private MapRenderer mapRenderer;

    private SpriteBatch spriteRenderer;
    private Set<Sprite> sprites;

    public TiledRenderer(TiledMap tiledMap, int tileSize) {
        this.tiledMap = tiledMap;
        this.tileSize = tileSize;
        this.sprites = new HashSet<>();
    }

    public void setSpriteRenderer(SpriteBatch spriteRenderer) {
        this.spriteRenderer = spriteRenderer;
    }

    public void setMapRenderer(MapRenderer mapRenderer) {
        this.mapRenderer = mapRenderer;
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    @Override
    public void render() {
        super.render();
        if (mapRenderer != null) {
            mapRenderer.render();
        }
        if (spriteRenderer != null) {
            spriteRenderer.begin();
            sprites.forEach(sprite -> spriteRenderer.draw(tiledMap.getTileSets().getTile(sprite.getTileID().getID()).getTextureRegion(), sprite.getActor().getX() * tileSize, sprite.getActor().getY() * tileSize));
            spriteRenderer.end();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if (spriteRenderer != null) {
            spriteRenderer.dispose();
        }
    }
}
