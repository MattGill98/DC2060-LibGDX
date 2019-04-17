package uk.ac.aston.dc2060.view.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class CustomTiledMapRenderer extends OrthogonalTiledMapRenderer {

    public CustomTiledMapRenderer(TiledMap map, int originalTileSize, int renderTileSize) {
        super(map, renderTileSize / (float) originalTileSize);

        // Configure camera
        OrthographicCamera mapCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mapCamera.setToOrtho(true);
        mapCamera.update();
        setView(mapCamera);
    }

}
