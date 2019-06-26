package uk.aston.dc2060.model.tower;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import uk.aston.dc2060.model.enemy.Enemy;
import uk.aston.dc2060.model.tiles.TileID;
import uk.aston.dc2060.model.tower.components.TowerBase;
import uk.aston.dc2060.model.tower.components.TowerTurret;
import uk.aston.dc2060.model.tower.strategy.TowerStrategy;

public abstract class Tower extends Group {

    private final TowerTurret turret;

    private final float maxRange;
    private final float damage;
    private final float fireRate;

    public Tower(int x, int y, TiledMapTileSets tileSet, TileID turretTileID, float maxRange, float damage, float fireRate) {
        this.maxRange = maxRange;
        this.damage = damage;
        this.fireRate = fireRate;
        setBounds(x, y, 1, 1);
        addActor(new TowerBase(tileSet, TileID.SINGLE_TURRET_BASE));
        turret = new TowerTurret(tileSet, turretTileID);
        addActor(turret);
    }

    void enable() {
        addAction(new TowerStrategy(this));
        addCaptureListener(new ClickListener(0) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("clicked");
                setColor(1, 1, 1, 0.8f);
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        Color batchColor = batch.getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        super.draw(batch, parentAlpha);
        batch.setColor(batchColor);
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

    @Override
    public void setRotation(float degrees) {
        turret.setRotation(degrees);
    }

    static <T extends Tower> Tower createTower(Class<T> towerType, TiledMapTileSets tileSet, int x, int y) {
        switch (towerType.getSimpleName()) {
            case "SingleTurret":
                return new SingleTurret(tileSet, x, y);
            case "DoubleTurret":
                return new DoubleTurret(tileSet, x, y);
            default:
                throw new IllegalArgumentException("Unrecognised tower type: " + towerType);
        }
    }
}
