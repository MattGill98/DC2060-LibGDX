package uk.ac.aston.dc2060.model.enemy;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Arrays;
import java.util.List;

/**
 * Utility class to hold the route that each enemy should follow.
 */
public class EnemyRoute {

    /**
     * A list of world space coordinates constituting the enemy route.
     */
    public static List<GridPoint2> ROUTE = Arrays.asList(
            new GridPoint2(-1, 10),
            new GridPoint2(14, 10),
            new GridPoint2(14, 7),
            new GridPoint2(1, 7),
            new GridPoint2(1, 4),
            new GridPoint2(14, 4),
            new GridPoint2(14, 1),
            new GridPoint2(-1, 1)
    );

}
