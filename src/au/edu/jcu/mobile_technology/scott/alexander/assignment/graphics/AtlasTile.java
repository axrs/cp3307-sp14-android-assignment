package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics;

/**
 * Class representing an location of an AtlasTile
 */
public class AtlasTile {
    //Atlas tile column
    private int atlasColumn;
    //Atlas tile row
    private int atlasRow;

    /**
     * Gets the atlas map tile column
     *
     * @return Atlas column
     */
    public int getAtlasColumn() {
        return atlasColumn;
    }

    /**
     * Gets the atlas map tile row
     *
     * @return Atlas row
     */
    public int getAtlasRow() {
        return atlasRow;
    }

    /**
     * Sets the atlas map tile location
     *
     * @param row    Tile Row
     * @param column Tile Column
     */
    public void setTileLocation(int row, int column) {
        this.atlasRow = row;
        this.atlasColumn = column;
    }
}
