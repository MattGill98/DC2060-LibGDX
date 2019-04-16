package uk.ac.aston.dc2060;

import uk.ac.aston.dc2060.view.TileTexture;
import uk.ac.aston.dc2060.model.Tile;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TowerDefenceGame extends ApplicationAdapter {

    private static final int TILE_SIZE = 64;

    private SpriteBatch batch;
    private Texture tileSheet;
    private TileTexture singleTurret;
    private TextureRegion doubleTurret;

    @Override
    public void create () {
        batch = new SpriteBatch();
        tileSheet = new Texture("tilesheet.png");
        singleTurret = new TileTexture(tileSheet, TILE_SIZE, Tile.SINGLE_TURRET);
        doubleTurret = new TileTexture(tileSheet, TILE_SIZE, Tile.DOUBLE_TURRET);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(singleTurret, 100, 100);
        batch.draw(doubleTurret, 300, 200);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        tileSheet.dispose();
    }
}
