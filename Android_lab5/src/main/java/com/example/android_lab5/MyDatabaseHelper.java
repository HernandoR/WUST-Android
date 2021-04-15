package com.example.android_lab5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.widget.Toast.*;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREAT_Student="create table Student ("
            +"id integer primary key autoincrement,"
            +"name text,"
            +"number text)";
    //系统自己增加id的数据，name和number都是text文档
   private Context mContext;
   public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
