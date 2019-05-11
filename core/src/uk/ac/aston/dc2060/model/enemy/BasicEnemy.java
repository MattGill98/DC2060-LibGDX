package uk.ac.aston.dc2060.model.enemy;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.math.Vector2;
import uk.ac.aston.dc2060.model.TileID;

public class BasicEnemy extends Enemy {

    public BasicEnemy(TiledMapTileSets tileSet) {
        super(tileSet, TileID.SOLDIER, 2);
    }

    @Override
    public boolean isVisible() {
        boolean visibleProperty = super.isVisible();
        return visibleProperty && getStage().isWithinMap(new Vector2(getX() + 0.3f, getY()));
    }
}
