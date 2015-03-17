package au.edu.jcu.mobile_technology.scott.alexander.assignment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.AudioEngine;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.DenyAudible;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.HighScore;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.dao.SQLiteHighScoreDAO;


/**
 * UserWinFragment class controller
 */
public class UserWinFragment extends Fragment {
    EditText userName;

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
        View view = inflater.inflate(R.layout.user_win_fragment,
                container, false);

        TextView titleText = (TextView) view.findViewById(R.id.winText);
        titleText.append(String.format(" %d", Game.getScore()));
        userName = (EditText) view.findViewById(R.id.userName);
        userName.setText(HighScore.getLastUsedName());

        connectButtonListeners(view);
        return view;
    }

    /**
     * Connects any control listeners to associated methods
     *
     * @param view Parent view containing controls
     */
    private void connectButtonListeners(View view) {
        Button submitButton = (Button) view.findViewById(R.id.submitAction);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmitClick();
            }
        });

        Button cancelButton = (Button) view.findViewById(R.id.cancelAction);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCancelClick();
            }
        });
    }

    /**
     * On Submit button clicked
     */
    public void onSubmitClick() {
        if (userName.length() == 0) {
            AudioEngine.add(new DenyAudible());
        } else {
            HighScore.setLastUsedName(userName.getText().toString());
            HighScore hs = new HighScore(userName.getText().toString(), Game.getScore());
            SQLiteHighScoreDAO.getInstance(getActivity()).create(hs);
            getActivity().onBackPressed();
        }
    }

    /**
     * On Cancel button clicked
     */
    public void onCancelClick() {
        getActivity().onBackPressed();
    }
}
