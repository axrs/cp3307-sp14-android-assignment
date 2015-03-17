package au.edu.jcu.mobile_technology.scott.alexander.assignment.view;

import android.graphics.Canvas;

/**
 * Basic AnimationController
 */
public interface AnimationController {

    /**
     * Called when the View is to update components
     */
    public void update();

    /**
     * Called by the AnimationThread when the Canvas has been modified.
     *
     * @param canvas Canvas to render
     */
    public void render(Canvas canvas);

    /**
     * Called by the AnimationThread when the average frames per second has changed.
     *
     * @param fps New Calculated FPS
     */
    public void setAverageFPS(double fps);


    /**
     * Starts the animation controller
     */
    public void start();

}
