package com.example.android_lab4;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Dynamic extends AppCompatActivity implements View.OnClickListener {
    private static final String DynamicAction = "DynamicBroadcast";//动态注册广播的action名字
    Button btn_unreg, btn_send;
    EditText et_message;
    private MyDynamicReceiver myDynamicReceiver;//求救的广播接收者对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dynamic);
        this.btn_send = this.findViewById(R.id.button_send);
        this.btn_send.setOnClickListener(this);
        this.btn_unreg = this.findViewById(R.id.button_unreg);
        this.btn_unreg.setOnClickListener(this);
        this.et_message = this.findViewById(R.id.edit1);

        this.initDynamicReceiver();//初始化动态广播接收者
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_send:
                String result = this.et_message.getText().toString();
                Intent intent = new Intent();
                intent.setAction(DynamicAction);
                intent.putExtra("msg", result);
                this.sendBroadcast(intent);
                break;
            case R.id.button_unreg:
                Toast.makeText(this, "unregister success", Toast.LENGTH_SHORT).show();
                this.unregisterReceiver(this.myDynamicReceiver);
                break;
        }
    }

    //结束时销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(this.myDynamicReceiver);
    }

    //动态注册广播接收者
    private void initDynamicReceiver() {
        this.myDynamicReceiver = new MyDynamicReceiver();//实例化对象
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DynamicAction);
        this.registerReceiver(this.myDynamicReceiver, intentFilter);
    }


}
