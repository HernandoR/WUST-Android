package com.example.android_lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        //点击按钮创建数据库
        Button createDatabase = (Button) this.findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.dbHelper = new MyDatabaseHelper(MainActivity.this, "lzdb", null, 2);
                SQLiteDatabase db = MainActivity.this.dbHelper.getWritableDatabase();
                MainActivity.this.dbHelper.deleteAll(db);
                Log.i("lz_database", "onClick: 新建完成");
            }
        });

        //点击按钮输入数据
        Button addData = (Button) this.findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = MainActivity.this.dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "zhangshan");
                values.put("number", "201805135001");
                db.insert("Student", null, values);
                values.clear();
                values.put("name", "lisi");
                values.put("number", "201805135002");
                db.insert("Student", null, values);
                values.clear();
                values.put("name", "wangwu");
                values.put("number", "201805135003");
                db.insert("Student", null, values);
                values.clear();
                values.put("name", "liuzhen");
                values.put("number", "201804135090");
                db.insert("Student", null, values);
                values.clear();
                Log.i("lz_database", "onClick: 添加完成");
            }
        });

        //点击按钮输出数据
        Button queryButton = (Button) this.findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = MainActivity.this.dbHelper.getReadableDatabase();
                //利用游标遍历所有数据对象
                Cursor cursor = MainActivity.this.dbHelper.queryAll(db, "Student");
                while (cursor.moveToNext()) {
                    String id = cursor.getString(cursor.getColumnIndex("id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String number = cursor.getString(cursor.getColumnIndex("number"));
                    Log.i("Mainactivity", "result: id=" + id + " name: " + name + "  number:" + number);
                }
                // 关闭游标，释放资源
                cursor.close();

            }
        });

        //点击按钮更新数据
        Button updateData = (Button) this.findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = MainActivity.this.dbHelper.getReadableDatabase();
                MainActivity.this.dbHelper.alterData(db, 2, "hanmeimei", "201804135002");
            }
        });

        //点击按钮删除数据
        Button deleteData = (Button) this.findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = MainActivity.this.dbHelper.getReadableDatabase();
                MainActivity.this.dbHelper.delele(db, 2);
            }
        });
    }
}
