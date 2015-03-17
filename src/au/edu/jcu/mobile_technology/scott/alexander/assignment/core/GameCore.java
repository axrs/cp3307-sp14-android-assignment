package au.edu.jcu.mobile_technology.scott.alexander.assignment.core;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.generators.BinaryKeyStrategy;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.modes.HintedFreePlayGameMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Core game class defining rules and playability
 */
public class GameCore {

    private List<Card> cards;
    private List<Integer> keys;

    private int minimumValue = 1;
    private int maximumValue = 10;

    private GameKeyStrategy strategy;
    private GameMode gameMode;
    private ScoreTracker scoreTracker;

    /**
     * Constructs a GameCore with a default range value of 1 to 10
     */
    public GameCore() {
    }

    /**
     * Constructs a GameCore with a specified maximum range value (default minimum is 1)
     *
     * @param maximumValue Maximum Range Value
     */
    protected GameCore(int maximumValue) {
        setMaximumValue(maximumValue);
    }

    /**
     * Constructs a GameCore with a specified Key generator strategy and maximum range
     *
     * @param strategy     Number sequence generator strategy
     * @param maximumValue Maximum Range Value
     */
    public GameCore(GameKeyStrategy strategy, int maximumValue) {
        setMaximumValue(maximumValue);
        setStrategy(strategy);
    }

    /**
     * Gets the key generator strategy
     *
     * @return Game key generator strategy
     */
    public GameKeyStrategy getStrategy() {
        if (strategy == null) {
            strategy = new BinaryKeyStrategy();
            strategy.setMaximumValue(maximumValue);
        }
        return strategy;
    }

    /**
     * Sets the key generator strategy
     *
     * @param strategy Key generator strategy to use
     */
    public void setStrategy(GameKeyStrategy strategy) {
        this.strategy = strategy;
        this.strategy.setMaximumValue(maximumValue);
    }

    /**
     * Gets the score tracker implementation
     *
     * @return Score tracker
     */
    public ScoreTracker getScoreTracker() {
        if (scoreTracker == null) {
            scoreTracker = new ScoreTracker();
        }
        return scoreTracker;
    }

    /**
     * Sets the score tracker implementation
     *
     * @param scoreTracker Tracker to use
     */
    public void setScoreTracker(ScoreTracker scoreTracker) {
        this.scoreTracker = scoreTracker;
    }

    /**
     * Gets the game play mode (rules and restrictions)
     *
     * @return Game play rules
     */
    public GameMode getGameMode() {
        if (gameMode == null) {
            gameMode = new HintedFreePlayGameMode();
        }
        return gameMode;
    }

    /**
     * Sets the game play mode (rules and restrictions)
     *
     * @param mode Game play rules
     */
    public void setGameMode(GameMode mode) {
        gameMode = mode;
    }

    /**
     * Indicates a user defined value is on a specified card index
     *
     * @param index Card value is displayed on
     */
    public void setValueIsOnCard(int index) {
        getGameMode().setValueIsOnCard(getKeys().get(index), getCards().get(index));
    }

    /**
     * Indicates a user defined value is not on a specified card index
     *
     * @param index Card value is not displayed on
     */
    public void setValueIsNotOnCard(int index) {
        getGameMode().setValueIsNotOnCard(getKeys().get(index), getCards().get(index));
    }

    /**
     * Gets the minimum card value
     *
     * @return Minimum card value
     */
    public int getMinimumValue() {
        return minimumValue;
    }

    /**
     * Sets the minimum card value (between 1 & 100)
     *
     * @param value Minimum Card Value
     */
    public void setMinimumValue(int value) {
        if (value <= 0 || value >= 100) {
            value = 1;
        }
        minimumValue = value;
    }

    /**
     * Gets the maximum card value
     *
     * @return Maximum card value
     */
    public int getMaximumValue() {
        return maximumValue;
    }

    /**
     * Sets the maximum card value
     *
     * @param value Card Value
     */
    public void setMaximumValue(int value) {
        if (value <= 0) {
            value = 10;
        } else if (value > 100) {
            value = 100;
        }
        maximumValue = value;
    }

    /**
     * Resets the game to default state ready for a replay
     */
    public void reset() {
        getStrategy().setMaximumValue(maximumValue);
        getGameMode().setup(minimumValue, maximumValue);
        getScoreTracker().setup(minimumValue, maximumValue);
        keys = null;
        cards = null;
    }

    /**
     * Indicates game end
     */
    public void finished() {
        getScoreTracker().onGameFinished();
    }

    /**
     * Gets a list of keys used to determine the card values
     *
     * @return List of keys
     */
    public List<Integer> getKeys() {
        if (keys == null) {
            keys = getStrategy().generateKeys();
        }
        return keys;
    }

    /**
     * Adds a key value to a specified index
     *
     * @param value Key value to add
     */
    public void addKey(int value) {
        if (!getKeys().contains(value)) {
            keys.add(value);
        }
    }

    /**
     * Gets a list of card indices a hint is to be displayed on
     *
     * @return Card indices
     */
    public List<Integer> getHintIndices() {

        GameMode gameMode = getGameMode();
        int totalCards = getCards().size();

        List<Integer> results = new ArrayList<Integer>();


        if (gameMode.hintsEnabled()) {
            for (int i = 0; i < totalCards; i++) {
                if (gameMode.displayHintForCard(getCards().get(i))) {
                    results.add(i);
                }
            }
        }
        return results;
    }

    /**
     * Gets a list of values for a specified card index
     *
     * @param card Index of the card
     * @return List of values on the card
     */
    public List<Integer> getCardValues(int card) {
        List<Integer> result = null;
        if (getCards().size() > card) {
            result = getCards().get(card).getValues();
        }
        return result;
    }

    /**
     * Gets a list of cards
     *
     * @return List of cards
     */
    public List<Card> getCards() {
        if (cards == null) {
            cards = new ArrayList<Card>();
            generateCards();
        }
        return cards;
    }

    /**
     * Adds a value to a card at a specified index
     *
     * @param card  Index of the card
     * @param value Value to add
     */
    public void addToCard(int card, int value) {
        if (getCards().size() > card) {
            getCards().get(card).add(value);
        } else {
            getCards().add(new Card(value));
        }
    }

    /**
     * Generates all associated cards for the given multiplier
     */
    private void generateCards() {
        for (int i = getMinimumValue(); i <= getMaximumValue(); i++) {
            int valueToFind = i;

            for (int j = getKeys().size() - 1; j >= 0; j--) {
                if (valueToFind >= getKeys().get(j)) {
                    addToCard(j, i);
                    valueToFind -= getKeys().get(j);
                }
            }
        }
    }
}
