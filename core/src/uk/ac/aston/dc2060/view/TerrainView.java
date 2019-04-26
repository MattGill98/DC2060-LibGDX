package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TerrainView {

    private final OrthogonalTiledMapRenderer renderer;

    public TerrainView(TiledMap map, float tileSize) {
        // Configure renderers
        this.renderer = new OrthogonalTiledMapRenderer(map, tileSize / (float) (Integer) map.getProperties().get("tileheight"));

        // Configure camera
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.update();
        renderer.setView(camera);
    }

    public void render() {
        renderer.render();
    }

    public void dispose() {
        renderer.dispose();
    }
}
