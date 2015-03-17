package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.Log;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.R;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.seeding.BasicSeedingStrategy;


/**
 * Self generating Game Map
 */
public class RegeneratingGameMap extends RenderableAtlasTileMap {

    private static final String TAG = "RegeneratingGameMap";

    private static final int COLUMNS_IN_MAP = 8;

    //Shared bitmap reference
    private static Bitmap atlas;

    //Context reference used to load the bitmap from resources
    private static Context context;

    private int sceneWidth;
    private int sceneHeight;

    private MapSeedingStrategy seedingStrategy;

    /**
     * Creates a regenerating game map
     *
     * @param context Application context for resource access
     */
    public RegeneratingGameMap(Context context) {
        RegeneratingGameMap.context = context;
        //Loads the atlas
        getAtlas();
    }

    /**
     * Gets the tile at a specified point
     *
     * @param hitLocation Location to search
     * @return Map tile at location
     */
    public AtlasTile hitTest(Point hitLocation) {
        AtlasTile tile;

        int column = hitLocation.x / getAtlasTileSize();
        int row = hitLocation.y / getAtlasTileSize();

        tile = getTile(row, column);

        return tile;
    }

    /**
     * Gets the map seeding strategy
     *
     * @return Map Seeding Strategy
     */
    public MapSeedingStrategy getSeedingStrategy() {
        if (seedingStrategy == null) {
            seedingStrategy = new BasicSeedingStrategy();
        }
        return seedingStrategy;
    }

    /**
     * Sets the map seeding strategy
     *
     * @param seedingStrategy Map seeding strategy
     */
    public void setSeedingStrategy(MapSeedingStrategy seedingStrategy) {
        this.seedingStrategy = seedingStrategy;
    }

    /**
     * Sets the intended game map size
     *
     * @param viewWidth  Current view width
     * @param viewHeight Current view height
     * @param totalViews total number of intended views
     * @return true if map has been generated
     */
    public boolean setMapSize(int viewWidth, int viewHeight, int totalViews) {
        Log.d(TAG + ".setMapSize()", String.format("Old: %d x %d | New: %d x %d | Tile Size: %d",
                sceneWidth, sceneHeight, viewWidth, viewHeight, getAtlasTileSize()));

        boolean result = false;
        if ((viewWidth != sceneWidth || viewHeight != sceneHeight) && getAtlasTileSize() > 0) {
            Log.d(TAG, "Regenerating Map");
            int stripWidth = viewWidth % getAtlasTileSize();
            int stripHeight = viewHeight % getAtlasTileSize();

            sceneHeight = viewHeight - stripHeight;
            sceneWidth = viewWidth - stripWidth;

            Log.d(TAG + ".setMapSize()", String.format("Proposed: %d x %d", sceneWidth, sceneHeight));

            int rows = sceneHeight / getAtlasTileSize();
            int columns = (sceneWidth * totalViews) / getAtlasTileSize();
            setSize(rows, columns, totalViews);
            getSeedingStrategy().seed(this);
            result = true;

        }
        return result;
    }


    /**
     * Gets the intended canvas viewport width
     *
     * @return View width adapted to suit the tile size
     */
    public int getSceneWidth() {
        return sceneWidth;
    }

    /**
     * Gets the intended canvas viewport height
     *
     * @return View height adapted to suite the tile size
     */
    public int getSceneHeight() {
        return sceneHeight;
    }

    /**
     * Gets the atlas bitmap
     *
     * @return Atlas bitmap
     */
    @Override
    public Bitmap getAtlas() {
        if (atlas == null) {
            atlas = BitmapFactory.decodeResource(context.getResources(), R.drawable.atlas);
            int tileSize = atlas.getWidth() / COLUMNS_IN_MAP;
            setAtlasTileSize(tileSize);
        }
        return atlas;
    }
}
