package au.edu.jcu.mobile_technology.scott.alexander.assignment.commands;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.Command;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Game;

/**
 * EasyGameDifficultyCommand configures the game to use a easy difficulty number range
 */
public class EasyGameDifficultyCommand implements Command {

    @Override
    public void execute() {
        Game.setGameRange(1, 10);
    }
}
