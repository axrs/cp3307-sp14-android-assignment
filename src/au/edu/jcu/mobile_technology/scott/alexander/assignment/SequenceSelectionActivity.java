package au.edu.jcu.mobile_technology.scott.alexander.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.AudioEngine;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.ConfirmAudible;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.commands.BinaryGameSequenceCommand;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.commands.FibonacciGameSequenceCommand;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.commands.PrimeGameSequenceCommand;


/**
 * Number Sequence Selection Activity Controller
 */
public class SequenceSelectionActivity extends CoreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sequence_selection_activity);
    }

    /**
     * Initiates a transition to a difficulty selection activity
     */
    private void goToDifficultySelection() {
        AudioEngine.add(new ConfirmAudible());

        Intent intent = new Intent(this, DifficultySelectionActivity.class);
        startActivity(intent);
    }

    /**
     * Initialises the Binary Game Sequence selection
     *
     * @param view Sender
     */
    public void onBinarySelectionClick(View view) {
        new BinaryGameSequenceCommand().execute();
        goToDifficultySelection();
    }

    /**
     * Initialises the Prime Game Sequence selection
     *
     * @param view Sender
     */
    public void onPrimeSelectionClick(View view) {
        new PrimeGameSequenceCommand().execute();
        goToDifficultySelection();
    }

    /**
     * Initialises the Fibonacci Game Sequence selection
     *
     * @param view Sender
     */
    public void onFibonacciSelectionClick(View view) {
        new FibonacciGameSequenceCommand().execute();
        goToDifficultySelection();
    }
}
