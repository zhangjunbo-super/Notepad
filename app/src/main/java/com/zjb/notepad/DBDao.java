package com.zjb.notepad;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBDao {
    private static DBHelper helper = null;
    private DBDao() {

    }
    public static DBHelper getDBHelper(Context context) {
        if (helper == null) {
            helper = new DBHelper(context);
        }
        return helper;
    }

}
