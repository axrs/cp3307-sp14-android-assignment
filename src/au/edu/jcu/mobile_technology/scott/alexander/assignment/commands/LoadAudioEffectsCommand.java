package au.edu.jcu.mobile_technology.scott.alexander.assignment.commands;

import android.content.Context;
import android.util.Log;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Command;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Contextual;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.*;

/**
 * Command to initialise and load the audio effects within the sound pool
 */
public class LoadAudioEffectsCommand extends Contextual implements Command {

    public LoadAudioEffectsCommand(Context context) {
        super(context);
    }

    @Override
    public void execute() {
        Log.d("LoadAudioEffectsCommand.execute()", "Executing");
        AudioEngine.add(new BackAudible());
        AudioEngine.add(new CardTransitionAudible());
        AudioEngine.add(new CardTransitionAudibleLeft());
        AudioEngine.add(new CardTransitionAudibleRight());
        AudioEngine.add(new ConfirmAudible());
        AudioEngine.add(new DenyAudible());
    }
}
