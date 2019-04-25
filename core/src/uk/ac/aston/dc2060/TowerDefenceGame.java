package uk.ac.aston.dc2060;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import uk.ac.aston.dc2060.controller.EnemyController;
import uk.ac.aston.dc2060.controller.LogicController;
import uk.ac.aston.dc2060.model.TileID;
import uk.ac.aston.dc2060.view.GridView;
import uk.ac.aston.dc2060.view.GuiView;
import uk.ac.aston.dc2060.view.MapView;

public class TowerDefenceGame extends ApplicationAdapter {

    private GridView gridView;
    private MapView mapView;
    private GuiView guiView;

    private EnemyController enemyController;

    @Override
    public void create () {
        // Load tilemap
        TiledMap map = new TmxMapLoader().load("tilemap.tmx");

        // Calculate UI dimensions
        float mapSize = Gdx.graphics.getWidth() - (float) (Integer) map.getProperties().get("tilewidth");
        float tileSize = mapSize / (float) (Integer) map.getProperties().get("width");

        // Configure rendering
        this.gridView = new GridView(tileSize);
        this.mapView = new MapView(map, 0, 0, tileSize);
        this.guiView = new GuiView(tileSize, mapSize);
        guiView.addTowerTile(map.getTileSets(), TileID.SINGLE_TURRET);
        guiView.addTowerTile(map.getTileSets(), TileID.DOUBLE_TURRET);

        // Configure enemy spawning
        enemyController = new EnemyController(map.getTileSets());
        LogicController.registerController(enemyController);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Game logic
        LogicController.pollControllers(Gdx.graphics.getDeltaTime());

        // Render map
        mapView.begin();
        mapView.render(enemyController.getEnemies());
        mapView.end();

        // Render gui
        guiView.render();

        // Render grid
        gridView.render();
    }

    @Override
    public void dispose () {
        mapView.dispose();
    }
}
