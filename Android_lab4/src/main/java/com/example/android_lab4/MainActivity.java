package com.example.android_lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面的逻辑代码
 */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

    }


    //结束时销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //静态广播按钮
    public void static_btn_click(View view) {
        if (view.getId() == R.id.Button1) {
            Toast.makeText(MainActivity.this, "静态注册", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(MainActivity.this, StaticActivity.class);
            this.startActivity(intent1);
        }
    }

    //动态注册广播按钮
    public void dynamic_btn_click(View view) {
        if (view.getId() == R.id.Button2) {
            Toast.makeText(MainActivity.this, "动态注册", Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(MainActivity.this, Dynamic.class);
            this.startActivity(intent2);
        }
    }
}