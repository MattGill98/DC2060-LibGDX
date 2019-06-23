package uk.aston.dc2060.model.enemy;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import uk.aston.dc2060.model.tiles.TileID;

public class SimpleEnemy extends Enemy {
    /**
     * Create a basic enemy.
     *
     * @param tileSet the tileset to fetch the enemy texture from.
     */
    public SimpleEnemy(TiledMapTileSets tileSet) {
        super(tileSet, TileID.SOLDIER, 2, 1);
    }
}
