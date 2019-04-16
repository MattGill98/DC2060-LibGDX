package uk.ac.aston.dc2060;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TowerDefenceGame extends ApplicationAdapter {

    // Map components
    private MapRenderer mapRenderer;
    private TiledMap map;

    // Sprite components
    private SpriteBatch batch;

    @Override
    public void create () {
        // Configure Map rendering
        map = new TmxMapLoader().load("tilemap.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 50 / 64f);
        OrthographicCamera mapCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mapCamera.setToOrtho(true);
        mapCamera.update();
        mapRenderer.setView(mapCamera);

        // Configure sprite rendering
        batch = new SpriteBatch();
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render map
        mapRenderer.render();

        // Render everything else
        batch.begin();
        batch.end();
    }

    @Override
    public void dispose () {
        map.dispose();
        batch.dispose();
    }
}
