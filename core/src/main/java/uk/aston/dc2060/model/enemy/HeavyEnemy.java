package uk.aston.dc2060.model.enemy;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import uk.aston.dc2060.model.tiles.TileID;

public class HeavyEnemy extends Enemy {
    /**
     * Create a basic enemy.
     *
     * @param tileSet the tileset to fetch the enemy texture from.
     */
    public HeavyEnemy(TiledMapTileSets tileSet) {
        super(tileSet, TileID.HEAVY_SOLDIER, 1, 2);
    }
}
