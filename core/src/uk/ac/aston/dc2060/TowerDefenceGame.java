package uk.ac.aston.dc2060;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import uk.ac.aston.dc2060.model.TileID;
import uk.ac.aston.dc2060.model.TileMap;
import uk.ac.aston.dc2060.view.TileMapView;

public class TowerDefenceGame extends ApplicationAdapter {

    private static final int TILE_SIZE = 50;

    // Map component
    private TileMapView map;

    // Sprite components
    private SpriteBatch batch;
    private TextureRegion singleTurret;
    private TextureRegion doubleTurret;

    @Override
    public void create () {
        // Configure Map rendering
        TileMap mapModel = new TileMap("tilemap.tmx");
        this.map = new TileMapView(mapModel, TILE_SIZE);

        // Configure sprite rendering
        this.batch = new SpriteBatch();
        this.singleTurret = mapModel.getSprite(TileID.SINGLE_TURRET);
        this.doubleTurret = mapModel.getSprite(TileID.DOUBLE_TURRET);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render map
        map.render();

        // Render everything else
        batch.begin();
        batch.draw(singleTurret, TILE_SIZE * 2, TILE_SIZE * 2);
        batch.draw(doubleTurret, TILE_SIZE * 3, TILE_SIZE * 3);
        batch.end();
    }

    @Override
    public void dispose () {
        map.dispose();
        batch.dispose();
    }
}
