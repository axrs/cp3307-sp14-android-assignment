package au.edu.jcu.mobile_technology.scott.alexander.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.AudioEngine;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.BackAudible;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.ConfirmAudible;

/**
 * Game Lost Activity Controller
 */
public class GameLostActivity extends CoreActivity {

    /**
     * Initialises the Activity
     *
     * @param savedInstanceState Any saved data from the last state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_lost_activity);
    }


    public void onCancelPressed(View view) {
        onBackPressed();
    }

    /**
     * Overrides the back option to return to the Home Activity
     */
    @Override
    public void onBackPressed() {
        AudioEngine.add(new BackAudible());
        Intent i = new Intent(this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    public void onReplayPressed(View sender) {
        AudioEngine.add(new ConfirmAudible());
        Intent i = new Intent(this, GameActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }
}








































































































































































































































































































































