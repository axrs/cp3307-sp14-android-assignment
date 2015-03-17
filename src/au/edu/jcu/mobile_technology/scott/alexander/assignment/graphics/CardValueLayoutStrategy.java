package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics;

import android.graphics.Rect;


/**
 * Card Value Layout Strategy is used to determine packing rectangles for an area given a set number of values to fit
 */
public interface CardValueLayoutStrategy {

    /**
     * Gets the value tile size
     *
     * @param width            Container Width
     * @param height           Container Height
     * @param tileSize         game tile size
     * @param totalValuesToFit Total number of values to fit within container
     * @return Best fit rectangle
     */
    public Rect getRect(int width, int height, int tileSize, int totalValuesToFit);
}
