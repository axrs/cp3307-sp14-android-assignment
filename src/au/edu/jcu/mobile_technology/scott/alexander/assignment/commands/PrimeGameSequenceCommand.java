package au.edu.jcu.mobile_technology.scott.alexander.assignment.commands;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.Command;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Game;

/**
 * PrimeGameSequenceCommand initialises the game sequence type as binary
 */
public class PrimeGameSequenceCommand implements Command {
    @Override
    public void execute() {
        Game.setSequence(Game.SequenceType.PRIME);
    }
}
