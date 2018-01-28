package com.example.baptiste.exerciseha;

/**
 * Created by Axel on 27/01/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {
    public static class Constants implements BaseColumns {

        public static final String DATABASE_NAME = "myData.db";
        public static final int DATABASE_VERSION = 1;
        public static final String MY_TABLE = "Word";

        public static final String KEY_COL_ID = "Id";
        public static final String KEY_COL_WORD = "Word";
        public static final String KEY_COL_IMAGEID = "ImageID";
        public static final String KEY_COL_IMAGESHADID = "ImageShadID";
        public static final String KEY_COL_BUTTON = "Button";
        public static final String KEY_COL_SOUNDID = "SoundID";
        public static final String KEY_COL_LVL = "Lvl";

        // Index des colonnes
        public static final int ID_COLUMN = 1;
        public static final int WORD_COLUMN = 2;
        public static final int IMAGEID_COLUMN = 3;
        public static final int IMAGESHADID_COLUMN = 4;
        public static final int BUTTON_COLUMN = 5;
        public static final int SOUNDID_COLUMN = 6;
        public static final int LVL_COLUMN = 7;
    }

    // The static string to create the database.
    private static final String DATABASE_CREATE = "create table if not exists "
            + Constants.MY_TABLE + "(" + Constants.KEY_COL_ID
            + " integer primary key autoincrement, "
            + Constants.KEY_COL_WORD + " TEXT, "
            + Constants.KEY_COL_IMAGEID + " INTEGER, "
            + Constants.KEY_COL_IMAGESHADID + " INTEGER, "
            + Constants.KEY_COL_BUTTON + " INTEGER, "
            + Constants.KEY_COL_SOUNDID + " INTEGER, "
            + Constants.KEY_COL_LVL + " INTEGER )";

    /**
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the new database using the SQL string Database_create
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DBOpenHelper", "Mise à jour de la version " + oldVersion
                + " vers la version " + newVersion
                + ", les anciennes données seront détruites ");
        // Drop the old database
        db.execSQL("DROP TABLE IF EXISTS " + Constants.MY_TABLE);
        // Create the new one
        onCreate(db);
        // or do a smartest stuff
    }
}