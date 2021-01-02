package com.zjb.notepad;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.UnicodeSetSpanner;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.sql.SQLTransactionRollbackException;

public class DBHelper extends SQLiteOpenHelper {
    public static final String SHEET_INFORMATIONS="create table sheet_informations(" +
            "inf_id integer primary key autoincrement,"+
            "inf_title text not null,"+
            "inf_inf text not null,"+
            "inf_date text not null,"+
            "group_id integer," +
            "foreign key(group_id) references sheet_group(group_id)" +
            ")";
    public static String SHEET_GROUP="create table sheet_group(" +
            "group_id integer primary key autoincrement," +
            "group_name text not null," +
            "group_count integer" +
            ")";
    private Context mContext;
    public DBHelper(@Nullable Context context) {
        super(context,"Notepad.DB",null,1);
        this.mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SHEET_GROUP);
        sqLiteDatabase.execSQL(SHEET_INFORMATIONS);
        Toast.makeText(mContext,"数据库创建成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Toast.makeText(mContext,"数据库更新成功",Toast.LENGTH_SHORT).show();
    }
    //增删改查
    public Cursor Select(SQLiteDatabase sqLiteDatabase, String sql){
        //Toast.makeText(mContext,"查询成功",Toast.LENGTH_SHORT).show();
        return sqLiteDatabase.rawQuery(sql,null);
    }
    public void Delete(SQLiteDatabase sqLiteDatabase,String sql){
        //Toast.makeText(mContext,"删除成功",Toast.LENGTH_SHORT).show();
        sqLiteDatabase.execSQL(sql);
    }
    public void Update(SQLiteDatabase sqLiteDatabase,String sql){
        //Toast.makeText(mContext,"修改成功",Toast.LENGTH_SHORT).show();
        sqLiteDatabase.execSQL(sql);
    }
    public void Insert(SQLiteDatabase sqLiteDatabase,String sql){
        //Toast.makeText(mContext,"增加成功",Toast.LENGTH_SHORT).show();
        sqLiteDatabase.execSQL(sql);
    }
}
