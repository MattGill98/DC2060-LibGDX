package uk.ac.aston.dc2060;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import uk.ac.aston.dc2060.controller.EnemyStage;
import uk.ac.aston.dc2060.model.Enemy;
import uk.ac.aston.dc2060.model.EnemyRoute;
import uk.ac.aston.dc2060.model.TileID;
import uk.ac.aston.dc2060.view.GridView;
import uk.ac.aston.dc2060.view.GuiView;
import uk.ac.aston.dc2060.view.MapViewport;
import uk.ac.aston.dc2060.view.TerrainView;

public class TowerDefenceGame extends ApplicationAdapter {

    private Stage stage1;

    private GridView gridView;

    private TerrainView terrainView;

    private GuiView guiView;

    @Override
    public void create () {
        // Load tilemap
        TiledMap map = new TmxMapLoader().load("tilemap.tmx");

        // Calculate UI dimensions
        float mapSize = Gdx.graphics.getWidth() - (float) (Integer) map.getProperties().get("tilewidth");
        float tileSize = mapSize / (float) (Integer) map.getProperties().get("width");

        // Configure rendering
        this.gridView = new GridView(tileSize);
        this.terrainView = new TerrainView(map, tileSize);
        this.guiView = new GuiView(tileSize, mapSize);
        guiView.addTowerTile(map.getTileSets(), TileID.SINGLE_TURRET);
        guiView.addTowerTile(map.getTileSets(), TileID.DOUBLE_TURRET);

        // Configure first stage
        stage1 = new EnemyStage(new MapViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), tileSize), new Enemy(map.getTileSets(), TileID.SOLDIER, EnemyRoute.ROUTE));
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render map
        terrainView.render();

        // Run stage
        stage1.act();
        stage1.draw();

        // Render gui
        guiView.render();

        // Render grid
        gridView.render();
    }

    @Override
    public void dispose () {
        stage1.dispose();
        gridView.dispose();
        terrainView.dispose();
        guiView.dispose();
    }
}
