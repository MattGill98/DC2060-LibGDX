package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GridViewport extends FitViewport {

    public GridViewport() {
        super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.update();
        setCamera(camera);
    }

    @Override
    public Vector2 project(Vector2 worldCoords) {
        Vector2 screenCoords = new Vector2();
        screenCoords.x = worldCoords.x * getScreenWidth();
        screenCoords.y = worldCoords.y * getScreenHeight();
        return screenCoords;
    }
}