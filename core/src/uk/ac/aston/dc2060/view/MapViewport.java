package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MapViewport extends FitViewport {

    private final float tileSize;

    public MapViewport(float mapWidth, float mapHeight, float tileSize) {
        super(mapWidth, mapHeight);
        this.tileSize = tileSize;
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.update();
        setCamera(camera);
    }

    @Override
    public Vector2 project(Vector2 worldCoords) {
        Vector2 screenCoords = new Vector2();
        screenCoords.x = worldCoords.x * tileSize;
        screenCoords.y = worldCoords.y * tileSize;
        return screenCoords;
    }
}
