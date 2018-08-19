package com.niksan.amin.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.niksan.amin.MySqlite;

import java.util.ArrayList;
import java.util.List;

public class TableDA {
    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase database;
    private String[] columns = {MySqlite.COL1_ID, MySqlite.COL2_STATUS};

    public TableDA(Context context) {
        sqLiteOpenHelper = new MySqlite(context);
    }

    public void addConfig(Item item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqlite.COL1_ID, item.getItemId());
        contentValues.put(MySqlite.COL2_STATUS, item.getItemStatus());

        database.insert(MySqlite.TABLE_NAME, null, contentValues);
    }

    public List<Item> getAllConfig() {
        List<Item> items = new ArrayList<>();
        database = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = database.query(MySqlite.TABLE_NAME, columns, null, null,
                null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            do {
                Item item = new Item();
                item.setItemId(cursor.getInt(0));
                item.setItemStatus(cursor.getInt(1));

                items.add(item);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }


    public void update(Item item){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqlite.COL2_STATUS, item.getItemStatus());
       database.update(MySqlite.TABLE_NAME, contentValues, MySqlite.COL1_ID + "=?" ,
                         new String[] {String.valueOf(item.getItemId())});
    }




    public boolean isEmpty() {
        boolean empty = true;
        Cursor cur = database.rawQuery("SELECT COUNT(*) FROM menu_selection_status", null);
        if (cur != null && cur.moveToFirst()) {
            empty = (cur.getInt (0) == 0);
        }
        cur.close();

        return empty;
    }


    public void open() {
        database = sqLiteOpenHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

}
