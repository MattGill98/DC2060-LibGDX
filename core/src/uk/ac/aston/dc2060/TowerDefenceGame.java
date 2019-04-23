package uk.ac.aston.dc2060;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import uk.ac.aston.dc2060.model.Actor;
import uk.ac.aston.dc2060.model.TileID;
import uk.ac.aston.dc2060.view.MapView;

public class TowerDefenceGame extends ApplicationAdapter {

    private TiledMap mapModel;
    private MapView mapView;

    @Override
    public void create () {
        // Configure Map rendering
        this.mapModel = new TmxMapLoader().load("tilemap.tmx");
        this.mapView = new MapView(mapModel,0, 0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapView.begin();
        TiledMapTileSets tileSet = mapModel.getTileSets();
        mapView.render(new Actor(tileSet, TileID.SINGLE_TURRET, 1, 2));
        mapView.render(new Actor(tileSet, TileID.DOUBLE_TURRET, 3, 3));
        mapView.end();
    }

    @Override
    public void dispose () {
        mapView.dispose();
    }
}
