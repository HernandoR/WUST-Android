package com.example.android_lab6;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        int uri = this.getContentResolver().delete(StudentsProvider.CONTENT_URI, null, null);
    }

    public void onClickAddName(View view) {
        // 增加新的学生记录
        ContentValues values = new ContentValues();

        EditText etName = this.findViewById(R.id.editText2);
        EditText etNum = this.findViewById(R.id.editText3);

        //此处需要利用values.put将数据填入数据库中，需要大家把代码补充完整
        values.put("name", etName.getText().toString());
        values.put("number", etNum.getText().toString());

        Uri uri = this.getContentResolver().insert(
                StudentsProvider.CONTENT_URI, values);

        Toast.makeText(this.getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view) {

        // 检索学生记录
        String URL = "content://com.example.provider.lzdb/students/1";//请将URL补充完整

        Uri students = Uri.parse(URL);
        Cursor c = this.managedQuery(students, null, null, null, "name");

        if (c.moveToFirst()) {
            do {
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                                ", " + c.getString(c.getColumnIndex(StudentsProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex(StudentsProvider.NUM)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
}