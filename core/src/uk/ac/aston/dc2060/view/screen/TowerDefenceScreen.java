package uk.ac.aston.dc2060.view.screen;

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
public class TowerDefenceScreen extends CustomScreen {

    private OrthogonalTiledMapRenderer mapRenderer;
    private GridView grid;

    private TowerDefenceScreen(TowerDefenceStage stage, OrthographicCamera camera, int virtualMapX, int virtualMapY, int virtualMapWidth, int virtualWidth, int virtualHeight) {
        super(stage);
        camera.translate(-virtualMapX, -virtualMapY);
        camera.update();
        this.mapRenderer = new TowerDefenceMapRenderer(camera, virtualMapWidth);
        camera.translate(virtualMapX, virtualMapY);
        camera.update();
        this.grid = new GridView(camera, virtualWidth, virtualHeight);

        // Position camera
        camera.translate(-virtualMapX, -virtualMapY);

        // Add GUI icons
        getStage().addActor(new BasicTower(virtualMapWidth, 0, 0.1f, 1000));
        getStage().addActor(new DoubleBasicTower(virtualMapWidth, 1, 0.1f, 500));

        // Add endpoint health
        getStage().addActor(new Number(-virtualMapX, 1, () -> getStage().getEndpointHealth()));

        // Add game score
        getStage().addActor(new Number(virtualMapWidth, virtualHeight - 1, () -> getStage().getScore()));

        // Add round counter
        getStage().addActor(new Number(-virtualMapX, virtualHeight - 1, () -> getStage().getRound()));

        // Add Tower generator
        getStage().addListener(new TowerSpawner(getStage()));
    }

    public void onGameEnd(Runnable task) {
        getStage().setEndGameTask(task);
    }

    @Override
    public TowerDefenceStage getStage() {
        return (TowerDefenceStage) super.getStage();
    }

    @Override
    public void doRender() {
        mapRenderer.render();
        grid.render();
        super.doRender();
    }

    @Override
    public void resize(int width, int height) {
        //gameViewport.update(width, height);
        //camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        //camera.update();
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        mapRenderer.dispose();
        grid.dispose();
        super.dispose();
    }

    public static TowerDefenceScreen createInstance() {
        // Configure the game view
        int virtualMapX = 1;
        int virtualMapY = 0;
        int virtualMapWidth = (Integer) TILE_MAP.getProperties().get("width");
        int virtualMapHeight = (Integer) TILE_MAP.getProperties().get("height");
        int virtualWidth = 2 + virtualMapWidth;
        int virtualHeight = virtualMapHeight;
        OrthographicCamera camera = new OrthographicCamera(virtualWidth, virtualHeight);
        Viewport gameViewport = new FitViewport(virtualWidth, virtualHeight, camera);

        // Configure rendered scene
        TowerDefenceStage gameStage = new TowerDefenceStage(gameViewport, virtualMapWidth, virtualMapHeight);

        return new TowerDefenceScreen(gameStage, camera, virtualMapX, virtualMapY, virtualMapWidth, virtualWidth, virtualHeight);
    }
}
