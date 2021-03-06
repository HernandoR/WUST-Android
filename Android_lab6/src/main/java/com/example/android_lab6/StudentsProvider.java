package com.example.android_lab6;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import android.net.Uri;
import android.text.TextUtils;

public class StudentsProvider extends ContentProvider {

    static final String PROVIDER_NAME = "com.example.provider.lzdb";//此处需要填写provider的名称，也就是com.example.provider.XX，这里的XX需要自己填写
    static final String URL = "content://" + PROVIDER_NAME + "/students";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "id";
    static final String NAME = "name";
    static final String NUM = "number";
    static final int STUDENTS = 1;
    static final int STUDENT_ID = 2;
    static final UriMatcher uriMatcher;
    static final String DATABASE_NAME = "lzdb";//这里的名称应该和前面自己定义的一样
    static final String STUDENTS_TABLE_NAME = "Students";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE = "CREATE TABLE IF NOT EXISTS " + STUDENTS_TABLE_NAME + " ("
            + "id integer primary key autoincrement,"
            + "name text,"
            + "number text)";//创建数据库的表，应该包括id,name和number，需要自己补充代码
    private static HashMap<String, String> STUDENTS_PROJECTION_MAP;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "students", STUDENTS);
        uriMatcher.addURI(PROVIDER_NAME, "students/#", STUDENT_ID);
    }

    //数据库特定常量声明
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        Context context = this.getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        //如果不存在，则创建一个可写的数据库
        this.db = dbHelper.getWritableDatabase();
        return (this.db == null) ? false : true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //添加新学生记录
        long rowID = this.db.insert(STUDENTS_TABLE_NAME, "", values);

        //如果记录添加成功
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            this.getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(STUDENTS_TABLE_NAME);

        switch (uriMatcher.match(uri)) {  //switch里面需要补充代码
            case STUDENTS:

                break;

            case STUDENT_ID:
                projection = new String[]{_ID, NAME, NUM};
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (sortOrder == null || sortOrder == "") {
            //默认按照学生姓名排序，需要自己编写代码
            sortOrder = "name";
        }
        Cursor c = qb.query(this.db, projection, selection, selectionArgs, null, null, sortOrder);

        //注册内容URI变化的监听器
        c.setNotificationUri(this.getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                count = this.db.delete(STUDENTS_TABLE_NAME, selection, selectionArgs);
                break;

            case STUDENT_ID:
                String id = uri.getPathSegments().get(1);
                count = this.db.delete(STUDENTS_TABLE_NAME, _ID + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        this.getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {//仿照delete函数中switch的代码，补充update函数代码
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case STUDENTS:

                break;

            case STUDENT_ID:

                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        this.getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            //获取所有学生记录
            case STUDENTS:
                return "vnd.android.cursor.dir/vnd.example.students";

            //获取一个特定的学生
            case STUDENT_ID:
                return "vnd.android.cursor.item/vnd.example.students";

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    //创建和管理提供者内部数据源的帮助类
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + STUDENTS_TABLE_NAME);
            this.onCreate(db);
        }
    }
}