package uk.ac.aston.dc2060.model;

public enum TileID {
    SINGLE_TURRET(249), DOUBLE_TURRET(250);

    private int id;

    TileID(int id) {
        this.id = id + 1;
    }

    public int getID() {
        return id;
    }
}
