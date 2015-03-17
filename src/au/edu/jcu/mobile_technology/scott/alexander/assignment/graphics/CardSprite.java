package au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics;

import android.content.Context;
import android.graphics.*;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.Card;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.layout.DefaultCardValueLayoutStrategy;

/**
 * Card Sprite Renderable
 */
public class CardSprite implements OffsetRenderable {

    private static Context context;
    private static int canvasWidth;
    private static int canvasHeight;
    private static int tileSize;
    private static CardValueLayoutStrategy paintStrategy;
    private Paint paint;
    private Card card;
    private Rect paintTileSize;
    private Rect paintDestination = new Rect();
    private RectF paintTextBounds = new RectF();

    private Bitmap numberMap;

    /**
     * Constructs a card sprite object
     *
     * @param context Application Context
     * @param card    Card to render
     */
    public CardSprite(Context context, Card card) {
        CardSprite.context = context;
        this.card = card;
        paintTileSize = getLayoutStrategy().getRect(canvasWidth, canvasHeight, tileSize, card.getValues().size());
    }

    /**
     * Gets the CardValueLayoutStrategy for all Cards.
     *
     * @return Layout strategy
     */
    private static CardValueLayoutStrategy getLayoutStrategy() {
        if (paintStrategy == null) {
            paintStrategy = new DefaultCardValueLayoutStrategy();
        }
        return paintStrategy;
    }

    /**
     * Sets the area restrictions for each individual card
     *
     * @param tileSize Game tile size
     * @param rows     Maximum number of rows to use
     * @param columns  Maximum number of columns to use
     */
    public static void setRenderDetails(int tileSize, int rows, int columns) {
        CardSprite.tileSize = tileSize;
        canvasWidth = (columns) * tileSize;
        canvasHeight = (rows) * tileSize;
    }

    /**
     * Gets the paint for rendering the card values to a bitmap (Scaled to suit the tile sizes)
     *
     * @return Paint for card values
     */
    private Paint getPaint() {
        if (paint == null) {
            paint = new Paint();
            paint.setTypeface(TypefaceFactory.getPaintTypeface(context));
            paint.setColor(Color.WHITE);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(1);
            paint.setTextSize(paintTileSize.height() * 5 / 10);
        }
        return paint;
    }

    /**
     * Gets the card bitmap, creating it if necessary
     *
     * @return Card value bitmap
     */
    private Bitmap getCardBitmap() {
        if (numberMap == null) {
            //Create an empty bitmap to initially paint to.
            Bitmap.Config bitmapConfig = Bitmap.Config.ARGB_8888;
            numberMap = Bitmap.createBitmap(canvasWidth, canvasHeight, bitmapConfig);
            Canvas canvas = new Canvas(numberMap);

            int left = 0;
            int top = 0;

            int paintLeft;
            int paintTop;
            int paintRight;
            int paintBottom;

            //Determine where to paint each value
            for (int cardValue : card.getValues()) {
                paintLeft = left;
                paintTop = top;
                paintRight = paintLeft + paintTileSize.width();
                paintBottom = paintTop + paintTileSize.height();

                paintDestination.set(paintLeft, paintTop, paintRight, paintBottom);

                //Paint each value central (horizontally and vertically) in the defined destination
                paintValueInRect(canvas, paintDestination, String.valueOf(cardValue));

                //Increase the left margin for the next tile
                left += paintTileSize.width();

                //If the tile goes over the canvas width, move to the next row
                if (left + paintTileSize.width() > canvasWidth) {
                    left = 0;
                    top += paintTileSize.height();
                }
            }
        }
        return numberMap;
    }


    /**
     * Paints a specified value centrally in a provided rect
     *
     * @param canvas Canvas to paint onto
     * @param rect   Maximum bounds rect to paint in the centre of
     * @param text   Text value to paint
     */
    private void paintValueInRect(Canvas canvas, Rect rect, String text) {
        paintTextBounds.set(rect);
        // measure text width
        Paint p = getPaint();

        paintTextBounds.right = p.measureText(text, 0, text.length());
        // measure text height
        paintTextBounds.bottom = p.descent() - p.ascent();

        paintTextBounds.left += (rect.width() - paintTextBounds.right) / 2.0f;
        paintTextBounds.top += (rect.height() - paintTextBounds.bottom) / 2.0f;

        canvas.drawText(text, paintTextBounds.left, paintTextBounds.top - p.ascent(), p);
    }


    /**
     * Renders the card onto the canvas
     *
     * @param canvas     Canvas to render onto
     * @param offsetLeft Stage Left Offset
     * @param offsetTop  Stage Top Offset
     */
    @Override
    public void render(Canvas canvas, int offsetLeft, int offsetTop) {
        canvas.drawBitmap(getCardBitmap(), offsetLeft, offsetTop, null);
    }
}
