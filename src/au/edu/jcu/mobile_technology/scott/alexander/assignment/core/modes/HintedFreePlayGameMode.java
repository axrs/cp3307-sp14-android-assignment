package au.edu.jcu.mobile_technology.scott.alexander.assignment.core.modes;

public class HintedFreePlayGameMode extends FreePlayGameMode {
    /**
     * Determines if hints are to be displayed to the user
     *
     * @return True if hints are enabled
     */
    @Override
    public boolean hintsEnabled() {
        return true;
    }
}
