package uk.ac.aston.dc2060;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import uk.ac.aston.dc2060.controller.TowerDefenceStage;
import uk.ac.aston.dc2060.view.GridView;
import uk.ac.aston.dc2060.view.TowerDefenceMapRenderer;
import uk.ac.aston.dc2060.view.TowerDefenceViewport;

public class TowerDefenceGame extends ApplicationAdapter {

    private TowerDefenceStage stage;

    private GridView gridView;

    @Override
    public void create () {
        // Load tilemap
        TiledMap map = new TmxMapLoader().load("tilemap.tmx");

        // Calculate UI dimensions
        int mapWidth = Gdx.graphics.getWidth() - (Integer) map.getProperties().get("tilewidth");
        float tileSize = mapWidth / (float) (Integer) map.getProperties().get("width");

        // Configure grid rendering
        this.gridView = new GridView(tileSize);

        // Configure level rendering
        this.stage = new TowerDefenceStage(new TowerDefenceViewport(tileSize), map.getTileSets(), new TowerDefenceMapRenderer(map, mapWidth));
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render level
        stage.act();
        stage.draw();

        // Render grid
        gridView.render();
    }

    @Override
    public void dispose () {
        stage.dispose();
        gridView.dispose();
    }
}
