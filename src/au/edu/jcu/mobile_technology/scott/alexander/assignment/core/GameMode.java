package au.edu.jcu.mobile_technology.scott.alexander.assignment.core;


/**
 * Game mode class used to define specific methods of play
 */
public abstract class GameMode {

    /**
     * Highest Possible Secret Number
     */
    private int maximum;

    /**
     * Lowest Possible Secret Number
     */
    private int minimum;

    /**
     * Sets up the score tracking
     *
     * @param minimum Lowest number available to guess
     * @param maximum Highest number available to guess
     */
    public void setup(int minimum, int maximum) {
        this.maximum = maximum;
        this.minimum = minimum;
        reset();
    }

    /**
     * Gets the minimum value in the range
     *
     * @return Minimum range value
     */
    public int getMinimum() {
        return minimum;
    }

    /**
     * Sets the minimum range value
     *
     * @param minimum Minimum range value
     */
    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    /**
     * Gets the maximum range value
     *
     * @return Maximum range value
     */
    public int getMaximum() {
        return maximum;
    }

    /**
     * Sets the maximum range value
     *
     * @param maximum Maximum range value
     */
    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    /**
     * Resets the game mode
     */
    public abstract void reset();

    /**
     * Is the specified value the AIs secret number
     *
     * @param value Value to check
     * @return True if correct
     */
    public boolean isSecret(int value) {
        return false;
    }

    /**
     * Asks for an getAiGuess
     *
     * @return AI Guess Value
     */
    public int getAiGuess() {
        return 0;
    }

    /**
     * Determines if the user is guessing the AI value
     *
     * @return True if user guessing, False if AI guessing
     */
    public boolean userIsGuessing() {
        return true;
    }

    /**
     * Determines if the user can view the previous card in sequence
     *
     * @return True if able
     */
    public boolean canViewPrevious() {
        return false;
    }

    /**
     * Determines if the user can view the next card in sequence
     *
     * @return True if able
     */
    public boolean canViewNext() {
        return true;
    }

    /**
     * Determines if hints are to be displayed to the user
     *
     * @return True if hints are enabled
     */
    public boolean hintsEnabled() {
        return true;
    }

    /**
     * Determines if a specified value is displayed on a given card
     *
     * @param value Value to find
     * @param card  Card to look on
     * @return True if found
     */
    public boolean isValueDisplayedOnCard(int value, Card card) {
        return card.contains(value);
    }

    /**
     * Determines if the games secret value is displayed on a specified card
     *
     * @param card Card to search
     * @return True if value on it
     */
    public boolean isValueDisplayedOnCard(Card card) {
        return false;
    }

    /**
     * Used to inform an AI player that the value is seen on the card.
     *
     * @param key  Generated key value
     * @param card Card with the value
     */
    public void setValueIsOnCard(int key, Card card) {
    }

    /**
     * Used to inform an AI player that the value is not seen on the card.
     *
     * @param key  Generated key value
     * @param card Card with the value
     */
    public void setValueIsNotOnCard(int key, Card card) {
    }

    /**
     * Used to determine if a hint should be displayed for the specified card
     *
     * @param card Card with the value
     * @return true if hints is to be displayed
     */
    public boolean displayHintForCard(Card card) {
        return false;
    }
}
