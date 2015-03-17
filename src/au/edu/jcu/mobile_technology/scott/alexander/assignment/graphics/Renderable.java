package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics;

import android.graphics.Canvas;

/**
 * A base interface for rendering and painting to a canvas
 */
public interface Renderable {
    /**
     * Causes this renderable to render itself using the provided canvas
     *
     * @param canvas Canvas to render onto
     */
    public void render(Canvas canvas);
}
