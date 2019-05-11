package uk.ac.aston.dc2060.model;

/**
 * An enum describing the IDs of each tile in the tileset.
 */
public enum TileID {
    DIRT(167),
    SINGLE_TURRET(249),
    SINGLE_TURRET_BASE(180),
    SINGLE_TURRET_GUNFIRE(296),
    DOUBLE_TURRET(250),
    SOLDIER(245),
    ZERO(276),
    ONE(277),
    TWO(278),
    THREE(279),
    FOUR(280),
    FIVE(281),
    SIX(282),
    SEVEN(283),
    EIGHT(284),
    NINE(285);

    private int id;

    TileID(int id) {
        this.id = id + 1;
    }

    public int getID() {
        return id;
    }
}
