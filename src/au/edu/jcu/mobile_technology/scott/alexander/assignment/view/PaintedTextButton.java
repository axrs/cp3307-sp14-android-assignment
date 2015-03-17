package au.edu.jcu.mobile_technology.scott.alexander.assignment.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.TypefaceFactory;

/**
 * Subclass of Button with a WetPaint looking font
 */
public class PaintedTextButton extends Button {

    /**
     * Constructs a PaintedTextButton
     *
     * @param context
     */
    public PaintedTextButton(Context context) {
        super(context);
        init();
    }

    /**
     * Constructs a PaintedTextButton
     *
     * @param context
     * @param attrs
     */
    public PaintedTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Constructs a PaintedTextButton
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public PaintedTextButton(Context context, AttributeSet attrs, int defStyle) {
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
