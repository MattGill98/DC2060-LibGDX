package uk.aston.dc2060.model.tower;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.scenes.scene2d.Group;
import uk.aston.dc2060.controller.tower.TowerSelectedListener;
import uk.aston.dc2060.model.enemy.Enemy;
import uk.aston.dc2060.model.stage.GameStage;
import uk.aston.dc2060.model.tiles.TileID;
import uk.aston.dc2060.model.tower.components.TowerBase;
import uk.aston.dc2060.model.tower.components.TowerTurret;
import uk.aston.dc2060.model.tower.strategy.TowerStrategy;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The generic class for all towers.
 */
public abstract class Tower extends Group {

    private final TowerTurret turret;

    private final float maxRange;
    private final float damage;
    private final float fireRate;

    private final AtomicInteger cost;

    public Tower(TiledMapTileSets tileSet, TileID turretTileID, int x, int y, float maxRange, float damage, float fireRate, int cost) {
        this.maxRange = maxRange;
        this.damage = damage;
        this.fireRate = fireRate;
        this.cost = new AtomicInteger(cost);
        setBounds(x, y, 1, 1);
        addActor(new TowerBase(tileSet, TileID.SINGLE_TURRET_BASE));
        turret = new TowerTurret(tileSet, turretTileID);
        addActor(turret);
    }

    public void enable() {
        addAction(new TowerStrategy(this));
        getStage().addListener(new TowerSelectedListener(this, ((GameStage) getStage()).getContextMenu()));
    }

    @Override
    public boolean remove() {
        ((GameStage) getStage()).increaseScore(cost.get());
        return super.remove();
    }

    public void shoot(Enemy enemy) {
        enemy.modifyHealth(-damage);
    }

    public float getMaxRange() {
        return maxRange;
    }

    public float getDamage() {
        return damage;
    }

    public float getFireRate() {
        return fireRate;
    }

    public AtomicInteger getCost() {
        return cost;
    }

    @Override
    public void setRotation(float degrees) {
        turret.setRotation(degrees);
    }

    public static Tower createTower(Class<? extends Tower> towerType, TiledMapTileSets tileSet, int x, int y) {
        switch (towerType.getSimpleName()) {
            case "SingleTurret":
                return new SingleTurret(tileSet, x, y);
            case "DoubleTurret":
                return new DoubleTurret(tileSet, x, y);
            default:
                throw new IllegalArgumentException("Unrecognised tower type: " + towerType);
        }
    }

    public static Tower createTower(Class<? extends Tower> towerType, TiledMapTileSets tileSet, int x, int y, int cost) {
        switch (towerType.getSimpleName()) {
            case "SingleTurret":
                return new SingleTurret(tileSet, x, y, cost);
            case "DoubleTurret":
                return new DoubleTurret(tileSet, x, y, cost);
            default:
                throw new IllegalArgumentException("Unrecognised tower type: " + towerType);
        }
    }
}
