package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

public class GridView {

    private final float tileSize;
    private final ShapeRenderer renderer;

    private boolean enabled;

    public GridView(float tileSize) {
        this.tileSize = tileSize;
        this.renderer = new ShapeRenderer();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void render() {
        if (enabled) {
            renderer.begin(Line);

            // Draw the grid
            for (float x = 0; x < Gdx.graphics.getWidth(); x += tileSize) {
                renderer.line(x, 0, x, Gdx.graphics.getHeight());
            }
            for (float y = 0; y < Gdx.graphics.getHeight(); y += tileSize) {
                renderer.line(0, y, Gdx.graphics.getWidth(), y);
            }

            renderer.end();
        }
    }
}
