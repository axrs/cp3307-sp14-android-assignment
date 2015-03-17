package au.edu.jcu.mobile_technology.scott.alexander.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.AudioEngine;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.ConfirmAudible;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.HighScore;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.dao.SQLiteHighScoreDAO;

/**
 * AI Win Fragment Controller
 */
public class AIWinFragment extends Fragment {

    /**
     * Initialises the fragment
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's UI should be attached to.
     *                           The fragment should not add the view itself, but this can be used to generate the
     *                           LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as
     *                           given here.
     * @return Inflated Fragment View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ai_win_fragment,
                container, false);

        TextView titleText = (TextView) view.findViewById(R.id.winText);
        titleText.append(String.format(" %d", Game.getScore()));

        String aiName = getActivity().getResources().getString(R.string.ai_name);

        HighScore hs = new HighScore(aiName, Game.getScore());
        SQLiteHighScoreDAO.getInstance(getActivity()).create(hs);

        connectButtonListeners(view);
        return view;
    }

    /**
     * Connects any action listeners
     *
     * @param view Parent View
     */
    private void connectButtonListeners(View view) {
        Button cancelButton = (Button) view.findViewById(R.id.cancelAction);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCancelClick();
            }
        });
    }

    /**
     * Initiates a new Game using the previous configuration
     *
     * @param sender
     */
    public void onReplayPressed(View sender) {
        AudioEngine.add(new ConfirmAudible());
        Intent i = new Intent(getActivity(), GameActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        getActivity().finish();
    }

    /**
     * Handles an on cancel click
     */
    public void onCancelClick() {
        getActivity().onBackPressed();
    }
}
