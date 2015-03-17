package au.edu.jcu.mobile_technology.scott.alexander.assignment.core.modes;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.Card;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.GameMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChallengeAIGameMode extends GameMode {

    List<Integer> indicatedKeys;

    /**
     * Gets a list containing all keys with the users number displayed
     *
     * @return List of keys
     */
    private List<Integer> getIndicatedKeys() {
        if (indicatedKeys == null) {
            indicatedKeys = new ArrayList<Integer>();
        }
        return indicatedKeys;
    }

    /**
     * Resets the game mode using the previous multiplier
     */
    @Override
    public void reset() {
        getIndicatedKeys().clear();
    }

    /**
     * Asks for an getAiGuess based on indicated keys
     *
     * @return AI Guess Value
     */
    @Override
    public int getAiGuess() {
        Random randomGenerator = new Random();

        int guess = 0;

        //Calculated Guess
        for (int i : getIndicatedKeys()) {
            guess += i;
        }

        //Only guess correctly 80% of the time!
        float chance = randomGenerator.nextFloat();
        if (chance <= 0.2f) {
            guess = randomGenerator.nextInt(getMaximum() - getMinimum()) + getMinimum();
        }

        return guess;
    }

    /**
     * Determines if the user is guessing the AI value
     *
     * @return True if user guessing, False if AI guessing
     */
    @Override
    public boolean userIsGuessing() {
        return false;
    }

    /**
     * Determines if the user can view the previous card in sequence
     *
     * @return True if able
     */
    @Override
    public boolean canViewPrevious() {
        return true;
    }

    /**
     * Determines if the user can view the next card in sequence
     *
     * @return True if able
     */
    @Override
    public boolean canViewNext() {
        return true;
    }

    /**
     * Determines if hints are to be displayed to the user
     *
     * @return True if hints are enabled
     */
    @Override
    public boolean hintsEnabled() {
        return false;
    }

    /**
     * Used to inform an AI player that the value is seen on the card.
     *
     * @param key  Generated key value
     * @param card Card with the value
     */
    @Override
    public void setValueIsOnCard(int key, Card card) {
        if (!getIndicatedKeys().contains(key)) {
            getIndicatedKeys().add(key);
        }
    }

    /**
     * Used to inform an AI player that the value is not seen on the card.
     *
     * @param key  Generated key value
     * @param card Card with the value
     */
    @Override
    public void setValueIsNotOnCard(int key, Card card) {
        if (getIndicatedKeys().contains(key)) {
            int index = getIndicatedKeys().indexOf(key);
            getIndicatedKeys().remove(index);
        }
    }
}
