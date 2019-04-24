package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import uk.ac.aston.dc2060.model.Actor;

public class MapView {

    private final float mapX;
    private final float mapY;
    private final float tileSize;

    private final SpriteBatch spriteBatch;
    private final OrthogonalTiledMapRenderer renderer;

    public MapView(TiledMap map, int x, int y, float tileSize) {
        this.mapX = x;
        this.mapY = y;
        this.tileSize = tileSize;

        // Configure renderers
        this.spriteBatch = new SpriteBatch();
        this.renderer = new OrthogonalTiledMapRenderer(map, tileSize / (float) (Integer) map.getProperties().get("tileheight"));

        // Configure camera
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.translate(-x, -y);
        camera.update();
        renderer.setView(camera);
    }

    public void begin() {
        renderer.render();
        spriteBatch.begin();
    }

    public void render(Iterable<? extends Actor> actors) {
        actors.forEach(this::render);
    }

    public void render(Actor actor) {
        spriteBatch.draw(actor.getTextureRegion(), mapX + (actor.getX() * tileSize), mapY + (actor.getY() * tileSize), tileSize, tileSize);
    }

    public void end() {
        spriteBatch.end();
    }

    public void dispose() {
        spriteBatch.dispose();
        renderer.dispose();
    }
}
