package au.edu.jcu.mobile_technology.scott.alexander.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.AudioEngine;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.ConfirmAudible;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.commands.ChallengeAIGameModeCommand;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.commands.FreePlayGameModeCommand;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.commands.PlayGameModeCommand;

/**
 * Home Activity Controller
 */
public class HomeActivity extends CoreActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
    }

    /**
     * Initiates a Sequence Selection Activity
     */
    private void goToSequenceSelection() {
        AudioEngine.add(new ConfirmAudible());
        Intent intent = new Intent(this, SequenceSelectionActivity.class);
        startActivity(intent);
    }

    /**
     * Initialises the Standard Game Mode selection
     *
     * @param view Sender
     */
    public void onPlayClicked(View view) {
        new PlayGameModeCommand().execute();
        goToSequenceSelection();
    }

    /**
     * Initialises the Free Play Game Mode selection
     *
     * @param view Sender
     */
    public void onFreePlayClicked(View view) {
        new FreePlayGameModeCommand().execute();
        goToSequenceSelection();
    }

    /**
     * Initialises the Challenge AI Game Mode selection
     *
     * @param view Sender
     */
    public void onChallengePlayClicked(View view) {
        new ChallengeAIGameModeCommand().execute();
        goToSequenceSelection();
    }

    /**
     * Initiates a High Score Activity
     */
    public void onHighScoresClicked(View view) {
        AudioEngine.add(new ConfirmAudible());
        Intent intent = new Intent(this, HighScoresActivity.class);
        startActivity(intent);
    }
}
