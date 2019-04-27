package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.math.Vector2;

public class GuiViewport extends TowerDefenceViewport {

    private float mapSize;

    public GuiViewport(float tileSize, float mapSize) {
        super(tileSize);
        this.mapSize = mapSize;
    }

    @Override
    public Vector2 project(Vector2 worldCoords) {
        super.project(worldCoords);
        worldCoords.x += mapSize;
        return worldCoords;
    }

    @Override
    public Vector2 unproject(Vector2 screenCoords) {
        screenCoords.x -= mapSize;
        super.unproject(screenCoords);
        return screenCoords;
    }
}
