package au.edu.jcu.mobile_technology.scott.alexander.assignment;

import android.content.Intent;
import android.os.Bundle;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.AudioEngine;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.ConfirmAudible;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.view.GameView;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.view.OnGameFinishedListener;

/**
 * Game Activity Controller
 */
public class GameActivity extends CoreActivity implements OnGameFinishedListener {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        Game.getInstance().reset();

        GameView view = (GameView) findViewById(R.id.animationSurface);
        view.setGameFinishedListener(this);
    }

    /**
     * Handles an on finished activity event, ending the game and transitioning to a guess screen
     */
    @Override
    public void onFinished() {
        Game.finished();
        GameView view = (GameView) findViewById(R.id.animationSurface);
        view.stop();

        AudioEngine.add(new ConfirmAudible());

        Intent i;
        if (Game.getInstance().getGameMode().userIsGuessing()) {
            i = new Intent(this, UserGuessActivity.class);
        } else {
            i = new Intent(this, AIGuessActivity.class);
        }
        startActivity(i);
        finish();

    }

    /**
     * Handles an on back press
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
