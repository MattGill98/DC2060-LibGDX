package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * A viewport for managing screen space translations for map objects.
 */
public class TowerDefenceViewport extends FitViewport {

    private final float tileSize;

    /**
     * Create a viewport for map objects.
     *
     * @param tileSize the size on screen of each tile in the map.
     */
    public TowerDefenceViewport(float tileSize) {
        super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.tileSize = tileSize;
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(true);
        setCamera(camera);
    }

    @Override
    public Vector2 project(Vector2 worldCoords) {
        worldCoords.x = worldCoords.x * tileSize;
        worldCoords.y = worldCoords.y * tileSize;
        return worldCoords;
    }

    @Override
    public Vector2 unproject(Vector2 screenCoords) {
        // Convert them
        screenCoords.x = (int) (screenCoords.x / tileSize);
        screenCoords.y = (int) ((getScreenHeight() - screenCoords.y) / tileSize);
        return screenCoords;
    }
}
