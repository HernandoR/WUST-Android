package com.example.android_lab5;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.widget.Toast.*;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "Student";
    public static final String CREAT_Student =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "number text)";

    String TAG = "lz_database";
    //系统自己增加id的数据，name和number都是text文档
    private Context mContext;

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_Student);
        Log.i(this.TAG, "onCreate: success create table " + TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(this.TAG, "onUpgrade: updating");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);

    }

    public Cursor queryAll(SQLiteDatabase db, String tableName) {
        Log.i(this.TAG, "queryAll: 查询中。。。");
        Cursor cursor = db.query(tableName, new String[]{"id", "name", "number"}, null,
                null, null, null, null);
        return cursor;
    }

    public void alterData(SQLiteDatabase db, int id, String name, String number) {
        db.execSQL("update Student set name = ? ,number = ? where id = ?", new Object[]{name, number, id});
        Log.i(this.TAG, "alterNumber: alter successed");
    }

    public void delele(SQLiteDatabase db, int id) {
        int i = db.delete(TABLE_NAME, "id=" + id, null);
        if (i > 0) {
            Log.i(this.TAG, "数据删除成功！");
        } else {
            Log.i(this.TAG, "数据未删除！");
        }
    }

    public void deleteAll(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(CREAT_Student);
    }
}
