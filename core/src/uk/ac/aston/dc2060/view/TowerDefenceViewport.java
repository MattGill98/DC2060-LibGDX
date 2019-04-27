package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class TowerDefenceViewport extends FitViewport {

    private final float tileSize;

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
        screenCoords.x = screenCoords.x / tileSize;
        screenCoords.y = screenCoords.y / tileSize;
        return screenCoords;
    }
}
