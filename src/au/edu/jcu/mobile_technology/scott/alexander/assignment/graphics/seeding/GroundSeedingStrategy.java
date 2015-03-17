package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.seeding;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.MapSeedingStrategy;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.TileMap;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.tiles.GroundTile;

/**
 * Seeding strategy to seed the map ground tiles
 */
public class GroundSeedingStrategy implements MapSeedingStrategy {
    /**
     * Places the ground tiles within the tile map
     * @param map TileMap to seed
     */
    @Override
    public void seed(TileMap map) {

        int groundRow = map.getRows() - 1;
        int groundColumns = map.getColumns();

        for (int i = 0; i < groundColumns; i++) {
            map.setTile(groundRow, i, new GroundTile());
        }
    }
}
