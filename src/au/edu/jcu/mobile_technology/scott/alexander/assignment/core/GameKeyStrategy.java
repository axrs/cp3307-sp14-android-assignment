package au.edu.jcu.mobile_technology.scott.alexander.assignment.core;

import java.util.List;

public abstract class GameKeyStrategy {

    private int _maxValue;

    protected GameKeyStrategy() {
    }

    protected GameKeyStrategy(int maximumValue) {
        _maxValue = maximumValue;
    }

    public int getMaximumValue() {
        return _maxValue;
    }

    public void setMaximumValue(int value) {
        if (value <= 0) {
            value = 10;
        } else if (value > 100) {
            value = 100;
        }
        _maxValue = value;
    }

    /**
     * Generates the _keys required to calculate the card values
     */
    public abstract List<Integer> generateKeys();
}
