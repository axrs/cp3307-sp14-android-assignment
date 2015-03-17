package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.layout;

import android.graphics.Rect;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.CardValueLayoutStrategy;

/**
 * Default Card Value Layout Strategy
 */
public class DefaultCardValueLayoutStrategy implements CardValueLayoutStrategy {

    /**
     * Calculates a basic grid layout for rendering card values
     *
     * @param width            Container Width
     * @param height           Container Height
     * @param tileSize         game tile size
     * @param totalValuesToFit Total number of values to fit within container
     * @return Grid based Rect
     */
    @Override
    public Rect getRect(int width, int height, int tileSize, int totalValuesToFit) {
        Rect paintRect = new Rect();
        double halfTileSize = tileSize / 2.0;
        double oneAndHalfTileSize = tileSize * 1.5;

        //Convert to double to avoid integer truncation for row and column calculations
        double values = totalValuesToFit + 0.0;

        int maxColumnsPerRow;
        int columns;
        int rows;
        maxColumnsPerRow = (int) Math.ceil(width / halfTileSize);

        if (width > height) {
            maxColumnsPerRow = (int) (maxColumnsPerRow / (width / oneAndHalfTileSize));
        }
        columns = (int) Math.ceil(values / maxColumnsPerRow);
        rows = (int) Math.ceil(values / columns);

        paintRect.set(0, 0, width / columns, height / rows);
        return paintRect;
    }
}
