package au.edu.jcu.mobile_technology.scott.alexander.assignment;


import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.GameCore;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.GameKeyStrategy;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.generators.BinaryKeyStrategy;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.generators.FibonacciKeyStrategy;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.generators.PrimeKeyStrategy;

/**
 * Singleton game wrapper and helper class for the GameCore
 */
public class Game {

    private static GameCore core;

    /**
     * Protected Constructor to prevent instantiation
     */
    protected Game() {
    }

    /**
     * Gets the instance of the game
     *
     * @return Game instance
     */
    public static GameCore getInstance() {
        if (core == null) {
            core = new GameCore();
        }
        return core;
    }

    /**
     * Sets the games upper and lower generation range
     *
     * @param minimum Lowest possible value
     * @param maximum Highest possible value
     */
    public static void setGameRange(int minimum, int maximum) {
        getInstance().setMaximumValue(maximum);
        getInstance().setMinimumValue(minimum);
    }

    /**
     * Resets the GameCore
     *
     * @param sequenceType Number Sequence to use
     * @param minimumValue Lowest possible value
     * @param maximumValue Highest possible value
     */
    public static void setSequence(SequenceType sequenceType, int minimumValue, int maximumValue) {
        getInstance().setMaximumValue(minimumValue);
        getInstance().setMaximumValue(maximumValue);
        setSequence(sequenceType);
    }

    /**
     * Calls a game reset
     */
    public static void reset() {
        getInstance().reset();
    }

    /**
     * Called to indicate the game has finished
     */
    public static void finished() {
        getInstance().finished();
    }

    /**
     * Used to inform an AI player that the value is seen on the card at a specified index
     *
     * @param index Card index
     */
    public static void setValueIsOnCard(int index) {
        getInstance().setValueIsOnCard(index);
    }

    /**
     * Used to inform an AI player that the value is not seen on the card at a specified index
     *
     * @param index Card index
     */
    public static void setValueIsNotOnCard(int index) {
        getInstance().setValueIsNotOnCard(index);
    }

    /**
     * Is the specified value the AIs secret number
     *
     * @param value Value to check
     * @return True if correct
     */
    public static boolean isSecret(int guess) {
        return Game.getInstance().getGameMode().isSecret(guess);
    }

    /**
     * Gets the users score for the current cycle
     *
     * @return Score
     */
    public static long getScore() {
        return Game.getInstance().getScoreTracker().getScore();
    }

    /**
     * Resets the GameCore keeping the current range
     *
     * @param sequenceType Number Sequence to use
     */
    public static void setSequence(SequenceType sequenceType) {
        GameKeyStrategy strategy;
        switch (sequenceType) {
            case FIBONACCI:
                strategy = new FibonacciKeyStrategy();
                break;
            case PRIME:
                strategy = new PrimeKeyStrategy();
                break;
            default:
                strategy = new BinaryKeyStrategy();
        }
        getInstance().setStrategy(strategy);
    }

    /**
     * Number Key Sequence Types
     */
    public enum SequenceType {
        BINARY, FIBONACCI, PRIME
    }
}
