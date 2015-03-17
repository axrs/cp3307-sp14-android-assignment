package au.edu.jcu.mobile_technology.scott.alexander.assignment.commands;

import android.content.Context;
import android.util.Log;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Command;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Contextual;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.AudioEngine;

/**
 * Command to resume the Audio Engine
 */
public class ResumeAudioEngineCommand extends Contextual implements Command {

    public ResumeAudioEngineCommand(Context context) {
        super(context);
    }

    @Override
    public void execute() {
        Log.d("ResumeAudioEngineCommand.execute()", "Resuming Audio");
        AudioEngine.getInstance(getContext()).resume();
    }
}
