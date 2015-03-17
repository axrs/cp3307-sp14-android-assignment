package au.edu.jcu.mobile_technology.scott.alexander.assignment.commands;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.Command;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Game;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.ScoreTracker;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.modes.FreePlayGameMode;

/**
 * HardGameDifficultyCommand configures the game to a free play mode without high scoring
 */
public class FreePlayGameModeCommand implements Command {
    @Override
    public void execute() {
        Game.getInstance().setGameMode(new FreePlayGameMode());

        Game.getInstance().setScoreTracker(new ScoreTracker());
    }
}
