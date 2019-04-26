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
        screenCoords.x = mapSize + (worldCoords.x * tileSize);
        screenCoords.y = (worldCoords.y * tileSize);
        return screenCoords;
    }


}
