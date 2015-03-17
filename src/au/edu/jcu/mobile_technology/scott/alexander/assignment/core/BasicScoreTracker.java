package au.edu.jcu.mobile_technology.scott.alexander.assignment.core;

/**
 * Basic time based score tracker
 */
public class BasicScoreTracker extends ScoreTracker {

    Long timeStart;
    Long timeEnd;
    long score = 0;
    int range;

    /**
     * Initialises the score tracker with the provided range
     *
     * @param minValue Lowest number available to guess
     * @param maxValue Highest number available to guess
     */
    @Override
    public void setup(int minValue, int maxValue) {
        range = (maxValue - minValue) * 10000000;
        reset();
    }

    /**
     * Stops the current score tracking and calculates the score
     */
    @Override
    public void onGameFinished() {
        timeEnd = System.currentTimeMillis();
        long duration = timeEnd - timeStart;
        score = range / duration;
    }

    /**
     * Resets the score tracker
     */
    @Override
    public void reset() {
        timeStart = System.currentTimeMillis();
    }

    /**
     * Determines if the score is being tracked
     *
     * @return True if tracking is enabled
     */
    @Override
    public boolean isTracked() {
        return true;
    }

    /**
     * Gets the users score for the current cycle
     *
     * @return Score
     */
    @Override
    public long getScore() {
        return score;
    }
}
