package au.edu.jcu.mobile_technology.scott.alexander.assignment.view;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * @author impaler
 *         <p/>
 *         The Main thread which contains the game loop. The thread must have access to
 *         the surface view and holder to trigger events every game tick.
 *         <p/>
 *         Reference Android Game Loops - MySecretRoom. 2014. Android Game Loops - MySecretRoom. [ONLINE]
 *         Available at: http://www.mysecretroom.com/www/programming-and-software/android-game-loops. [Accessed 14 April 2014].
 *         <p/>
 *         Altered to improve FPS performance and reduce lock holding times
 */
public class AnimationThread extends Thread {

    private static final String TAG = AnimationThread.class.getSimpleName();

    // desired fps
    private final static int MAX_FPS = 50;
    // the frame period
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;
    // maximum number of frames to be skipped
    private final static int MAX_FRAME_SKIPS = 5;
    // we'll be reading the stats every second
    private final static int STAT_INTERVAL = 1000; //ms
    // the average will be calculated by storing
    // the last n FPSs
    private final static int FPS_HISTORY_NR = 10;
    // Surface holder that can access the physical surface
    private final SurfaceHolder surfaceHolder;
    // last time the status was stored
    private long lastStatusStore = 0;
    // the status time counter
    private long statusIntervalTimer = 0l;
    // number of frames skipped since the game started
    private long totalFramesSkipped = 0l;
    // number of frames skipped in a store cycle (1 sec)
    private long framesSkippedPerStatCycle = 0l;
    // number of rendered frames in an interval
    private int frameCountPerStatCycle = 0;
    private long totalFrameCount = 0l;
    // the last FPS values
    private double fpsStore[];
    // the number of times the stat has been read
    private long statsCount = 0;
    // the average FPS since the game started
    private double averageFps = 0.0;
    // The actual view that handles inputs
    // and draws to the surface
    private AnimationController gameView;

    // flag to hold game state
    private boolean running;

    public AnimationThread(SurfaceHolder holder, AnimationController view) {
        super();
        surfaceHolder = holder;
        gameView = view;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        Canvas canvas;
        Log.d(TAG, "Starting game loop");
        // initialise timing elements for stat gathering
        initTimingElements();

        long beginTime;        // the time when the cycle begun
        long timeDiff;        // the time it took for the cycle to execute
        int sleepTime;        // ms to sleep (<0 if we're behind)
        int framesSkipped;    // number of frames being skipped

        sleepTime = 0;
        Log.d(TAG, "Running game loop");

        while (running) {
            canvas = null;

            beginTime = System.currentTimeMillis();
            framesSkipped = 0;    // resetting the frames skipped
            // update game state
            gameView.update();

            // try locking the canvas for exclusive pixel editing
            // in the surface
            canvas = this.surfaceHolder.lockCanvas();

            // in case of an exception the surface is not left in
            // an inconsistent state
            if (canvas != null) {
                // render state to the screen
                // draws the canvas on the panel
                synchronized (surfaceHolder) {
                    this.gameView.render(canvas);
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
            }

            // calculate how long did the cycle take
            timeDiff = System.currentTimeMillis() - beginTime;
            // calculate sleep time
            sleepTime = (int) (FRAME_PERIOD - timeDiff);

            if (sleepTime > 0) {
                // if sleepTime > 0 we're OK
                try {
                    // send the thread to sleep for a short period
                    // very useful for battery saving
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    Log.e(TAG, e.toString());
                }
            }

            while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
                // we need to catch up
                gameView.update(); // update without rendering
                sleepTime += FRAME_PERIOD;    // add frame period to check if in next frame
                framesSkipped++;
            }


            /*
            if (framesSkipped > 0) {
                Log.d(TAG, "Skipped:" + framesSkipped);
            }
            */


            // for statistics
            framesSkippedPerStatCycle += framesSkipped;
            // calling the routine to store the gathered statistics
            storeStats();
            // end finally
        }
    }

    /**
     * The statistics - it is called every cycle, it checks if time since last
     * store is greater than the statistics gathering period (1 sec) and if so
     * it calculates the FPS for the last period and stores it.
     * <p/>
     * It tracks the number of frames per period. The number of frames since
     * the start of the period are summed up and the calculation takes part
     * only if the next period and the frame count is reset to 0.
     */
    private void storeStats() {
        frameCountPerStatCycle++;
        totalFrameCount++;

        // check the actual time
        statusIntervalTimer += (System.currentTimeMillis() - statusIntervalTimer);

        if (statusIntervalTimer >= lastStatusStore + STAT_INTERVAL) {
            // calculate the actual frames pers status check interval
            double actualFps = (double) (frameCountPerStatCycle / (STAT_INTERVAL / 1000));

            //stores the latest fps in the array
            fpsStore[(int) statsCount % FPS_HISTORY_NR] = actualFps;

            // increase the number of times statistics was calculated
            statsCount++;

            double totalFps = 0.0;
            // sum up the stored fps values
            for (int i = 0; i < FPS_HISTORY_NR; i++) {
                totalFps += fpsStore[i];
            }

            // obtain the average
            if (statsCount < FPS_HISTORY_NR) {
                // in case of the first 10 triggers
                averageFps = totalFps / statsCount;
            } else {
                averageFps = totalFps / FPS_HISTORY_NR;
            }
            // saving the number of total frames skipped
            totalFramesSkipped += framesSkippedPerStatCycle;
            // resetting the counters after a status record (1 sec)
            framesSkippedPerStatCycle = 0;
            statusIntervalTimer = 0;
            frameCountPerStatCycle = 0;

            statusIntervalTimer = System.currentTimeMillis();
            lastStatusStore = statusIntervalTimer;
            gameView.setAverageFPS(averageFps);
        }
    }

    private void initTimingElements() {
        // initialise timing elements
        fpsStore = new double[FPS_HISTORY_NR];
        for (int i = 0; i < FPS_HISTORY_NR; i++) {
            fpsStore[i] = 0.0;
        }
        Log.d(TAG + ".initTimingElements()", "Timing elements for stats initialised");
    }

}