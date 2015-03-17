package au.edu.jcu.mobile_technology.scott.alexander.assignment.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

/**
 * Animation based Surface View
 */
public abstract class AnimatedView extends SurfaceView implements AnimationController {

    //LogCat debugging tag
    private static final String TAG = "AnimatedView";

    //Animation game thread
    private AnimationThread thread;
    //Average FPS value
    private double averageFPS = 0.0;

    public AnimatedView(Context context) {
        super(context);
        init();
    }

    public AnimatedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimatedView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * Initialises the AnimatedView
     */
    private void init() {
    }

    /**
     * Gets the average FPS
     *
     * @return Average FPS
     */
    public double getAverageFPS() {
        return averageFPS;
    }

    /**
     * Sets the current average FPS
     *
     * @param averageFPS Current average FPS
     */
    public void setAverageFPS(double averageFPS) {
        this.averageFPS = averageFPS;
    }

    /**
     * Starts the animation view and associated thread
     */
    @Override
    public void start() {
        if (thread == null || thread.getState() == Thread.State.TERMINATED) {
            thread = new AnimationThread(getHolder(), this);
            thread.setRunning(true);
            thread.start();
        }

        Log.d(TAG, String.valueOf(thread.getState()));
    }

    /**
     * Stops the animation view and associated thread
     */
    public void stop() {
        boolean retry = true;
        while (retry) {
            try {
                Log.d(TAG, "AnimationThread stop attempt.");

                thread.interrupt();
                thread.setRunning(false);
                retry = false;
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        Log.d(TAG, "AnimationThread gracefully shutdown.");
    }


    /**
     * Called to update any animation objects
     */
    @Override
    public abstract void update();

    /**
     * Renders any views to the canvas
     *
     * @param canvas Canvas to render
     */
    @Override
    public abstract void render(Canvas canvas);
}
