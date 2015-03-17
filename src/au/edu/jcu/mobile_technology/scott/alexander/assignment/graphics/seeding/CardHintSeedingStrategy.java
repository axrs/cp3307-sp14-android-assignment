package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.seeding;

import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.MapSeedingStrategy;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.TileMap;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.tiles.SignTile;

import java.util.List;


/**
 * Card Hint Seeding Strategy
 */
public class CardHintSeedingStrategy implements MapSeedingStrategy {
    private List<Integer> scenes;
    private SignTile.Icon icon = SignTile.Icon.TICK;

    public CardHintSeedingStrategy(List<Integer> scenes, Boolean visible) {
        this.scenes = scenes;
        icon = (visible) ? SignTile.Icon.TICK : SignTile.Icon.CROSS;
    }

    /**
     * Places Hints as signs under cards
     *
     * @param map TileMap to seed
     */
    @Override
    public void seed(TileMap map) {
        int signRow = map.getRows() - 2;
        int tilesPerScene = map.getTilesPerScene();

        for (int i : scenes) {
            int column = (tilesPerScene * i) + (tilesPerScene / 2);
            map.setTile(signRow, column, new SignTile(icon));
        }
    }
}
