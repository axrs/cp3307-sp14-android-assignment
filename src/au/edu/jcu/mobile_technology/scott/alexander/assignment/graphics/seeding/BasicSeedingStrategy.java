package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.seeding;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.Game;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.GameCore;

import java.util.List;

/**
 * Basic Composite seeding strategy producing the default game map
 */
public class BasicSeedingStrategy extends CompositeMapSeedingStrategy {

    /**
     * Constructor
     */
    public BasicSeedingStrategy() {
        addStrategy(new CardSeedingStrategy());

        addStrategy(new GroundSeedingStrategy());

        GameCore game = Game.getInstance();

        if (game.getGameMode().canViewNext()) {
            addStrategy(new NextSignSeedingStrategy());
        }

        if (game.getGameMode().canViewPrevious()) {
            addStrategy(new PreviousSignSeedingStrategy());
        }

        if (!game.getGameMode().userIsGuessing()) {
            addStrategy(new CardOptionSeedingStrategy());
        }

        addStrategy(new EndMapSignSeedingStrategy());

        List<Integer> hintedCards = game.getHintIndices();
        addStrategy(new CardHintSeedingStrategy(hintedCards, true));
    }
}
