package uk.ac.aston.dc2060.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import uk.ac.aston.dc2060.controller.TowerDefenceStage;
import uk.ac.aston.dc2060.model.TileID;
import uk.ac.aston.dc2060.model.tower.TowerIcon;
import uk.ac.aston.dc2060.view.GridView;
import uk.ac.aston.dc2060.view.TowerDefenceMapRenderer;

/**
 * A screen which runs the actual game.
 */
public class TowerDefenceScreen implements Screen {

    private boolean enabled;

    private OrthographicCamera camera;
    private Stage gameStage;

    private OrthogonalTiledMapRenderer mapRenderer;
    private GridView grid;

    public TowerDefenceScreen() {
        // Load tilemap
        TiledMap map = new TmxMapLoader().load("tilemap.tmx");

        // Configure the game view
        int virtualMapWidth = (Integer) map.getProperties().get("width");
        int virtualWidth = 1 + virtualMapWidth;
        int virtualHeight = (Integer) map.getProperties().get("height");
        this.camera = new OrthographicCamera(virtualWidth, virtualHeight);
        Viewport gameViewport = new FitViewport(virtualWidth, virtualHeight, camera);

        // Configure rendered scene
        this.gameStage = new TowerDefenceStage(gameViewport, map, virtualMapWidth, virtualHeight);
        this.mapRenderer = new TowerDefenceMapRenderer(map, camera, virtualMapWidth);
        this.grid = new GridView(camera, virtualWidth, virtualHeight);

        // Add GUI icons
        this.gameStage.addActor(new TowerIcon(map.getTileSets(), TileID.SINGLE_TURRET));
        this.gameStage.addActor(new TowerIcon(map.getTileSets(), TileID.DOUBLE_TURRET));
    }

    @Override
    public void show() {
        enabled = true;
        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void render(float delta) {
        if (enabled) {
            // Clear the window
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            // Update the camera
            camera.update();

            // Render map
            mapRenderer.render();

            // Render actors
            gameStage.act();
            gameStage.draw();

            // Render grid
            grid.render();
        }
    }

    @Override
    public void resize(int width, int height) {
        throw new UnsupportedOperationException("Resizing not supported.");
        //gameViewport.update(width, height);
        //camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        //camera.update();
    }

    @Override
    public void pause() {
        throw new UnsupportedOperationException("Pausing not supported.");
    }

    @Override
    public void resume() {
        throw new UnsupportedOperationException("Resuming not supported.");
    }

    @Override
    public void hide() {
        enabled = false;
    }

    @Override
    public void dispose() {
        grid.dispose();
        gameStage.dispose();
    }
}
