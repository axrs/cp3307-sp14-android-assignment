package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.seeding;


import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.MapSeedingStrategy;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.TileMap;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.tiles.CardTile;

/**
 * Card Seeding Strategy
 */
public class CardSeedingStrategy implements MapSeedingStrategy {

    /**
     * Calculates and places card display board pieces for each card
     *
     * @param map TileMap to seed
     */
    @Override
    public void seed(TileMap map) {

        int maxBoardHeight = map.getRows() - 2;
        int baseOfBoard = maxBoardHeight - 1;
        int columns = map.getColumns();
        int currentLocation = 0;
        int tilesPerScene = map.getTilesPerScene();

        //Set the top Pieces
        CardTile.SliceLocation type;
        for (int i = 0; i < columns; i++) {
            currentLocation = i % tilesPerScene;
            type = CardTile.SliceLocation.TOP_MIDDLE;

            if (currentLocation == 0) {
                type = CardTile.SliceLocation.TOP_LEFT;
            } else if (currentLocation == tilesPerScene - 1) {
                type = CardTile.SliceLocation.TOP_RIGHT;
            }
            map.setTile(0, i, new CardTile(type));
        }

        //Set the bottom pieces
        for (int i = 0; i < columns; i++) {
            currentLocation = i % tilesPerScene;
            type = CardTile.SliceLocation.BOTTOM_MIDDLE;

            if (currentLocation == 0) {
                type = CardTile.SliceLocation.BOTTOM_LEFT;
            } else if (currentLocation == tilesPerScene - 1) {
                type = CardTile.SliceLocation.BOTTOM_RIGHT;
            }
            map.setTile(baseOfBoard, i, new CardTile(type));
        }

        //Set middle pieces
        for (int j = 1; j < baseOfBoard; j++) {
            for (int i = 0; i < columns; i++) {
                currentLocation = i % tilesPerScene;
                type = CardTile.SliceLocation.MIDDLE;

                if (currentLocation == 0) {
                    type = CardTile.SliceLocation.MIDDLE_LEFT;
                } else if (currentLocation == tilesPerScene - 1) {
                    type = CardTile.SliceLocation.MIDDLE_RIGHT;
                }
                map.setTile(j, i, new CardTile(type));
            }
        }
    }
}
