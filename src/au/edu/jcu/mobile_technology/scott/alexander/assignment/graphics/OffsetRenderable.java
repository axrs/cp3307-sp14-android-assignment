package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics;

import android.graphics.Canvas;

/**
 * A base interface for rendering and painting to a canvas at a specified offset
 */
public interface OffsetRenderable {
    /**
     * Causes this renderable to render itself (offset from the origin) using the provided canvas
     *
     * @param canvas Canvas to render onto
     */
    public void render(Canvas canvas, int offsetLeft, int offsetTop);
}

