package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TerrainView extends ApplicationAdapter {

    private final OrthographicCamera camera;
    private final OrthogonalTiledMapRenderer renderer;

    public TerrainView(TiledMap map, int mapWidth, int mapHeight) {
        int originalMapWidth = (Integer) map.getProperties().get("width") * (Integer) map.getProperties().get("tilewidth");
        int originalMapHeight = (Integer) map.getProperties().get("height") * (Integer) map.getProperties().get("tileheight");
        double widthScale = mapWidth / (double) originalMapWidth;
        double heightScale = mapHeight / (double) originalMapHeight;
        // Configure renderer
        if (widthScale < 0 || heightScale < 1) {
            this.renderer = new OrthogonalTiledMapRenderer(map, (float) Math.max(widthScale, heightScale));
        } else {
            this.renderer = new OrthogonalTiledMapRenderer(map, (float) Math.min(widthScale, heightScale));
        }
        // Configure camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        renderer.setView(camera);
    }

    public TerrainView(TiledMap map, int mapWidth) {
        this(map, mapWidth, -1);
    }

    @Override
    public void render() {
        renderer.render();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
