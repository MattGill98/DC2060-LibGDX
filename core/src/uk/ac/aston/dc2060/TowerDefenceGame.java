package uk.ac.aston.dc2060;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TowerDefenceGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture tower;
    private Texture tile;

    @Override
    public void create () {
        batch = new SpriteBatch();
        tower = new Texture("test-tower.png");
        tile = new Texture("grass-tile.png");
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(tower, 100, 100);
        batch.draw(tile, 300, 200);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        tower.dispose();
        tile.dispose();
    }
}
