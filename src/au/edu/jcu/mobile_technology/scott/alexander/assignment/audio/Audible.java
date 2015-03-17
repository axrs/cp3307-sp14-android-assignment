package au.edu.jcu.mobile_technology.scott.alexander.assignment.audio;

import android.support.v4.util.ArrayMap;
import android.util.Log;

/**
 * Audible Interface for use with the AudioEngine
 */
public abstract class Audible {
    private static ArrayMap<String, Integer> references;

    /**
     * Gets the audible reference cache
     *
     * @return Audible reference cache
     */
    private ArrayMap<String, Integer> getReferences() {
        if (references == null) {
            references = new ArrayMap<String, Integer>();
        }
        return references;
    }

    /**
     * Gets the AudioEngine sound reference
     *
     * @return AudioEngine sound reference
     */
    public int getSoundId() {
        if (getReferences().containsKey(getClass().toString())) {
            return getReferences().get(getClass().toString());
        }
        return 0;
    }

    /**
     * Sets the AudioEngine sound reference
     *
     * @param reference Sound reference
     */
    public void setSoundId(int reference) {
        Log.d("Audible", "Adding Reference For" + getClass().toString());
        getReferences().put(getClass().toString(), reference);
    }

    /**
     * Determines if the Audible is intended to loop (Defaults to false)
     *
     * @return True if to be looped
     */
    public boolean isLooped() {
        return false;
    }

    /**
     * Gets the left speaker volume
     *
     * @return left volume value (range = 0.0 to 1.0)
     */
    public float getLeftVolume() {
        return 1.0f;
    }

    /**
     * Gets the right speaker volume
     *
     * @return right volume value (range = 0.0 to 1.0)
     */
    public float getRightVolume() {
        return 1.0f;
    }

    /**
     * Gets the raw resource id
     *
     * @return resource id
     */
    public abstract int getResourceId();

}
