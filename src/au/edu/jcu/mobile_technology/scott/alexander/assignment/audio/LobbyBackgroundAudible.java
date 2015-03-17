package au.edu.jcu.mobile_technology.scott.alexander.assignment.audio;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.R;

/**
 * Background lobby music audible
 */
public class LobbyBackgroundAudible extends Audible {

    /**
     * Determines if the audible is intended to be looped
     *
     * @return True if looped
     */
    @Override
    public boolean isLooped() {
        return true;
    }

    /**
     * Gets the intended left speaker volume
     *
     * @return Left speaker volume
     */
    @Override
    public float getLeftVolume() {
        return 0.75f;
    }

    /**
     * Gets the intended right speaker volume
     *
     * @return Right speaker volume
     */
    @Override
    public float getRightVolume() {
        return 0.75f;
    }

    /**
     * Gets the LobbyBackgroundAudible raw Id reference
     *
     * @return Android LobbyBackgroundAudible Raw Resource Id
     */
    @Override
    public int getResourceId() {
        return R.raw.lobby;
    }
}
