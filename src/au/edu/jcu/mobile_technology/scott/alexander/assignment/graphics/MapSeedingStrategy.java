package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics;

/**
 * Map seed generating strategy interface
 */
public interface MapSeedingStrategy {
    /**
     * Seeds the specified map
     *
     * @param map TileMap to seed
     */
    public void seed(TileMap map);
}
