package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

public class GridView extends ApplicationAdapter {

    private final float tileSize;
    private final ShapeRenderer renderer;

    private boolean enabled;

    public GridView(float tileSize) {
        this.tileSize = tileSize;
        this.renderer = new ShapeRenderer();
        this.enabled = true;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void render() {
        if (enabled) {
            float screenWidth = Gdx.graphics.getWidth() * 2;
            float screenHeight = Gdx.graphics.getHeight() * 2;

            renderer.begin(Line);
            for (float x = 0; x < screenWidth; x += tileSize) {
                renderer.line(x, 0, x, screenHeight);
            }
            for (float y = 0; y < screenHeight; y += tileSize) {
                renderer.line(0, y, screenWidth, y);
            }
            renderer.end();
        }
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
