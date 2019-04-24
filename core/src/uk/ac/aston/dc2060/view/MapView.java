package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import uk.ac.aston.dc2060.model.Actor;

public class MapView {

    private final int mapX;
    private final int mapY;
    private final int tileWidth;
    private final int tileHeight;

    private final SpriteBatch spriteBatch;
    private final OrthogonalTiledMapRenderer renderer;

    public MapView(TiledMap map, int x, int y, int mapHeight, int mapWidth) {
        this.mapX = x;
        this.mapY = y;
        this.spriteBatch = new SpriteBatch();
        this.tileWidth = mapWidth / (Integer) map.getProperties().get("width");
        this.tileHeight = mapHeight / (Integer) map.getProperties().get("height");
        this.renderer = new OrthogonalTiledMapRenderer(map, tileWidth / (float) (int) (Integer) map.getProperties().get("tilewidth"));
        // Configure camera
        OrthographicCamera camera = new OrthographicCamera(tileWidth, tileHeight);
        camera.setToOrtho(false);
        camera.translate(-x, -y);
        camera.update();
        this.renderer.setView(camera);
    }

    public void begin() {
        renderer.render();
        spriteBatch.begin();
    }

    public void render(Iterable<? extends Actor> actors) {
        actors.forEach(this::render);
    }

    public void render(Actor actor) {
        spriteBatch.draw(actor.getTextureRegion(), mapX + (actor.getX() * tileWidth), mapY + (actor.getY() * tileHeight), tileWidth, tileHeight);
    }

    public void end() {
        spriteBatch.end();
    }

    public void dispose() {
        spriteBatch.dispose();
        renderer.dispose();
    }
}
