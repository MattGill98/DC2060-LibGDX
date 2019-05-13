package uk.ac.aston.dc2060.model.tower;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import uk.ac.aston.dc2060.model.TileID;
import uk.ac.aston.dc2060.model.TimedEvent;
import uk.ac.aston.dc2060.model.enemy.Enemy;

import static uk.ac.aston.dc2060.TowerDefenceGame.TILE_MAP;

public class BasicTower extends Tower {

    private TextureRegion turret;
    private TextureRegion base;
    private TextureRegion gunfire;

    private final float damagePerShot;
    private final int timeBetweenShotsMs;

    private int shootingAnimationCounter;

    public BasicTower(float x, float y, float damagePerShot, int timeBetweenShotsMs) {
        super(x, y);
        this.damagePerShot = damagePerShot;
        this.timeBetweenShotsMs = timeBetweenShotsMs;
        this.turret = TILE_MAP.getTileSets().getTile(TileID.SINGLE_TURRET.getID()).getTextureRegion();
        this.base = TILE_MAP.getTileSets().getTile(TileID.SINGLE_TURRET_BASE.getID()).getTextureRegion();
        this.gunfire = TILE_MAP.getTileSets().getTile(TileID.SINGLE_TURRET_GUNFIRE.getID()).getTextureRegion();
        addTimedEvent(new TimedEvent(timeBetweenShotsMs, this::shoot));
    }

    @Override
    public Tower clone() {
        return new BasicTower(getX(), getY(), damagePerShot, timeBetweenShotsMs);
    }

    @Override
    public void draw(Batch batch) {
        if (!isEnabled()) {
            batch.draw(turret, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        } else {
            // Draw base
            batch.draw(base, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), 0);

            // Calculate rotation
            double rotationCos = Math.cos(Math.toRadians(getRotation() + 90));
            double rotationSin = Math.sin(Math.toRadians(getRotation() + 90));

            // Draw turret
            float turretOffsetX = (float) rotationCos * 0.17f;
            float turretOffsetY = (float) rotationSin * 0.17f;
            batch.draw(turret, getX() + turretOffsetX, getY() + turretOffsetY, getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

            // Show the shot animation for 10 frames
            if (shootingAnimationCounter++ > 0 && shootingAnimationCounter < 10) {
                // Draw gunfire
                float gunfireOffsetX = (float) rotationCos * 0.9f;
                float gunfireOffsetY = (float) rotationSin * 0.9f;
                batch.draw(gunfire, getX() + gunfireOffsetX, getY() + gunfireOffsetY, getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
            }
        }
    }

    private void shoot() {
        Enemy target = aimingStrategy.getTarget(this);
        if (target != null) {
            target.getHealthBar().modifyHealth(-damagePerShot);
            shootingAnimationCounter = 0;
        }
    }
}
