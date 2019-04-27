package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TowerDefenceMapRenderer extends OrthogonalTiledMapRenderer {

    private TowerDefenceMapRenderer(TiledMap map, int mapWidth, int mapHeight) {
        super(map, getMapScale(map, mapWidth, mapHeight));
        // Configure camera
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        setView(camera);
    }

    public TowerDefenceMapRenderer(TiledMap map, int mapWidth) {
        this(map, mapWidth, -1);
    }

    private static float getMapScale(TiledMap map, float mapWidth, float mapHeight) {
        int originalMapWidth = (Integer) map.getProperties().get("width") * (Integer) map.getProperties().get("tilewidth");
        int originalMapHeight = (Integer) map.getProperties().get("height") * (Integer) map.getProperties().get("tileheight");
        double widthScale = mapWidth / (double) originalMapWidth;
        double heightScale = mapHeight / (double) originalMapHeight;
        // Configure renderer
        if (widthScale < 0 || heightScale < 1) {
            return (float) Math.max(widthScale, heightScale);
        }
        return (float) Math.min(widthScale, heightScale);
    }
}
