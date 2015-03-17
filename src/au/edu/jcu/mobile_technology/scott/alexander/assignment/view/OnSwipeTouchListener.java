package au.edu.jcu.mobile_technology.scott.alexander.assignment.view;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * OnSwipeTouchListener listens to view touch events, firing recognised gestures
 */
public class OnSwipeTouchListener implements View.OnTouchListener {
    //Gesture detector
    private final GestureDetector detector;
    //Swipe action handle
    private SwipeActionHandler handler;

    /**
     * Constructs the listener within a specified Context, notifying a specified handler of recognised events
     *
     * @param context Application context
     * @param handler Action handler
     */
    public OnSwipeTouchListener(Context context, SwipeActionHandler handler) {
        detector = new GestureDetector(context, new SimpleGestureListener());
        this.handler = handler;
    }

    /**
     * On touch
     *
     * @param view
     * @param motionEvent
     * @return
     */
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        return detector.onTouchEvent(motionEvent);
    }

    /**
     * Notifies a receiver of a swiping right
     */
    public void onSwipeRight() {
        handler.onSwipeRight();
    }

    /**
     * Notifies a receiver of a swiping left
     */
    public void onSwipeLeft() {
        handler.onSwipeLeft();
    }

    /**
     * Notifies a receiver of a swiping up
     */
    public void onSwipeTop() {
        handler.onSwipeTop();
    }

    /**
     * Notifies a receiver of a swiping down
     */
    public void onSwipeBottom() {
        handler.onSwipeBottom();
    }

    /**
     * Gesture recogniser class
     */
    private final class SimpleGestureListener extends GestureDetector.SimpleOnGestureListener {

        //Minimum swiping threshold
        private static final int THRESHOLD = 100;
        //Minimum required swiping velocity
        private static final int VELOCITY = 100;

        //LogCat debugging tag for class identification
        private static final String TAG = "SimpleGestureListener";

        /**
         * Override the on down event to implement custom handling
         *
         * @param e Motion Event
         * @return True
         */
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        /**
         * Override of an onFling event to detect directions
         *
         * @param e1        Fling start event
         * @param e2        Current fling event location
         * @param velocityX Horizontal fling velocity
         * @param velocityY Vertical fling velocity
         * @return True if handled by recognised gesture
         */
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float difX = e2.getX() - e1.getX();
                float difY = e2.getY() - e1.getY();
                float deltaY = Math.abs(difY);
                float deltaX = Math.abs(difX);

                if (deltaX > deltaY) {
                    if (deltaX > THRESHOLD && Math.abs(velocityX) > VELOCITY) {
                        if (difX > 0.0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                } else {
                    if (deltaY > THRESHOLD && Math.abs(velocityY) > VELOCITY) {
                        if (difY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                        result = true;
                    }
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
            return result;
        }
    }
}
