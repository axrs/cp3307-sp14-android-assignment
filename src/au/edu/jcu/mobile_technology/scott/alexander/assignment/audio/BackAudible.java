package au.edu.jcu.mobile_technology.scott.alexander.assignment.audio;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.R;

/**
 * Reverse (back) navigation audible notification
 */
public class BackAudible extends Audible {

    /**
     * Gets the Android resource Id reference
     *
     * @return Android Resource Id
     */
    @Override
    public int getResourceId() {
        return R.raw.back;
    }
}

