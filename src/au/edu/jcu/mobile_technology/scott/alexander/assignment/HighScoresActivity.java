package au.edu.jcu.mobile_technology.scott.alexander.assignment;

import android.os.Bundle;
import android.widget.ListView;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.view.HighScoreListViewAdapter;

/**
 * High Scores Activity Controller
 */
public class HighScoresActivity extends CoreActivity {

    /**
     * Initialises the activity populating the list view with high scores
     *
     * @param savedInstanceState Any saved data from the last state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_score_activity);
        HighScoreListViewAdapter adapter = new HighScoreListViewAdapter(this);
        final ListView modelView = (ListView) findViewById(R.id.highScoresView);
        modelView.setAdapter(adapter);
    }

}
