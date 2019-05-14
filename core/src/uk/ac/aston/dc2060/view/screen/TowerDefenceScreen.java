package uk.ac.aston.dc2060.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import uk.ac.aston.dc2060.controller.TowerDefenceStage;
import uk.ac.aston.dc2060.controller.spawner.TowerSpawner;
import uk.ac.aston.dc2060.model.number.Number;
import uk.ac.aston.dc2060.model.tower.BasicTower;
import uk.ac.aston.dc2060.model.tower.DoubleBasicTower;
import uk.ac.aston.dc2060.view.GridView;
import uk.ac.aston.dc2060.view.TowerDefenceMapRenderer;

import static uk.ac.aston.dc2060.TowerDefenceGame.TILE_MAP;

/**
 * A screen which runs the actual game.
 */
public class TowerDefenceScreen implements Screen {

    private boolean enabled;

    private OrthographicCamera camera;
    private TowerDefenceStage gameStage;

    private OrthogonalTiledMapRenderer mapRenderer;
    private GridView grid;

    public TowerDefenceScreen() {
        // Configure the game view
        int virtualMapX = 1;
        int virtualMapY = 0;
        int virtualMapWidth = (Integer) TILE_MAP.getProperties().get("width");
        int virtualMapHeight = (Integer) TILE_MAP.getProperties().get("height");
        int virtualWidth = 2 + virtualMapWidth;
        int virtualHeight = virtualMapHeight;
        this.camera = new OrthographicCamera(virtualWidth, virtualHeight);
        Viewport gameViewport = new FitViewport(virtualWidth, virtualHeight, camera);

        // Configure rendered scene
        this.gameStage = new TowerDefenceStage(gameViewport, virtualMapWidth, virtualMapHeight);
        camera.translate(-virtualMapX, -virtualMapY);
        camera.update();
        this.mapRenderer = new TowerDefenceMapRenderer(camera, virtualMapWidth);
        camera.translate(virtualMapX, virtualMapY);
        camera.update();
        this.grid = new GridView(camera, virtualWidth, virtualHeight);

        // Position camera
        this.camera.translate(-virtualMapX, -virtualMapY);

        // Add GUI icons
        this.gameStage.addActor(new BasicTower(virtualMapWidth, 0, 0.1f, 1000));
        this.gameStage.addActor(new DoubleBasicTower(virtualMapWidth, 1, 0.1f, 500));

        // Add endpoint health
        this.gameStage.addActor(new Number(-virtualMapX, 1, () -> gameStage.getEndpointHealth()));

        // Add game score
        this.gameStage.addActor(new Number(virtualMapWidth, virtualHeight - 1, () -> gameStage.getScore()));

        // Add Tower generator
        this.gameStage.addListener(new TowerSpawner(gameStage));
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
            Gdx.gl.glClearColor(0.5804f, 0.6941f, 0.7059f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

            // Update the camera
            camera.update();

            // Render map
            mapRenderer.render();

            // Render grid
            grid.render();

            // Render actors
            gameStage.act();
            gameStage.draw();
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
