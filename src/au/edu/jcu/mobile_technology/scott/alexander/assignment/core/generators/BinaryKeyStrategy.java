package au.edu.jcu.mobile_technology.scott.alexander.assignment.core.generators;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.GameKeyStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates binary number sequence (keys) to a maximum value
 */
public class BinaryKeyStrategy extends GameKeyStrategy {

    /**
     * Generates the keys
     *
     * @return List of key values
     */
    @Override
    public List<Integer> generateKeys() {

        int sum = 0;
        int i = 1;
        ArrayList<Integer> keys = new ArrayList<Integer>();

        while (sum <= getMaximumValue()) {
            keys.add(i);
            sum += i;
            i += i;
        }
        return keys;
    }
}
