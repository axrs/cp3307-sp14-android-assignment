package au.edu.jcu.mobile_technology.scott.alexander.assignment.core;

/**
 * Empty ScoreTracker implementation defining the required structure of the score tracker
 */
public class ScoreTracker {
    /**
     * Sets up the score tracking
     *
     * @param minValue Lowest number available to guess
     * @param maxValue Highest number available to guess
     */
    public void setup(int minValue, int maxValue) {
        //Does nothing
    }


    public void onGameFinished() {
        //Does nothing
    }

    /**
     * Resets the score implementation
     */
    public void reset() {
        //Does nothing
    }

    /**
     * Determines if the game mode is tracking a score
     *
     * @return True if tracking score.
     */
    public boolean isTracked() {
        return false;
    }

    /**
     * Gets the current score value
     *
     * @return Score
     */
    public long getScore() {
        return 0;
    }
}
