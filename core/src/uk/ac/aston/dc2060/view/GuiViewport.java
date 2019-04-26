package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GuiViewport extends FitViewport {

    private float tileSize;
    private float mapSize;

    public GuiViewport(float tileSize, float mapSize) {
        super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.tileSize = tileSize;
        this.mapSize = mapSize;
    }

    @Override
    public Vector2 project(Vector2 worldCoords) {
        Vector2 screenCoords = new Vector2();
        screenCoords.x = (worldCoords.x * tileSize) + mapSize;
        screenCoords.y = worldCoords.y * tileSize;
        return screenCoords;
    }

    @Override
    public Vector2 unproject(Vector2 screenCoords) {
        Vector2 worldCoords = new Vector2();
        worldCoords.x = (screenCoords.x - mapSize) / tileSize;
        worldCoords.y = (Gdx.graphics.getHeight() - screenCoords.y) / tileSize;
        return worldCoords;
    }
}
