package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.tiles;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.AtlasTile;

/**
 * Game decoration class representing a information sign
 */
public class SignTile extends AtlasTile {

    //Icon type
    private Icon icon = Icon.BLANK;

    /**
     * Constructor setting the type to blank
     */
    public SignTile() {
        setIcon(Icon.BLANK);
    }

    /**
     * Creates a SignTile with a specified icon type
     *
     * @param icon type
     */
    public SignTile(Icon icon) {
        setIcon(icon);
    }

    /**
     * Gets the icon type
     *
     * @return iconType
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * Sets the sign icon
     *
     * @param icon
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
        int col = 0;
        int row = 0;
        switch (icon) {
            case BLANK:
                col = 3;
                break;
            case LEFT:
                col = 4;
                break;
            case RIGHT:
                col = 5;
                break;
            case TICK:
                col = 6;
                break;
            case CROSS:
                col = 7;
                break;
            case QUESTION:
                row = 1;
                col = 6;
                break;
            case SKULL:
                row = 1;
                col = 7;
                break;
        }
        setTileLocation(row, col);
    }

    /**
     * SignTile Icon types
     */
    public enum Icon {
        BLANK,
        LEFT,
        RIGHT,
        TICK,
        CROSS,
        QUESTION,
        SKULL
    }
}