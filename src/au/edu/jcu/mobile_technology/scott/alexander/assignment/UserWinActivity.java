package au.edu.jcu.mobile_technology.scott.alexander.assignment;

import android.content.Intent;
import android.os.Bundle;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.AudioEngine;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.BackAudible;

/**
 * UserWinActivity Controller
 */
public class UserWinActivity extends CoreActivity {

    /**
     * Initialises the UserWinActivity
     *
     * @param savedInstanceState Any saved data from the last state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_win_activity);
    }

    /**
     * On back button handler clearing the activity stack returning to the HomeActivity
     */
    @Override
    public void onBackPressed() {
        AudioEngine.add(new BackAudible());
        Intent i = new Intent(this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }
}
