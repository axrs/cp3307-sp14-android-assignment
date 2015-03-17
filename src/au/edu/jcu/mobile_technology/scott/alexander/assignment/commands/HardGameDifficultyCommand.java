package au.edu.jcu.mobile_technology.scott.alexander.assignment.commands;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.Command;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Game;

/**
 * HardGameDifficultyCommand configures the game to use a hard difficulty number range
 */
public class HardGameDifficultyCommand implements Command {

    @Override
    public void execute() {
        Game.setGameRange(1, 50);
    }
}
