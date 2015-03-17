package au.edu.jcu.mobile_technology.scott.alexander.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.AudioEngine;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.ConfirmAudible;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.commands.EasyGameDifficultyCommand;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.commands.HardGameDifficultyCommand;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.commands.MediumGameDifficultyCommand;


/**
 * Difficulty Selection Activity Controller
 */
public class DifficultySelectionActivity extends CoreActivity {

    /**
     * Initialises the Activity
     *
     * @param savedInstanceState Any saved data from the last state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulity_selection_activity);
    }

    /**
     * Initialises a transition to the GameActivity
     */
    public void goToGame() {
        AudioEngine.add(new ConfirmAudible());

        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    /**
     * Initialises the Easy Game Difficulty selection
     *
     * @param view Sender
     */
    public void onEasySelectionClick(View view) {
        new EasyGameDifficultyCommand().execute();
        goToGame();
    }

    /**
     * Initialises the Medium Game Difficulty selection
     *
     * @param view Sender
     */
    public void onMediumSelectionClick(View view) {
        new MediumGameDifficultyCommand().execute();
        goToGame();
    }

    /**
     * Initialises the Hard Game Difficulty selection
     *
     * @param view Sender
     */
    public void onHardSelectionClick(View view) {
        new HardGameDifficultyCommand().execute();
        goToGame();
    }
}
