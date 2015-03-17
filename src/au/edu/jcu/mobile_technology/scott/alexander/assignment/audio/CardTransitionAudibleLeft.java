package au.edu.jcu.mobile_technology.scott.alexander.assignment.audio;

/**
 * Card transition audible notification for left speaker only
 */
public class CardTransitionAudibleLeft extends CardTransitionAudible {
    /**
     * Gets the right left speaker volume
     *
     * @return Right speaker volume
     */
    @Override
    public float getRightVolume() {
        return 0.0f;
    }
}
