package uk.ac.aston.dc2060.model.enemy;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import uk.ac.aston.dc2060.model.TileID;

public class BasicEnemy extends Enemy {

    public BasicEnemy(TiledMapTileSets tileSet) {
        super(tileSet, TileID.SOLDIER, 5);
    }
}
