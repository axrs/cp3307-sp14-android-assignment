package au.edu.jcu.mobile_technology.scott.alexander.assignment.view;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

/**
 * Number Range Input Filter restricts inputs to a defined numeric range
 */
public class RangeInputFilter implements InputFilter {

    private static final String TAG = "RangeInputFilter";
    int min = 0;
    int max = 0;

    /**
     * Constructs an input filter, restricting between the specified range
     *
     * @param min Minimum Range Value
     * @param max Maximum Range Value
     */
    public RangeInputFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Filter used to validate modified characters as they are being typed
     *
     * @param source
     * @param charStart
     * @param charEnd
     * @param spanned
     * @param spannedStart
     * @param spannedEnd
     * @return
     */
    @Override
    public CharSequence filter(CharSequence source, int charStart, int charEnd, Spanned spanned, int spannedStart, int spannedEnd) {
        try {
            int input = Integer.parseInt(spanned.toString() + source.toString());
            if (isInRange(input)) {
                return null;
            }
        } catch (NumberFormatException ex) {
            Log.e(TAG, ex.toString());
        }
        return "";
    }

    /**
     * Indicates if a specified value is between the required range
     *
     * @param value Value to check
     * @return True if between range
     */
    private boolean isInRange(int value) {
        return (value <= max && value >= min);
    }
}
