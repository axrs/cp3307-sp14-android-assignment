package au.edu.jcu.mobile_technology.scott.alexander.assignment.commands;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.Command;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Game;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.BasicScoreTracker;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.modes.ChallengeAIGameMode;

/**
 * ChallengeAIGameModeCommand initialises the game to challenge the AI (where the player knows the value)
 */
public class ChallengeAIGameModeCommand implements Command {

    @Override
    public void execute() {
        Game.getInstance().setGameMode(new ChallengeAIGameMode());
        Game.getInstance().setScoreTracker(new BasicScoreTracker());
    }
}
