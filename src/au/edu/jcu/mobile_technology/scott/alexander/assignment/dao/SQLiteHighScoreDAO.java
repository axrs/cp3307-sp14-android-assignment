package au.edu.jcu.mobile_technology.scott.alexander.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import au.edu.jcu.mobile_technology.scott.alexander.assignment.core.HighScore;

import java.util.ArrayList;
import java.util.List;

/**
 * SQLite HighScore Data Access Object
 * <p/>
 * Defines and handles Create, Retrieve, Update and Delete actions for HighScore objects with the database
 */
public class SQLiteHighScoreDAO {

    /**
     * Current class instance
     */
    private static SQLiteHighScoreDAO instance;

    /**
     * Database connection
     */
    private SQLiteDatabase database;

    /**
     * Database helper
     */
    private SQLiteHighScoreHelper helper;

    /**
     * Columns used in the table
     */
    private String[] columns = {
            SQLiteHighScoreHelper.COLUMN_ID,
            SQLiteHighScoreHelper.COLUMN_NAME,
            SQLiteHighScoreHelper.COLUMN_SCORE
    };

    /**
     * Gets the SQLiteHighScoreDAO instance
     *
     * @param context Application context
     * @return Database Access Object
     */
    public static SQLiteHighScoreDAO getInstance(Context context) {
        if (instance == null) {
            instance = new SQLiteHighScoreDAO(context.getApplicationContext());
            instance.open();
        }
        return instance;
    }

    /**
     * Protected constructor
     *
     * @param context Application Context
     */
    protected SQLiteHighScoreDAO(Context context) {
        helper = new SQLiteHighScoreHelper(context);
    }

    /**
     * Opens the database connection
     */
    public void open() {
        if (database == null) {
            database = helper.getWritableDatabase();
        }
    }

    /**
     * Closes the database connection
     */
    public void close() {
        database.close();
    }


    /**
     * Retrieves all HighScores stored in the database
     *
     * @return List of high scores
     */
    public List<HighScore> getAll() {
        List<HighScore> models = new ArrayList<HighScore>();

        //query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
        Cursor c = database.query(SQLiteHighScoreHelper.TABLE_NAME, columns, null, null, null, null, helper.COLUMN_SCORE + " DESC", "100");
        c.moveToFirst();
        while (!c.isAfterLast()) {
            models.add(createFromRecord(c));
            c.moveToNext();
        }
        c.close();
        return models;
    }

    /**
     * Creates a HighScore for the database
     *
     * @param inModel HighScore to store
     * @return Amended HighScore with database reference id
     */
    public HighScore create(HighScore inModel) {

        ContentValues values = new ContentValues();
        values.put(SQLiteHighScoreHelper.COLUMN_NAME, inModel.getName());
        values.put(SQLiteHighScoreHelper.COLUMN_SCORE, inModel.getScore());

        long insertedId = database.insert(SQLiteHighScoreHelper.TABLE_NAME, null, values);

        Cursor cursor = database.query(SQLiteHighScoreHelper.TABLE_NAME, columns, SQLiteHighScoreHelper.COLUMN_ID + " = " + insertedId, null, null, null, null);
        cursor.moveToFirst();
        HighScore outModel = createFromRecord(cursor);
        cursor.close();
        return outModel;
    }

    /**
     * Updates a specified HighScore in the database
     *
     * @param inModel HighScore to update
     */
    public void update(HighScore inModel) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHighScoreHelper.COLUMN_NAME, inModel.getName());
        values.put(SQLiteHighScoreHelper.COLUMN_SCORE, inModel.getScore());
        database.update(SQLiteHighScoreHelper.TABLE_NAME, values, SQLiteHighScoreHelper.COLUMN_ID + " = " + inModel.getId(), null);
    }

    /**
     * Deletes a HighScore from the database
     *
     * @param inModel HighScore to delete
     */
    public void delete(HighScore inModel) {
        database.delete(SQLiteHighScoreHelper.TABLE_NAME, SQLiteHighScoreHelper.COLUMN_ID + "=" + inModel.getId(), null);
    }

    /**
     * Creates a HighScore from a database row
     *
     * @param cursor Current database row
     * @return HighScore
     */
    private HighScore createFromRecord(Cursor cursor) {
        HighScore model = new HighScore();

        model.setId(cursor.getLong(0));
        model.setName(cursor.getString(1));
        model.setScore(cursor.getLong(2));

        return model;
    }

}
