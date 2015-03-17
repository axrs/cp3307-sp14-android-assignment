package au.edu.jcu.mobile_technology.scott.alexander.assignment.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

/**
 * Extended implementation of SeekBar to introduce a minimum value
 */
public class MinimumSeekBar extends SeekBar {

    //Custom SeekBar listener reference to provide similar interface as possible to default
    protected OnSeekBarChangeListener listener;

    private int min = 0;

    /**
     * Constructs a MinimumSeekBar with minimum value of 0
     *
     * @param context
     */
    public MinimumSeekBar(Context context) {
        super(context);
        initListener();
    }

    /**
     * Constructs a MinimumSeekBar with minimum value of 0
     *
     * @param context
     * @param attrs
     */
    public MinimumSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initListener();
    }

    /**
     * Constructs a MinimumSeekBar with minimum value of 0
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public MinimumSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initListener();
    }

    /**
     * Sets the progress to a specified value, of defaults to minimum, which ever is higher
     *
     * @param progress Progress to set
     */
    @Override
    public synchronized void setProgress(int progress) {
        if (progress < min) {
            progress = min;
        }
        super.setProgress(progress);
    }

    /**
     * Sets the minimum progress range
     *
     * @param min Minimum possible progress value
     */
    public void setMin(int min) {
        this.min = min;
        if (getProgress() < min) {
            setProgress(min);
        }
    }

    @Override
    public void setOnSeekBarChangeListener(OnSeekBarChangeListener l) {
        listener = l;
    }

    /**
     * Initialises the SeekBar listeners, overriding a default, and passing events to a subscriber
     */
    private void initListener() {
        super.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i < min) {
                    setProgress(min);
                } else if (listener != null) {
                    listener.onProgressChanged(seekBar, i, b);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (listener != null) {
                    listener.onStartTrackingTouch(seekBar);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (listener != null) {
                    listener.onStopTrackingTouch(seekBar);
                }
            }
        });
    }

}
