package au.edu.jcu.mobile_technology.scott.alexander.assignment.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.TypefaceFactory;

/**
 * Subclass of TextView with a WetPaint looking font
 */
public class PaintedTextView extends TextView {
    /**
     * Constructs a PaintedTextView
     *
     * @param context
     */
    public PaintedTextView(Context context) {
        super(context);
        init();
    }

    /**
     * Constructs a PaintedTextView
     *
     * @param context
     * @param attrs
     */
    public PaintedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Constructs a PaintedTextView
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public PaintedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * Initialises the view to use the PaintType font
     */
    private void init() {
        if (!isInEditMode()) {
            setTypeface(TypefaceFactory.getPaintTypeface(getContext()));
        }
    }
}
