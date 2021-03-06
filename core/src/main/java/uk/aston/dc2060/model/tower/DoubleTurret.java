package uk.aston.dc2060.model.tower;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import uk.aston.dc2060.controller.TimedAction;
import uk.aston.dc2060.model.enemy.Enemy;
import uk.aston.dc2060.model.tiles.TileID;
import uk.aston.dc2060.model.tower.components.TowerGunfire;

/**
 * A turret that is more powerful but costs more.
 */
public class DoubleTurret extends Tower {

    private static final float MAX_RANGE = 5;
    private static final float DAMAGE = 1f;
    private static final int FIRE_RATE = 500;
    private static final int DEFAULT_COST = 20;

    private final TowerGunfire leftGunfire;
    private final TowerGunfire rightGunfire;

    private boolean isRightNext;

    DoubleTurret(TiledMapTileSets tileSet, int x, int y) {
        this(tileSet, x, y, DEFAULT_COST);
    }

    DoubleTurret(TiledMapTileSets tileSet, int x, int y, int cost) {
        super(tileSet, TileID.DOUBLE_TURRET, x, y, MAX_RANGE, DAMAGE, FIRE_RATE, cost);
        this.leftGunfire = new TowerGunfire(tileSet, TileID.SINGLE_TURRET_GUNFIRE, -0.1f);
        this.rightGunfire = new TowerGunfire(tileSet, TileID.SINGLE_TURRET_GUNFIRE, 0.1f);
        leftGunfire.setVisible(false);
        rightGunfire.setVisible(false);
        addActor(leftGunfire);
        addActor(rightGunfire);
    }

    @Override
    public void shoot(Enemy enemy) {
        isRightNext = !isRightNext;
        TowerGunfire nextGunfire = isRightNext? rightGunfire : leftGunfire;
        nextGunfire.setVisible(true);
        addAction(new TimedAction(100f) {
            @Override
            public boolean act() {
                nextGunfire.setVisible(false);
                return true;
            }
        });
        super.shoot(enemy);
    }

    @Override
    public void setRotation(float degrees) {
        super.setRotation(degrees);
        leftGunfire.setRotation(degrees);
        rightGunfire.setRotation(degrees);
    }

}
