package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

/**
 * A view for rendering the grid.
 */
public class GridView extends ApplicationAdapter {

    private final int virtualWidth;
    private final int virtualHeight;

    private final ShapeRenderer renderer;

    private boolean enabled;

    /**
     * Create a view for rendering the grid.
     *
     * @param camera the camera to use for viewing the grid.
     * @param virtualWidth the width in world space of the grid to draw.
     * @param virtualHeight the height in world space of the grid to draw.
     */
    public GridView(Camera camera, int virtualWidth, int virtualHeight) {
        this.virtualWidth = virtualWidth;
        this.virtualHeight = virtualHeight;
        this.enabled = true;

        // Configure renderer
        this.renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(camera.combined);
    }

    /**
     * @param enabled whether the grid rendering should be enabled.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void render() {
        if (enabled) {
            renderer.begin(Line);
            for (int x = 0; x < virtualWidth; x++) {
                renderer.line(x, 0, x, virtualHeight);
            }
            for (float y = 0; y < virtualHeight; y++) {
                renderer.line(0, y, virtualWidth, y);
            }
            renderer.end();
        }
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
