package au.edu.jcu.mobile_technology.scott.alexander.assignment.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;


/**
 * Sliding scene based animation view
 */
public abstract class SceneBasedAnimatedView extends AnimatedView implements SurfaceHolder.Callback {

    //Current scene index (assuming each scene is exactly one width of the view)
    private int currentSceneIndex;

    //Camera view left location
    private int cameraLeft;
    //Calculated scene left location
    private int sceneLeft;
    private int totalScenes;
    //Camera slide_left amount
    private int cameraTransitionAmount;

    //Current ticks since game start
    private long frameTicker;
    //Duration between animation frames (1seconds/intended frames)
    private int framePeriod;


    public SceneBasedAnimatedView(Context context) {
        super(context);
        init();
    }

    public SceneBasedAnimatedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SceneBasedAnimatedView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    /**
     * Initialises the view
     */
    private void init() {
        getHolder().addCallback(this);
        frameTicker = 01;
        framePeriod = 1000 / 50;
    }

    /**
     * Handles a surface view size changed event, calculating a new camera transition amount
     *
     * @param surfaceHolder The SurfaceHolder whose surface has changed.
     * @param format        The new PixelFormat of the surface.
     * @param width         The new width of the surface.
     * @param height        The new height of the surface.
     */
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        sceneLeft = getWidth() * currentSceneIndex;
        cameraLeft = sceneLeft;
        cameraTransitionAmount = getWidth() / 50;
    }

    /**
     * Sets the scene to the specified index
     *
     * @param sceneIndex New scene index
     * @return True if scene changed
     */
    public boolean setScene(int sceneIndex) {
        boolean result = false;
        if (sceneIndex < 0) {
            sceneIndex = 0;
        }

        if (sceneIndex < getTotalScenes()) {
            currentSceneIndex = sceneIndex;
            result = true;
        }
        //Calculates the intended screen left position
        sceneLeft = Math.round(currentSceneIndex * getWidth());
        return result;
    }

    /**
     * Gets the total number of scenes in the view
     *
     * @return Total scene count
     */
    public int getTotalScenes() {
        return totalScenes;
    }

    /**
     * Sets the total number of scenes in the stage
     *
     * @param totalScenes Total scenes
     */
    public void setTotalScenes(int totalScenes) {
        this.totalScenes = totalScenes;
    }

    /**
     * Gets the current scene index
     *
     * @return Current scene index
     */
    public int getCurrentSceneIndex() {
        return currentSceneIndex;
    }

    /**
     * Indicates if the scene is required to be offset
     *
     * @param offset Offset amount
     * @return True if offset required
     */
    public boolean offsetScene(int offset) {
        if (sceneLeft == cameraLeft) {
            int intendedScene = currentSceneIndex + offset;
            return setScene(intendedScene);
        }
        return false;
    }

    /**
     * Updates the scene camera, transitioning if required between scenes
     */
    @Override
    public void update() {
        long gameTime = System.currentTimeMillis();
        if (gameTime > frameTicker + framePeriod) {
            frameTicker = gameTime;

            //Calculate the new camera position

            //Shift amount is too close to the scene location, snap to scene

            if (Math.abs(sceneLeft - cameraLeft) < cameraTransitionAmount) {
                cameraLeft = sceneLeft;
            }
            // Shift camera in direction as required
            else if (cameraLeft < sceneLeft) {
                cameraLeft += cameraTransitionAmount;
            } else if (cameraLeft > sceneLeft) {
                cameraLeft -= cameraTransitionAmount;
            }
        }

    }

    /**
     * Renders the canvas to the view
     *
     * @param canvas Canvas to render
     */
    @Override
    public void render(Canvas canvas) {
        canvas.translate(-cameraLeft, 0);
    }
}
