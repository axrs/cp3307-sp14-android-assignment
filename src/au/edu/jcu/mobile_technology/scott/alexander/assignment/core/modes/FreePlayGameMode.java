package au.edu.jcu.mobile_technology.scott.alexander.assignment.core.modes;


import android.util.Log;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.Card;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.GameMode;

import java.util.Random;

/**
 * Hint less AI Controlled Random Number GameMode implementation
 */
public class FreePlayGameMode extends GameMode {

    /**
     * Current Secret Number
     */
    private int secret;

    /**
     * Resets the secret number
     */
    @Override
    public void reset() {
        secret = new Random().nextInt(getMaximum() - getMinimum()) + getMinimum();
        Log.d("SECRET NUMBER", String.valueOf(secret));
    }

    /**
     * Determines if the specified value is the AIs secret
     *
     * @param value Value to check
     * @return True if correct
     */
    @Override
    public boolean isSecret(int value) {
        return (value == secret);
    }

    /**
     * Determines if the user is able to view previous card
     *
     * @return True if able
     */
    @Override
    public boolean canViewPrevious() {
        return true;
    }

    /**
     * Determines if the user is able to see hints for the cards
     *
     * @return True if able
     */
    @Override
    public boolean hintsEnabled() {
        return false;
    }

    /**
     * Determines if a card contains the AIs secret value
     *
     * @param card Card to search
     * @return True if contained
     */
    @Override
    public boolean isValueDisplayedOnCard(Card card) {
        return card.contains(secret);
    }

    /**
     * Determines if a hint should be displayed for the specified card
     *
     * @param card Card with the value
     * @return True if hint is to be shown
     */
    @Override
    public boolean displayHintForCard(Card card) {
        return hintsEnabled() && card.contains(secret);
    }
}
