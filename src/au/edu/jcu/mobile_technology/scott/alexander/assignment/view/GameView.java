package au.edu.jcu.mobile_technology.scott.alexander.assignment.view;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.RelativeLayout;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.Game;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.R;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.AudioEngine;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.CardTransitionAudibleLeft;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.audio.CardTransitionAudibleRight;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.Card;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.AtlasTile;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.CardSprite;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.RegeneratingGameMap;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.graphics.tiles.SignTile;

import java.util.ArrayList;
import java.util.List;


/**
 * A class which renders to a SurfaceView from an independent thread (aiming to keep the Main-UI thread responsive).
 */
public class GameView extends SceneBasedAnimatedView implements SwipeActionHandler, View.OnTouchListener {

    //LogCat Debugging Tag
    private static final String TAG = "GameView";

    //Game view background bitmap
    private static Bitmap background;

    protected OnTouchListener touchListener;
    protected OnSwipeTouchListener swipeListener;

    //Background clipping rectangle
    private Rect backgroundClip = new Rect();

    //FPS Drawing String Paint
    private Paint fpsDrawPaint;

    //Card sprites representing game cards
    private List<CardSprite> cardSprites;

    //Self healing game map/sprite interface
    private RegeneratingGameMap gameMap;

    private OnGameFinishedListener gameFinishedListener;

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public OnGameFinishedListener getGameFinishedListener() {
        return gameFinishedListener;
    }

    public void setGameFinishedListener(OnGameFinishedListener gameFinishedListener) {
        this.gameFinishedListener = gameFinishedListener;
    }

    public void onTileTouched(AtlasTile tile) {
        if (tile instanceof SignTile) {
            SignTile t = (SignTile) tile;

            switch (t.getIcon()) {
                case LEFT:
                    onSwipeRight();
                    break;
                case RIGHT:
                    onSwipeLeft();
                    break;
                case QUESTION:
                    finish();
                    break;
            }

            if (!Game.getInstance().getGameMode().userIsGuessing()) {
                switch (t.getIcon()) {
                    case TICK:
                        Game.setValueIsOnCard(getCurrentSceneIndex());
                        onSwipeLeft();
                        break;
                    case CROSS:
                        Game.setValueIsNotOnCard(getCurrentSceneIndex());
                        onSwipeLeft();
                        break;
                }
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_UP:

                int viewLeft = getWidth() * getCurrentSceneIndex();
                int x = (int) motionEvent.getX() + viewLeft;
                int y = (int) motionEvent.getY();
                AtlasTile tile = gameMap.hitTest(new Point(x, y));
                if (tile != null) {
                    onTileTouched(tile);
                }
                break;
        }

        swipeListener.onTouch(view, motionEvent);

        if (touchListener != null) {
            touchListener.onTouch(view, motionEvent);
        }
        return true;
    }

    /**
     * Initialises the GameView
     */
    private void init() {
        if (background == null) {
            background = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.background);
        }
        gameMap = new RegeneratingGameMap(getContext());

        swipeListener = new OnSwipeTouchListener(getContext(), this);
        super.setOnTouchListener(this);
        setFocusable(true);
    }


    @Override
    public void setOnTouchListener(OnTouchListener l) {
        touchListener = l;
    }

    /**
     * Handles on bottom swipe gesture
     */
    @Override
    public void onSwipeBottom() {
        //Do Nothing
    }

    /**
     * Handles on top swipe gesture
     */
    @Override
    public void onSwipeTop() {
        //Do Nothing
    }

    /**
     * Handles on left swipe gesture
     */
    @Override
    public void onSwipeLeft() {
        if (Game.getInstance().getGameMode().canViewNext()) {
            if (offsetScene(1)) {
                AudioEngine.add(new CardTransitionAudibleLeft());
            } else {
                if (getCurrentSceneIndex() + 1 == getTotalScenes()) {
                    finish();
                }
            }
        }
    }

    private void finish() {
        if (gameFinishedListener != null) {
            gameFinishedListener.onFinished();
        }
    }

    /**
     * Handles on right swipe gesture
     */
    @Override
    public void onSwipeRight() {
        if (Game.getInstance().getGameMode().canViewPrevious()) {
            if (offsetScene(-1)) {
                AudioEngine.add(new CardTransitionAudibleRight());
            }
        }
    }


    /**
     * On SurfaceView created event called when the surface is first created
     *
     * @param surfaceHolder The SurfaceHolder whose surface is being created.
     */
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d(TAG, String.format("Surface being created. Width: %d Height: %d", getWidth(), getHeight()));
    }

    /**
     * On SurfaceView Changed Event
     *
     * @param surfaceHolder The SurfaceHolder whose surface has changed.
     * @param format        New PixelFormat of the surface
     * @param width         New surface width
     * @param height        New surface height
     */
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        super.surfaceChanged(surfaceHolder, format, width, height);

        Log.d(TAG, String.format("Surface being changed. Width: %d Height: %d", width, height));

        setTotalScenes(Game.getInstance().getCards().size());
        boolean mapChanged = gameMap.setMapSize(width, height, getTotalScenes());
        if (mapChanged) {
            setFixedSurfaceDimensions(gameMap.getSceneWidth(), gameMap.getSceneHeight());
        }
        start();
    }

    /**
     * Sets the view to have fixed dimensions matching the size of the game tile sprites
     *
     * @param width  New Width of the View
     * @param height New Height of the View
     */
    private void setFixedSurfaceDimensions(int width, int height) {
        getHolder().setFixedSize(width, height);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        setLayoutParams(params);
        backgroundClip.set(0, 0, width, height);
        background = Bitmap.createScaledBitmap(background, width, height, true);
    }

    /**
     * On SurfaceView Destroyed event
     *
     * @param surfaceHolder The SurfaceHolder hows surface is being destroyed
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "Surface being destroyed.");
        stop();
    }


    /**
     * Gets a list of card sprites
     *
     * @return List of card sprites
     */
    private List<CardSprite> getCardSprites() {
        if (cardSprites == null) {
            cardSprites = new ArrayList<CardSprite>();

            int columns = gameMap.getTilesPerScene() - 1; //Less 1/2 board each side
            int rows = gameMap.getRows() - 3; //less Ground, Sign & 1/2 top and bottom;
            CardSprite.setRenderDetails(gameMap.getAtlasTileSize(), rows, columns);

            for (Card c : Game.getInstance().getCards()) {
                cardSprites.add(new CardSprite(getContext(), c));
            }
        }
        return cardSprites;
    }

    /**
     * Renders the game and GuessingGame assets to the canvas
     *
     * @param canvas Canvas to render
     */
    @Override
    public void render(Canvas canvas) {
        super.render(canvas);

        canvas.drawBitmap(background, backgroundClip, canvas.getClipBounds(), null);
        gameMap.render(canvas);

        int halfTile = gameMap.getAtlasTileSize() / 2;
        int leftOffset;
        for (int i = 0; i < getCardSprites().size(); i++) {
            leftOffset = (getWidth() * i) + halfTile;
            getCardSprites().get(i).render(canvas, leftOffset, halfTile);
        }

        renderFPS(canvas);
    }

    /**
     * Draws the Frames Per Second (DPS) within the SurfaceView
     *
     * @param canvas Canvas to render to
     */
    private void renderFPS(Canvas canvas) {
        canvas.drawText(String.valueOf(getAverageFPS()), canvas.getClipBounds().left + 10, 20, getFpsDrawPaint());
    }

    /**
     * Gets the FPS text paint style
     *
     * @return FPS Paint configuration
     */
    private Paint getFpsDrawPaint() {
        if (fpsDrawPaint == null) {
            fpsDrawPaint = new Paint();
            fpsDrawPaint.setColor(Color.WHITE);
            fpsDrawPaint.setStyle(Paint.Style.FILL);
            fpsDrawPaint.setTextSize(getHeight() * 2 / 100);
        }
        return fpsDrawPaint;
    }
}
