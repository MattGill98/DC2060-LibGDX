package uk.ac.aston.dc2060;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import uk.ac.aston.dc2060.controller.EnemyController;
import uk.ac.aston.dc2060.controller.LogicController;
import uk.ac.aston.dc2060.model.Actor;
import uk.ac.aston.dc2060.model.TileID;
import uk.ac.aston.dc2060.view.GridView;
import uk.ac.aston.dc2060.view.MapView;

public class TowerDefenceGame extends ApplicationAdapter {

    private float tileSize;

    private GridView gridView;
    private MapView mapView;

    private Actor singleTurret;
    private EnemyController enemyController;

    @Override
    public void create () {
        // Configure tile size
        this.tileSize = Gdx.graphics.getWidth() / 17f;

        // Configure Grid rendering
        this.gridView = new GridView(tileSize);
        gridView.setEnabled(true);

        // Configure Map rendering
        TiledMap map = new TmxMapLoader().load("tilemap.tmx");
        this.mapView = new MapView(map, 0, 0, tileSize);

        singleTurret = new Actor(map.getTileSets(), TileID.SINGLE_TURRET, 1, 2);

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

        // Render loop
        mapView.begin();
        mapView.render(singleTurret);
        mapView.render(enemyController.getEnemies());
        mapView.end();

        // Render grid
        gridView.render();
    }

    @Override
    public void dispose () {
        mapView.dispose();
    }
}
