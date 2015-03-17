package au.edu.jcu.mobile_technology.scott.alexander.assignment.commands;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.Command;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Game;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.BasicScoreTracker;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.modes.StandardGameMode;

/**
 * PlayGameModeCommand initialises the game to use a standard score based game mode
 */
public class PlayGameModeCommand implements Command {

    @Override
    public void execute() {
        Game.getInstance().setGameMode(new StandardGameMode());
        Game.getInstance().setScoreTracker(new BasicScoreTracker());
    }
}
