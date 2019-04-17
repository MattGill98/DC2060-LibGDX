package uk.ac.aston.dc2060;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import uk.ac.aston.dc2060.view.Sprite;
import uk.ac.aston.dc2060.view.TileID;
import uk.ac.aston.dc2060.view.render.CustomTiledMapRenderer;
import uk.ac.aston.dc2060.view.render.TiledRenderer;

public class TowerDefenceGame extends ApplicationAdapter {

    private static final int TILE_SIZE = 50;

    private TiledRenderer renderer;

    @Override
    public void create () {
        // Configure Map rendering
        TiledMap mapModel = new TmxMapLoader().load("tilemap.tmx");
        this.renderer = new TiledRenderer(mapModel, TILE_SIZE);

        this.renderer.setMapRenderer(new CustomTiledMapRenderer(mapModel, (Integer) mapModel.getProperties().get("tilewidth"), TILE_SIZE));
        this.renderer.setSpriteRenderer(new SpriteBatch());

        // Configure sprite rendering
        renderer.addSprite(new Sprite(TileID.SINGLE_TURRET, 1, 2));
        renderer.addSprite(new Sprite(TileID.DOUBLE_TURRET, 3, 3));
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
    }

    @Override
    public void dispose () {
        renderer.dispose();
    }
}
