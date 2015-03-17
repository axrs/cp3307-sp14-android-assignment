package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics;


import android.content.Context;
import android.graphics.Typeface;

/**
 * Typeface Factory generates and manages typefaces throughout an application
 */
public class TypefaceFactory {

    //paint cache
    private static Typeface paintTypeface = null;

    /**
     * Gets the Paint style typeface
     *
     * @param context Application Context
     * @return Paint typeface
     */
    public static Typeface getPaintTypeface(Context context) {
        if (paintTypeface == null) {
            paintTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/cm_fresh_paint.ttf");
        }
        return paintTypeface;
    }

}
