package au.edu.jcu.mobile_technology.scott.alexander.assignment;

import android.content.Context;

/**
 * Basic Context Requiring Object
 */
public class Contextual {

    private Context context;

    public Contextual(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
