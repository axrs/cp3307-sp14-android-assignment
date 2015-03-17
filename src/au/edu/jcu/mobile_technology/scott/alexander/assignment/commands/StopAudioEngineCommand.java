package au.edu.jcu.mobile_technology.scott.alexander.assignment.commands;

import android.content.Context;
import android.util.Log;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Command;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Contextual;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.AudioEngine;

/**
 * Command to stop the Audio Engine
 */
public class StopAudioEngineCommand extends Contextual implements Command {

    public StopAudioEngineCommand(Context context) {
        super(context);
    }

    @Override
    public void execute() {
        Log.d("StopAudioEngineCommand.execute()", "Stopping Audio");
        AudioEngine.getInstance(getContext()).stop();
    }
}
