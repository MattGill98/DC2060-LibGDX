package uk.aston.dc2060.model.tower;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import uk.aston.dc2060.controller.TimedAction;
import uk.aston.dc2060.model.enemy.Enemy;
import uk.aston.dc2060.model.tiles.TileID;
import uk.aston.dc2060.model.tower.components.TowerGunfire;

public class SingleTurret extends Tower {

    private final TowerGunfire gunfire;

    SingleTurret(TiledMapTileSets tileSet, int x, int y) {
        super(x, y, tileSet, TileID.SINGLE_TURRET, 10, 0.25f, 1000);
        this.gunfire = new TowerGunfire(tileSet, TileID.SINGLE_TURRET_GUNFIRE);
        gunfire.setVisible(false);
        addActor(gunfire);
    }

    @Override
    public void shoot(Enemy enemy) {
        gunfire.setVisible(true);
        addAction(new TimedAction(100f) {
            @Override
            public boolean act() {
                gunfire.setVisible(false);
                return true;
            }
        });
        super.shoot(enemy);
    }

    @Override
    public void setRotation(float degrees) {
        super.setRotation(degrees);
        gunfire.setRotation(degrees);
    }
}
