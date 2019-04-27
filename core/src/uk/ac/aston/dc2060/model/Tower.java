package uk.ac.aston.dc2060.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Tower extends DrawableActor {

    private Tower(TextureRegion texture) {
        super(texture);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("clicked");
                super.clicked(event, x, y);
            }
        });
    }

    public Tower(TiledMapTileSets tileSet, TileID tileID) {
        this(tileSet.getTile(tileID.getID()).getTextureRegion());
    }
}
