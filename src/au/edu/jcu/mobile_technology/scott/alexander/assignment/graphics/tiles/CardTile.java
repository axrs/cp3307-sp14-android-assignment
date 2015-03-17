package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.tiles;


import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.AtlasTile;

/**
 * Card or Billboard sprite used for providing a consistent canvas to paint text over
 */
public class CardTile extends AtlasTile {
    //Atlas map row reference
    private static final int ATLAS_ROW = 0;
    //Atlas map column reference
    private static final int ATLAS_COLUMN = 0;

    private SliceLocation slice = SliceLocation.MIDDLE;

    /**
     * Constructs a new CardTile using the middle sprite of the 9Slice
     */
    public CardTile() {
        setTileType(slice.MIDDLE);
    }

    /**
     * Constructs a new CardTile using the specified 9Slice tile
     *
     * @param slice Slicelocation
     */
    public CardTile(SliceLocation slice) {
        setTileType(slice);
    }

    /**
     * Gets the tile slice
     *
     * @return Slice type
     */
    public SliceLocation getSlice() {
        return slice;
    }


    /**
     * Sets the slice location
     *
     * @param slice New slice target
     */
    public void setTileType(SliceLocation slice) {
        this.slice = slice;

        int signOrdinal = slice.ordinal();

        //Calculate the tile location
        int col = signOrdinal % 3;
        int row = signOrdinal / 3;

        setTileLocation(row + ATLAS_ROW, col + ATLAS_COLUMN);
    }

    /**
     * Card SliceLocations inspired by the 9Slice technique
     */
    public enum SliceLocation {
        TOP_LEFT,
        TOP_MIDDLE,
        TOP_RIGHT,
        MIDDLE_LEFT,
        MIDDLE,
        MIDDLE_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_MIDDLE,
        BOTTOM_RIGHT
    }
}
