package au.edu.jcu.mobile_technology.scott.alexander.assignment.view;


/**
 * Swipe Action Handler Interface
 */
public interface SwipeActionHandler {

    /**
     * Fires on a swipe to the right gesture
     */
    public void onSwipeRight();

    /**
     * Fires on a swipe to the left gesture
     */
    public void onSwipeLeft();

    /**
     * Fires on a swipe up gesture
     */
    public void onSwipeTop();

    /**
     * Fires on a swipe down gesture
     */
    public void onSwipeBottom();
}
