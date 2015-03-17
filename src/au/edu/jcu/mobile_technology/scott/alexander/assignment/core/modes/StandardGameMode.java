package au.edu.jcu.mobile_technology.scott.alexander.assignment.core.modes;

public class StandardGameMode extends FreePlayGameMode {

    /**
     * Determines if the user can view the previous card in sequence
     *
     * @return True if able
     */
    @Override
    public boolean canViewPrevious() {
        return false;
    }

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
