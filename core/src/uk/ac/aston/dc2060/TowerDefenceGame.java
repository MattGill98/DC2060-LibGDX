package uk.ac.aston.dc2060;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import uk.ac.aston.dc2060.controller.EnemyStage;
import uk.ac.aston.dc2060.controller.GridStage;
import uk.ac.aston.dc2060.controller.GuiStage;
import uk.ac.aston.dc2060.model.Enemy;
import uk.ac.aston.dc2060.model.EnemyRoute;
import uk.ac.aston.dc2060.model.TileID;
import uk.ac.aston.dc2060.model.Tower;
import uk.ac.aston.dc2060.view.GuiViewport;
import uk.ac.aston.dc2060.view.MapViewport;
import uk.ac.aston.dc2060.view.TerrainView;

public class TowerDefenceGame extends ApplicationAdapter {

    private Stage stage1;

    private GridStage gridStage;

    private TerrainView terrainView;

    private Stage guiStage;

    @Override
    public void create () {
        // Load tilemap
        TiledMap map = new TmxMapLoader().load("tilemap.tmx");

        // Calculate UI dimensions
        int mapWidth = Gdx.graphics.getWidth() - (Integer) map.getProperties().get("tilewidth");
        float tileSize = mapWidth / (float) (Integer) map.getProperties().get("width");

        // Configure grid rendering
        this.gridStage = new GridStage(new MapViewport(tileSize), tileSize);

        // Configure terrain rendering
        this.terrainView = new TerrainView(map, mapWidth);

        // Configure GUI
        this.guiStage = new GuiStage(new GuiViewport(tileSize, mapWidth));
        Tower singleTurret = new Tower(map.getTileSets(), TileID.SINGLE_TURRET);
        guiStage.addActor(singleTurret);
        Tower doubleTurret = new Tower(map.getTileSets(), TileID.DOUBLE_TURRET);
        guiStage.addActor(doubleTurret);

        // Configure first stage
        stage1 = new EnemyStage(new MapViewport(tileSize), new Enemy(map.getTileSets(), TileID.SOLDIER, EnemyRoute.ROUTE));
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
        guiStage.draw();

        // Render grid
        gridStage.draw();
    }

    @Override
    public void dispose () {
        stage1.dispose();
        gridStage.dispose();
        terrainView.dispose();
        guiStage.dispose();
    }
}
