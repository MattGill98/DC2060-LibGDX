package uk.ac.aston.dc2060.controller;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import uk.ac.aston.dc2060.model.Enemy;
import uk.ac.aston.dc2060.model.TileID;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class EnemyController extends LogicController {

    private List<Enemy> enemies;
    private EnemySpawner spawner;

    public EnemyController(TiledMapTileSets tileSet) {
        super(50);
        this.enemies = new LinkedList<>();
        this.spawner = new EnemySpawner(enemies, new Enemy(tileSet, TileID.SOLDIER, 0, 10));
    }

    public Collection<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    protected void update() {
        spawner.update();
        for (Enemy enemy : enemies) {
            enemy.setX(enemy.getX() + 1);
        }
    }
}
