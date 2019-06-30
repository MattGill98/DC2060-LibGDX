package uk.aston.dc2060.model.enemy;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import uk.aston.dc2060.model.tiles.TileID;

public class SimpleEnemy extends Enemy {

    private static final float SPEED = 2;
    private static final float HEALTH = 2;

    /**
     * Create a basic enemy.
     *
     * @param tileSet the tileset to fetch the enemy texture from.
     */
    public SimpleEnemy(TiledMapTileSets tileSet) {
        this(tileSet, 1f, 1f);
    }

    /**
     * Create a basic enemy.
     *
     * @param tileSet          the tileset to fetch the enemy texture from.
     * @param speedMultiplier  the multiplier for the standard speed.
     * @param healthMultiplier the multiplier for the standard health.
     */
    public SimpleEnemy(TiledMapTileSets tileSet, float speedMultiplier, float healthMultiplier) {
        super(tileSet, TileID.SOLDIER, SPEED * speedMultiplier, HEALTH * healthMultiplier);
    }
}
