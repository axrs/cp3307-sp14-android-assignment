package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Renderable tile map drawn from a atlas source
 */
public abstract class RenderableAtlasTileMap extends TileMap implements Renderable {

    private static int atlasTileSize = 0;
    //Atlas cut rect.  Instantiated only once
    private Rect atlasRegion;
    //Canvas render rect.  Instantiated only once
    private Rect canvasRegion;

    protected RenderableAtlasTileMap() {
        atlasRegion = new Rect();
        canvasRegion = new Rect();
    }

    /**
     * Gets the atlas tile size
     *
     * @return tile size (0 if not setSequence)
     */
    public int getAtlasTileSize() {
        return atlasTileSize;
    }

    /**
     * Sets the atlas tile size
     *
     * @param size tile size
     */
    public void setAtlasTileSize(int size) {
        atlasTileSize = size;
    }

    public abstract Bitmap getAtlas();

    /**
     * Gets the atlas cutting region
     *
     * @param tile AtlasTile to cut
     * @return Atlas cut rect
     */
    private Rect getAtlasCutRegion(AtlasTile tile) {
        int left = tile.getAtlasColumn() * atlasTileSize;
        int top = tile.getAtlasRow() * atlasTileSize;
        int right = left + atlasTileSize;
        int bottom = top + atlasTileSize;

        atlasRegion.set(left, top, right, bottom);

        return atlasRegion;
    }

    /**
     * Gets the canvas render region
     *
     * @param row    Map row
     * @param column Map column
     * @return Calculated canvas render region
     */
    private Rect getCanvasRenderRegion(int row, int column) {
        int left = column * atlasTileSize;
        int top = row * atlasTileSize;
        int right = left + atlasTileSize;
        int bottom = top + atlasTileSize;

        canvasRegion.set(left, top, right, bottom);
        return canvasRegion;
    }

    /**
     * Renders the Atlas Tile Map to the canvas
     *
     * @param canvas Canvas to render onto
     */
    @Override
    public void render(Canvas canvas) {
        int mapRow;
        int mapColumn;

        AtlasTile renderTile;
        for (int i = 0; i < getTileCount(); i++) {
            mapRow = (int) i / getColumns();
            mapColumn = i % getColumns();

            renderTile = getMap()[mapRow][mapColumn];
            if (renderTile != null) {
                canvas.drawBitmap(
                        getAtlas(),
                        getAtlasCutRegion(renderTile),
                        getCanvasRenderRegion(mapRow, mapColumn),
                        null);
            }
        }
    }
}
