package au.edu.jcu.mobile_technology.scott.alexander.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.AudioEngine;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.BackAudible;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.ConfirmAudible;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.DenyAudible;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.view.RangeInputFilter;

/**
 * UserGuessActivity controller
 */
public class UserGuessActivity extends CoreActivity {
    EditText userGuess;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_guess_activity);

        int min = Game.getInstance().getMinimumValue();
        int max = Game.getInstance().getMaximumValue();

        userGuess = (EditText) findViewById(R.id.userGuess);
        userGuess.setFilters(new InputFilter[]{new RangeInputFilter(min, max)});
    }


    /**
     * On submit guess controller
     *
     * @param view
     */
    public void onSubmitGuessClick(View view) {

        if (userGuess.getText().length() == 0) {
            AudioEngine.add(new DenyAudible());
        } else {
            AudioEngine.add(new ConfirmAudible());

            int guess = Integer.valueOf(userGuess.getText().toString());

            if (Game.isSecret(guess)) {
                goToCorrect();
            } else {
                goToIncorrect();
            }
        }
    }

    /**
     * Returns to the home activity
     */
    @Override
    public void onBackPressed() {
        AudioEngine.add(new BackAudible());

        Intent i = new Intent(this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(i);
        finish();
    }

    /**
     * Goes to the correct guess activity
     */
    public void goToCorrect() {
        Intent i = new Intent(this, UserWinActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * Goes to the incorrect guess activity
     */
    private void goToIncorrect() {
        Intent i = new Intent(this, GameLostActivity.class);
        startActivity(i);
        finish();
    }
}


