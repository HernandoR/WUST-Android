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

    private static final String staticAction = "StaticBroadcast"; //静态注册广播的action名字
    private static final String HelpAction = "DynamicBroadcast";//动态注册广播的action名字
    private MyStaticReceiver staticReceiver;//广播接收者对象
    private MyDynamicReceiver helpReceiver;//求救的广播接收者对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.initStaticReceiver();//自定义广播接收者1
        this.initHelpReceiver();//求救广播接收者1

    }


    //进行动态注册
    private void initStaticReceiver() {
        this.staticReceiver = new MyStaticReceiver();//实例化对象
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(staticAction);
        this.registerReceiver(this.staticReceiver, intentFilter);
    }

    //动态注册广播接收者
    private void initHelpReceiver() {
        this.helpReceiver = new MyDynamicReceiver();//实例化对象
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(HelpAction);
        this.registerReceiver(this.helpReceiver, intentFilter);
    }

    //结束时销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(this.staticReceiver);
        this.unregisterReceiver(this.helpReceiver);
    }

    //自定义广播按钮
    public void btn_click(View view) {
        Intent intent = new Intent();
        intent.setAction(staticAction);
        this.sendBroadcast(intent);
        if (view.getId() == R.id.Button1) {
            Toast.makeText(MainActivity.this, "静态注册", Toast.LENGTH_SHORT).show();
        }

    }

    //动态注册广播按钮
    public void btn_help_click(View view) {
        Intent intent = new Intent();
        Toast.makeText(MainActivity.this, "动态注册", Toast.LENGTH_SHORT).show();
        intent.setAction(HelpAction);
        this.sendBroadcast(intent);
//        Intent intent1 = new Intent(MainActivity.this, Dynamic.class);
//        this.startActivity(intent1);
    }
}