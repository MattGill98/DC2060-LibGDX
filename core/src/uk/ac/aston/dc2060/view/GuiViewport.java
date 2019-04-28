package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.math.Vector2;

/**
 * A viewport for managing screen space translations for the GUI.
 */
public class GuiViewport extends TowerDefenceViewport {

    private float mapSize;

    /**
     * Create a viewport for GUI objects.
     *
     * @param tileSize the size on screen of each tile in the map.
     * @param mapSize  the size on screen of the map to render alongside.
     */
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
