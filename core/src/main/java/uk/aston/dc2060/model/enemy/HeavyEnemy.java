package uk.aston.dc2060.model.enemy;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import uk.aston.dc2060.model.tiles.TileID;

public class HeavyEnemy extends Enemy {

    private static final float SPEED = 1;
    private static final float HEALTH = 4;

    /**
     * Create a heavy enemy.
     *
     * @param tileSet the tileset to fetch the enemy texture from.
     */
    public HeavyEnemy(TiledMapTileSets tileSet) {
        this(tileSet, 1f, 1f);
    }

    /**
     * Create a heavy enemy.
     *
     * @param tileSet          the tileset to fetch the enemy texture from.
     * @param speedMultiplier  the multiplier for the standard speed.
     * @param healthMultiplier the multiplier for the standard health.
     */
    public HeavyEnemy(TiledMapTileSets tileSet, float speedMultiplier, float healthMultiplier) {
        super(tileSet, TileID.HEAVY_SOLDIER, SPEED * speedMultiplier, HEALTH * healthMultiplier);
    }
}
