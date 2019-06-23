package uk.aston.dc2060.model.tower;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import uk.aston.dc2060.controller.TimedAction;
import uk.aston.dc2060.model.enemy.Enemy;
import uk.aston.dc2060.model.tiles.TileID;
import uk.aston.dc2060.model.tower.components.TowerGunfire;

public class DoubleTurret extends Tower {

    private final TowerGunfire leftGunfire;
    private final TowerGunfire rightGunfire;

    private boolean isRightNext;

    DoubleTurret(TiledMapTileSets tileSet, int x, int y) {
        super(x, y, tileSet, TileID.DOUBLE_TURRET, 5, 0.25f, 500);
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
