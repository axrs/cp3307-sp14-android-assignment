package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.seeding;


import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.MapSeedingStrategy;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.TileMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Map Seeding Strategy
 * <p/>
 * Seeds a specified map using multiple strategies
 */
public class CompositeMapSeedingStrategy implements MapSeedingStrategy {

    private List<MapSeedingStrategy> strategies;

    /**
     * Gets the seeding strategies
     *
     * @return MapSeedingStrategies
     */
    public List<MapSeedingStrategy> getStrategies() {
        if (strategies == null) {
            strategies = new ArrayList<MapSeedingStrategy>();
        }
        return strategies;
    }

    /**
     * Adds a MapSeedingStrategy to the list
     *
     * @param strategy Strategy to add
     */
    public void addStrategy(MapSeedingStrategy strategy) {
        getStrategies().add(strategy);
    }

    /**
     * Removes a MapSeedingStrategy from the list
     *
     * @param strategy Strategy to remove
     */
    private void removeStrategy(MapSeedingStrategy strategy) {
        if (getStrategies().contains(strategy)) {
            getStrategies().remove(strategy);
        }
    }

    /**
     * Seeds the map using the strategies
     *
     * @param map TileMap to seed
     * @return
     */
    @Override
    public void seed(TileMap map) {
        for (MapSeedingStrategy strategy : getStrategies()) {
            strategy.seed(map);
        }
    }
}
