package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.seeding;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.MapSeedingStrategy;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.TileMap;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.tiles.SignTile;

/**
 * Card Option Seeding Strategy
 */
public class CardOptionSeedingStrategy implements MapSeedingStrategy {

    /**
     * Places Yes No Signs as options under the cards to a map
     *
     * @param map TileMap to seed
     */
    @Override
    public void seed(TileMap map) {
        int signRow = map.getRows() - 2;
        int tilesPerScene = map.getTilesPerScene();

        boolean isEvenSpaced = ((tilesPerScene & 1) == 0);
        int rightSign = isEvenSpaced ? 0 : 1;

        int column = tilesPerScene / 2;
        for (int i = 0; i < map.getColumns(); i += tilesPerScene) {
            map.setTile(signRow, column - 1, new SignTile(SignTile.Icon.CROSS));
            map.setTile(signRow, column + rightSign, new SignTile(SignTile.Icon.TICK));
            column += tilesPerScene;
        }
    }

}
