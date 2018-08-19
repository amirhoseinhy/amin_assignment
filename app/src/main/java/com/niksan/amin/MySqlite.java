package com.niksan.amin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlite extends SQLiteOpenHelper {

    public static final String DB_NAME = "nartab.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "menu_selection_status";
    public static final String COL1_ID = "id";
    public static final String COL2_STATUS = "status";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "(" + COL1_ID + " INTEGER PRIMARY KEY  ," +
                COL2_STATUS + " INTEGER NOT NULL" + ")";


    public MySqlite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}
