package au.edu.jcu.mobile_technology.scott.alexander.assignment.commands;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.Command;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Game;

/**
 * CustomGameDifficultyCommand configures the game to use a custom difficulty number range as specified
 */
public class CustomGameDifficultyCommand implements Command {

    private int minimum;
    private int maximum;

    /**
     * Constructs the command object configured to a specified range
     *
     * @param minimum Minimum value of the range
     * @param maximum Maximum value of the range
     */
    public CustomGameDifficultyCommand(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    @Override
    public void execute() {
        Game.setGameRange(minimum, maximum);
    }
}
