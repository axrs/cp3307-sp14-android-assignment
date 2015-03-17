package au.edu.jcu.mobile_technology.scott.alexander.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.AudioEngine;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.ConfirmAudible;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.commands.CustomGameDifficultyCommand;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.view.MinimumSeekBar;

/**
 * Custom Difficulty Fragment Controller
 */
public class CustomDifficultyFragment extends Fragment {
    private int minimumValue = 1;
    private int maximumValue = 100;

    private MinimumSeekBar minimumControl;
    private MinimumSeekBar maximumControl;

    private TextView minimumLabel;
    private TextView maximumLabel;

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
        View view = inflater.inflate(R.layout.custom_difficulty_fragment,
                container, false);

        minimumLabel = (TextView) view.findViewById(R.id.minimumValueDisplay);
        maximumLabel = (TextView) view.findViewById(R.id.maximumValueDisplay);
        minimumControl = (MinimumSeekBar) view.findViewById(R.id.minimumValue);
        maximumControl = (MinimumSeekBar) view.findViewById(R.id.maximumValue);


        minimumControl.setMin(1);
        minimumControl.setMax(20);
        minimumControl.setProgress(getMinimumValue());

        maximumControl.setMax(getMaximumValue());
        maximumControl.setProgress(getMaximumValue());

        connectListeners(view);

        setMaximumValue(100);
        setMinimumValue(1);
        return view;
    }

    /**
     * Connects the control listeners for seekbars and buttons
     *
     * @param view Parent View
     */
    private void connectListeners(View view) {
        minimumControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setMinimumValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        maximumControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                setMaximumValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button b = (Button) view.findViewById(R.id.submitAction);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CustomGameDifficultyCommand(getMinimumValue(), getMaximumValue()).execute();
                AudioEngine.add(new ConfirmAudible());

                Intent intent = new Intent(getActivity(), GameActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * Gets the minimum range value
     *
     * @return
     */
    private int getMinimumValue() {
        return minimumValue;
    }

    /**
     * Sets the minimum range value
     *
     * @param minimumValue Minimum range
     */
    private void setMinimumValue(int minimumValue) {
        this.minimumValue = minimumValue;
        maximumControl.setMin(minimumValue + 1);
        minimumLabel.setText(String.valueOf(minimumValue));
    }

    /**
     * Gets the maximum range value
     *
     * @return Maximum Range
     */
    private int getMaximumValue() {
        return maximumValue;
    }

    /**
     * Sets the maximum range value
     *
     * @param maximumValue Maximum range
     */
    private void setMaximumValue(int maximumValue) {
        this.maximumValue = maximumValue;
        maximumLabel.setText(String.valueOf(maximumValue));
    }
}
