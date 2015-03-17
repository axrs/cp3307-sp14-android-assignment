package au.edu.jcu.mobile_technology.scott.alexander.assignment.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHighScoreHelper extends SQLiteOpenHelper {

    public SQLiteHighScoreHelper(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    //Database name
    private static final String DATABASE = "scores.db";

    //Database Table
    public static final String TABLE_NAME = "scores";

    //Database Column Names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE = "score";


    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Creation SQL
    private static final String CREATION_SQL =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " integer primary key autoincrement," +
                    COLUMN_NAME + " text not null," +
                    COLUMN_SCORE + " integer not null);";

    /**
     * Method used to create the database table
     *
     * @param sqLiteDatabase SQLite Database
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATION_SQL);
    }

    /**
     * Method used to upgrade the database
     *
     * @param sqLiteDatabase SQLite Database
     * @param oldVersion     Old Version
     * @param newVersion     New Version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //ToDo for Version 2.
    }
}