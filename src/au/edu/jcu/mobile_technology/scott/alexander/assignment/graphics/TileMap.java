package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics;

/**
 * Two dimensional TileMap class holding information about any tiles located at a position
 */
public class TileMap {

    //Map structure
    private AtlasTile[][] map;
    private int tileCount;
    private int rows;
    private int columns;
    private int tilesPerScene;

    /**
     * Sets the atlas tile map to a specified size.
     * <p/>
     * Note: Map gets cleared on size change.
     *
     * @param rows        Number of tile rows
     * @param columns     Number of tile columns
     * @param totalScenes Number of scenes in the map
     */
    public void setSize(int rows, int columns, int totalScenes) {
        map = new AtlasTile[rows][columns];
        tileCount = rows * columns;
        this.rows = rows;
        this.columns = columns;
        tilesPerScene = columns / totalScenes;
    }

    /**
     * Gets number of tiles (width) that are displayed in each scene
     *
     * @return scene tile width
     */
    public int getTilesPerScene() {
        return tilesPerScene;
    }

    /**
     * Gets the current map
     *
     * @return map or null
     */
    public AtlasTile[][] getMap() {
        return map;
    }

    /**
     * Gets the total number of tiles in the map
     *
     * @return Number of map tiles
     */
    public int getTileCount() {
        return tileCount;
    }

    /**
     * Gets the total number of rows in the map
     *
     * @return row count
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the total number of columns in the map
     *
     * @return column count
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Sets the tile at the specified location
     *
     * @param row    map row
     * @param column map column
     * @param tile   tile to setSequence
     */
    public void setTile(int row, int column, AtlasTile tile) {
        if (map != null) {
            map[row][column] = tile;
        }
    }

    /**
     * Gets the tile at a specified location
     *
     * @param row    map row
     * @param column map column
     */
    public AtlasTile getTile(int row, int column) {
        AtlasTile tile = null;

        if (map != null && row <= getRows() && column <= getColumns()) {
            tile = map[row][column];
        }
        return tile;
    }
}
