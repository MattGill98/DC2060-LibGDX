package uk.ac.aston.dc2060;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TowerDefenceGame extends ApplicationAdapter {

    // Sprite components
    private SpriteBatch batch;
    
    // Map components
    private OrthographicCamera mapCamera;
    private MapRenderer mapRenderer;
    private TiledMap map;

    @Override
    public void create () {
        batch = new SpriteBatch();
        map = new TmxMapLoader().load("tilemap.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 50 / 64f);
        mapCamera = new OrthographicCamera(800, 600);
        mapCamera.setToOrtho(false);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        mapCamera.update();
        mapRenderer.render();
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        map.dispose();
    }
}
