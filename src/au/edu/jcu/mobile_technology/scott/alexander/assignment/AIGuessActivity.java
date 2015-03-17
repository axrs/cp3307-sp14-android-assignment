package au.edu.jcu.mobile_technology.scott.alexander.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * AI Guess Activity Controller
 */
public class AIGuessActivity extends CoreActivity {
    /**
     * Initialises the activity
     *
     * @param savedInstanceState Any saved data from the last state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ai_guess_activity);

        TextView view = (TextView) findViewById(R.id.guess);
        view.setText(String.valueOf(Game.getInstance().getGameMode().getAiGuess()));
    }

    /**
     * Control a correct action click fired by the user indicating the AI is correct
     *
     * @param view Sender
     */
    public void onCorrectClick(View view) {
        Intent i = new Intent(this, AIWinActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * Control a incorrect action click fired by the user indicating the AI is incorrect
     *
     * @param view Sender
     */
    public void onIncorrectClick(View view) {
        onBackPressed();
    }
}
