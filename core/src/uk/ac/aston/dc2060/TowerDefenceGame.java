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
import uk.ac.aston.dc2060.view.MapView;

public class TowerDefenceGame extends ApplicationAdapter {

    private TiledMap mapModel;
    private MapView mapView;

    private Actor singleTurret;
    private EnemyController enemyController;

    @Override
    public void create () {
        // Configure Map rendering
        this.mapModel = new TmxMapLoader().load("tilemap.tmx");
        this.mapView = new MapView(mapModel,0, 0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth());

        singleTurret = new Actor(mapModel.getTileSets(), TileID.SINGLE_TURRET, 1, 2);

        // Configure enemy spawning
        enemyController = new EnemyController(mapModel.getTileSets());
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
    }

    @Override
    public void dispose () {
        mapView.dispose();
    }
}
