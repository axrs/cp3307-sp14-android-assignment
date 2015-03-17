package au.edu.jcu.mobile_technology.scott.alexander.assignment.commands;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.Command;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Game;

/**
 * BinaryGameSequenceCommand initialises the game sequence type as binary
 */
public class BinaryGameSequenceCommand implements Command {
    @Override
    public void execute() {
        Game.setSequence(Game.SequenceType.BINARY);
    }
}
