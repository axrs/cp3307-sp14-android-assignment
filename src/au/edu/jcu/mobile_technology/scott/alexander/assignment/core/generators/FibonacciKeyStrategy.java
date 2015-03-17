package au.edu.jcu.mobile_technology.scott.alexander.assignment.core.generators;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.GameKeyStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates fibonacci number sequence (keys) to a maximum value
 */
public class FibonacciKeyStrategy extends GameKeyStrategy {

    static List<Integer> _computedKeys;
    private ArrayList<Integer> _keys;

    /**
     * Gets a list of already defined keys for the sequence
     *
     * @return Predefined keys
     */
    static List<Integer> getComputedKeys() {
        if (_computedKeys == null) {
            _computedKeys = new ArrayList<Integer>();
            _computedKeys.add(0);
            _computedKeys.add(1);
        }
        return _computedKeys;
    }

    /**
     * Recursive implementation to evaluate fibonacci up to the nth degree
     *
     * @param n Nth value of fibonacci to calculate to
     * @return Calculated value of n
     */
    private static int fibonacci(int n) {
        if (getComputedKeys().size() - 1 < n) {
            getComputedKeys().add((fibonacci(n - 1)) + fibonacci(n - 2));
        }
        return getComputedKeys().get(n);
    }

    /**
     * Gets a list of fibonacci keys
     *
     * @return Fibonacci keys
     */
    private List<Integer> getKeys() {
        if (_keys == null) {
            _keys = new ArrayList<Integer>();
        }
        return _keys;
    }

    /**
     * Adds a value to the list of fibonacci keys
     *
     * @param value Value to add
     */
    private void addKeyValue(int value) {
        if (!getKeys().contains(value)) {
            getKeys().add(value);
        }
    }

    /**
     * Generates the keys required to calculate to the maximum range
     *
     * @return List of key values
     */
    @Override
    public List<Integer> generateKeys() {
        int hitTest = 1;
        while (fibonacci(hitTest) < getMaximumValue()) {
            hitTest++;
        }
        int sum = 0;
        int value;

        for (int i = 2; i < getComputedKeys().size(); i++) {
            value = getComputedKeys().get(i);
            sum += value;
            addKeyValue(value);

            if (sum >= getMaximumValue()) {
                break;
            }
        }

        return getKeys();
    }
}

