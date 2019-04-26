package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MapViewport extends Viewport {

    private final float tileSize;

    public MapViewport(float tileSize) {
        this.tileSize = tileSize;
        OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

    @Override
    public Vector2 unproject(Vector2 screenCoords) {
        Vector2 worldCoords = new Vector2();
        worldCoords.x = screenCoords.x / tileSize;
        worldCoords.y = (Gdx.graphics.getHeight() - screenCoords.y) / tileSize;
        return worldCoords;
    }
}
