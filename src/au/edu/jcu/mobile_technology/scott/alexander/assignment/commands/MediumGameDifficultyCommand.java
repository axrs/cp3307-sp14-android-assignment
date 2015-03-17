package au.edu.jcu.mobile_technology.scott.alexander.assignment.commands;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.Command;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Game;

/**
 * MediumGameDifficultyCommand configures the game to use a medium difficulty number range
 */
public class MediumGameDifficultyCommand implements Command {

    @Override
    public void execute() {
        Game.setGameRange(1, 20);
    }
}
