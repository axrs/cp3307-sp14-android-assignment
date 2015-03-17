package au.edu.jcu.mobile_technology.scott.alexander.assignment.audio;

/**
 * Card transition audible notification for Right speaker only
 */
public class CardTransitionAudibleRight extends CardTransitionAudible {
    /**
     * Gets the intended left speaker volume
     *
     * @return Left speaker volume
     */
    @Override
    public float getLeftVolume() {
        return 0.0f;
    }
}
