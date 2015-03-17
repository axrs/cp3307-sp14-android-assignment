package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.tiles;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.AtlasTile;

import java.util.Random;

/**
 * Ground Tile
 */
public class GroundTile extends AtlasTile {

    //Atlas map row reference
    private static final int ATLAS_ROW = 1;
    //Atlas map column reference
    private static final int ATLAS_COLUMN = 3;

    //Ground decoration type
    private Decoration decoration = Decoration.PLAIN;

    /**
     * Constructs a GroundTile with a random ground decoration
     */
    public GroundTile() {
        randomlyDecorate();
    }

    /**
     * Constructs a GroundTile with a specified decoration
     *
     * @param decoration Decoration type
     */
    public GroundTile(Decoration decoration) {
        setDecoration(decoration);
    }

    /**
     * Gets the current tile decoration
     *
     * @return GroundTile decoration
     */
    public Decoration getDecoration() {
        return decoration;
    }

    /**
     * Sets the ground decoration type
     *
     * @param decoration Ground decoration
     */
    public void setDecoration(Decoration decoration) {
        this.decoration = decoration;
        int column = decoration.ordinal() + ATLAS_COLUMN;
        setTileLocation(ATLAS_ROW, column);
    }

    /**
     * Randomly decorates the sprite
     */
    private void randomlyDecorate() {
        Random rand = new Random();
        int randomNum = rand.nextInt((10 - 1) + 1) + 1;
        Decoration type = Decoration.PLAIN;
        if (randomNum == 6) {
            type = Decoration.WHITE_FLOWER;
        } else if (randomNum == 10) {
            type = Decoration.RED_FLOWER;
        }
        setDecoration(type);
    }

    /**
     * Decoration types
     */
    public enum Decoration {
        PLAIN,
        WHITE_FLOWER,
        RED_FLOWER
    }
}
