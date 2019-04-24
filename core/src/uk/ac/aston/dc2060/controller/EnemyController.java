package uk.ac.aston.dc2060.controller;

import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.math.GridPoint2;
import uk.ac.aston.dc2060.model.Enemy;
import uk.ac.aston.dc2060.model.TileID;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class EnemyController extends LogicController {

    private static GridPoint2[] ROUTE = {
            new GridPoint2(-1, 10),
            new GridPoint2(0, 10),
            new GridPoint2(1, 10),
            new GridPoint2(2, 10),
            new GridPoint2(3, 10),
            new GridPoint2(4, 10),
            new GridPoint2(5, 10),
            new GridPoint2(6, 10),
            new GridPoint2(7, 10),
            new GridPoint2(8, 10),
            new GridPoint2(9, 10),
            new GridPoint2(10, 10),
            new GridPoint2(11, 10),
            new GridPoint2(12, 10),
            new GridPoint2(13, 10),
            new GridPoint2(14, 10),
            new GridPoint2(14, 9),
            new GridPoint2(14, 8),
            new GridPoint2(14, 7),
            new GridPoint2(13, 7),
            new GridPoint2(12, 7),
            new GridPoint2(11, 7),
            new GridPoint2(10, 7),
            new GridPoint2(9, 7),
            new GridPoint2(8, 7),
            new GridPoint2(7, 7),
            new GridPoint2(6, 7),
            new GridPoint2(5, 7),
            new GridPoint2(4, 7),
            new GridPoint2(3, 7),
            new GridPoint2(2, 7),
            new GridPoint2(1, 7),
            new GridPoint2(1, 6),
            new GridPoint2(1, 5),
            new GridPoint2(1, 4),
            new GridPoint2(2, 4),
            new GridPoint2(3, 4),
            new GridPoint2(4, 4),
            new GridPoint2(5, 4),
            new GridPoint2(6, 4),
            new GridPoint2(7, 4),
            new GridPoint2(8, 4),
            new GridPoint2(9, 4),
            new GridPoint2(10, 4),
            new GridPoint2(11, 4),
            new GridPoint2(12, 4),
            new GridPoint2(12, 4),
            new GridPoint2(13, 4),
            new GridPoint2(14, 4),
            new GridPoint2(14, 3),
            new GridPoint2(14, 2),
            new GridPoint2(14, 1),
            new GridPoint2(13, 1),
            new GridPoint2(12, 1),
            new GridPoint2(11, 1),
            new GridPoint2(10, 1),
            new GridPoint2(9, 1),
            new GridPoint2(8, 1),
            new GridPoint2(7, 1),
            new GridPoint2(6, 1),
            new GridPoint2(5, 1),
            new GridPoint2(4, 1),
            new GridPoint2(3, 1),
            new GridPoint2(2, 1),
            new GridPoint2(1, 1),
            new GridPoint2(0, 1),
            new GridPoint2(-1, 1)
    };

    private List<Enemy> enemies;

    public EnemyController(TiledMapTileSets tileSet) {
        super(1000);
        this.enemies = new LinkedList<>();
        EnemySpawner spawner = new EnemySpawner(enemies, new Enemy(tileSet, TileID.SOLDIER, Arrays.asList(ROUTE)));
        LogicController.registerController(spawner);
    }

    public Collection<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    protected void update() {
        enemies.removeIf(enemy -> !enemy.move());
    }
}
