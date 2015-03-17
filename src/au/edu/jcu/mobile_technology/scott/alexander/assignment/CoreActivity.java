package au.edu.jcu.mobile_technology.scott.alexander.assignment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.AudioEngine;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.BackAudible;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.commands.*;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Core Activity class for tracking the number of open activities and managing the Audio Engine
 */
public abstract class CoreActivity extends FragmentActivity {

    //Track the number of open activities related to this app
    private static int openActivities;
    //Timer to kill the audio when the app is backgrounded
    private static Timer audioTimeout;

    /**
     * Called when the activity is created
     *
     * @param savedInstanceState Any saved data from the last state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //If there are no open activates, start the lobby audio
        if (openActivities == 0) {
            new StartLobbyAudioCommand(this).execute();
            new LoadAudioEffectsCommand(this).execute();
            audioTimeout = new Timer();
        }

        //Add to the activity counter
        openActivities++;
    }

    /**
     * Called when the activity is to pause
     */
    @Override
    protected void onPause() {

        //Create a new scheduled audio timeout
        if (audioTimeout == null) {
            audioTimeout = new Timer();
            audioTimeout.schedule(new PauseAudioTask(this), 1000);
        }
        super.onPause();
    }

    /**
     * Called when the activity is to resume
     */
    @Override
    protected void onResume() {
        super.onResume();

        //Cancel the audio timeout.
        if (audioTimeout != null) {
            audioTimeout.cancel();
            audioTimeout = null;
        }

        new ResumeAudioEngineCommand(this).execute();
    }

    /**
     * Calls when the activity is to be destroyed
     */
    @Override
    protected void onDestroy() {
        openActivities--;

        if (openActivities == 0) {
            new StopAudioEngineCommand(this).execute();
        }
        super.onDestroy();
    }

    /**
     * Basic pause audio timer class to pause audio when the app is sent to the background
     */
    private class PauseAudioTask extends TimerTask {
        Context context;


        private PauseAudioTask(Context context) {
            this.context = context;
        }

        /**
         * Runs the task
         */
        @Override
        public void run() {
            new PauseAudioEngineCommand(context).execute();
        }
    }

    /**
     * Extends the default implementation to introduce audible feedback
     */
    @Override
    public void onBackPressed() {
        AudioEngine.add(new BackAudible());
        super.onBackPressed();
    }
}
