package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.seeding;


import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.MapSeedingStrategy;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.TileMap;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.tiles.SignTile;

/**
 * End Maps Sign Seeding Strategy
 */
public class EndMapSignSeedingStrategy implements MapSeedingStrategy {

    /**
     * Places a sign at the end of the map, indicted the scene end
     *
     * @param map TileMap to seed
     */
    @Override
    public void seed(TileMap map) {

        int signRow = map.getRows() - 2;
        int columns = map.getColumns();

        map.setTile(signRow, columns - 1, new SignTile(SignTile.Icon.QUESTION));
    }
}
