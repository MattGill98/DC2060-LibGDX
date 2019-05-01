package uk.ac.aston.dc2060.model;

/**
 * An enum describing the IDs of each tile in the tileset.
 */
public enum TileID {
    SINGLE_TURRET(249),
    DOUBLE_TURRET(250),
    SOLDIER(245),
    GUN_FIRE(297);

    private int id;

    TileID(int id) {
        this.id = id + 1;
    }

    public int getID() {
        return id;
    }
}
