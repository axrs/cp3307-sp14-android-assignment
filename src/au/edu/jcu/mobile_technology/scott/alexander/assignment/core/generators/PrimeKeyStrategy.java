package au.edu.jcu.mobile_technology.scott.alexander.assignment.core.generators;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.GameKeyStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates prime number sequence (keys) to a maximum value
 */
public class PrimeKeyStrategy extends GameKeyStrategy {

    /**
     * Generates the keys required to generate values to the maximum range
     *
     * @return List of key values
     */
    @Override
    public List<Integer> generateKeys() {
        //1 is a prime number, but throws off calculations
        int value = 2;
        ArrayList<Integer> keys = new ArrayList<Integer>();

        int sum = 1;
        while (sum <= getMaximumValue()) {
            Boolean valueIsPrime = true;
            for (int j : keys) {
                if (value % j == 0) {
                    valueIsPrime = false;
                }
            }
            if (valueIsPrime) {
                keys.add(value);
                sum += value;
            }
            value++;
        }
        //Add 1 to the start of the keys
        keys.add(0, 1);
        return keys;
    }
}
