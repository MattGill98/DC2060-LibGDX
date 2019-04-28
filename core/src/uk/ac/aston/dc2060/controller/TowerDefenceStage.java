package uk.ac.aston.dc2060.controller;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import uk.ac.aston.dc2060.model.Enemy;
import uk.ac.aston.dc2060.model.EnemyRoute;
import uk.ac.aston.dc2060.model.TileID;

import java.util.HashSet;
import java.util.Set;

public class TowerDefenceStage extends PollingStage {

    private TiledMapTileSets tileSet;

    private OrthogonalTiledMapRenderer mapRenderer;

    private Set<Enemy> enemies;

    private int enemySpawnInterval;
    private int enemySpawnCounter;

    public TowerDefenceStage(Viewport viewport, TiledMapTileSets tileSet, OrthogonalTiledMapRenderer mapRenderer) {
        super(viewport, 1000);
        this.tileSet = tileSet;
        this.mapRenderer = mapRenderer;
        this.enemies = new HashSet<>();
        this.enemySpawnInterval = 4;
    }

    @Override
    public void addActor(Actor actor) {
        if (actor instanceof Enemy) {
            enemies.add((Enemy) actor);
        }
        super.addActor(actor);
    }

    @Override
    public void draw() {
        mapRenderer.render();
        super.draw();
    }

    @Override
    protected void timeout() {
        enemies.forEach(Enemy::move);
        if (enemySpawnCounter++ % enemySpawnInterval == 0) {
            Enemy newEnemy = new Enemy(tileSet, TileID.SOLDIER, EnemyRoute.ROUTE);
            addActor(newEnemy);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        mapRenderer.dispose();
    }
}
