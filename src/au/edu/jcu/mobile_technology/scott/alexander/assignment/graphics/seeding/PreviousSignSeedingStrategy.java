package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.seeding;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.MapSeedingStrategy;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.TileMap;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.tiles.SignTile;

/**
 * Previous Sign Seeding Strategy
 */
public class PreviousSignSeedingStrategy implements MapSeedingStrategy {

    /**
     * Places a previous sign sprite within the tile map
     * @param map TileMap to seed
     */
    @Override
    public void seed(TileMap map) {

        int signRow = map.getRows() - 2;
        int columns = map.getColumns();

        int tilesPerScene = map.getTilesPerScene();

        for (int i = tilesPerScene; i < columns - 1; i += tilesPerScene) {
            map.setTile(signRow, i, new SignTile(SignTile.Icon.LEFT));
        }
    }
}
