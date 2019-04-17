package uk.ac.aston.dc2060.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import uk.ac.aston.dc2060.model.TileMap;

public class TileMapView extends ApplicationAdapter {

    private MapRenderer renderer;
    private TileMap model;

    public TileMapView(TileMap model, int tileSize) {
        this.model = model;

        OrthographicCamera mapCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mapCamera.setToOrtho(true);
        mapCamera.update();
        this.renderer = new OrthogonalTiledMapRenderer(model.getDrawableMap(), tileSize / (float) (Integer) model.getDrawableMap().getProperties().get("tilewidth"));
        this.renderer.setView(mapCamera);
    }

    @Override
    public void render() {
        super.render();
        renderer.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        model.getDrawableMap().dispose();
    }
}
