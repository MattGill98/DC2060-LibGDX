package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * A tilemap renderer that renders a map from (0,0) world coordinates to the
 * screen with a specified size, maintaining the correct aspect ratio.
 */
public class TowerDefenceMapRenderer extends OrthogonalTiledMapRenderer {

    /**
     * Create a renderer. The map will be scaled up uniformly
     * until one of the dimensions is fulfilled.
     *
     * @param map       the tilemap to render.
     * @param mapWidth  the width of the map to draw.
     * @param mapHeight the height of the map to draw.
     */
    private TowerDefenceMapRenderer(TiledMap map, int mapWidth, int mapHeight) {
        super(map, getMapScale(map, mapWidth, mapHeight));
        // Configure camera
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        setView(camera);
    }

    /**
     * Create a renderer. The map will be scaled up uniformly
     * until the map is the correct width.
     *
     * @param map the tilemap to render.
     * @param mapWidth the width of the map to draw.
     */
    public TowerDefenceMapRenderer(TiledMap map, int mapWidth) {
        this(map, mapWidth, -1);
    }

    /**
     * Used for finding a size ratio that fulfils either the
     * correct map height or width, preferring the lower one.
     *
     * @param map the tile map to model.
     * @param mapWidth the requested width of the map.
     * @param mapHeight the requested height of the map.
     * @return the correct ratio for scaling the tilemap to.
     */
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
