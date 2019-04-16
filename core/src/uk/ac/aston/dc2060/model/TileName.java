package uk.ac.aston.dc2060.model;

public enum TileName {

    SINGLE_TURRET(10, 19),
    DOUBLE_TURRET(10, 20);
    
    private final int row;
    private final int column;
    
    private TileName(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
}
