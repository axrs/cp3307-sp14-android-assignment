package au.edu.jcu.mobile_technology.scott.alexander.assignment.commands;

import android.content.Context;
import android.util.Log;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Command;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Contextual;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.AudioEngine;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.LobbyBackgroundAudible;

/**
 * Command to start the lobby music
 */
public class StartLobbyAudioCommand extends Contextual implements Command {

    public StartLobbyAudioCommand(Context context) {
        super(context);
    }

    @Override
    public void execute() {
        Log.d("StartLobbyAudioCommand.execute()", "Executing");
        AudioEngine.getInstance(getContext()).setBackground(new LobbyBackgroundAudible());
    }
}
