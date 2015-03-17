package au.edu.jcu.mobile_technology.scott.alexander.assignment.commands;

import android.content.Context;
import android.util.Log;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Command;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Contextual;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.AudioEngine;

/**
 * Command to stop the Audio Engine
 */
public class PauseAudioEngineCommand extends Contextual implements Command {

    public PauseAudioEngineCommand(Context context) {
        super(context);
    }

    @Override
    public void execute() {
        Log.d("PauseAudioEngineCommand.execute()", "Pausing Audio");
        AudioEngine.getInstance(getContext()).pause();
    }
}
