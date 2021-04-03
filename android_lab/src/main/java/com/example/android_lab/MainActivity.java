package com.example.android_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    String[] personInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化所有人员信息
        personInfos = new String[]{"susan", "Liming", "zhangsan", "Lihao"};
        //这里要填写代码，实现对各个布局文件中各种工件的关联，并添加按钮的Click事件监听器
        btn = findViewById(R.id.btnToastDisp);
        btn.setOnClickListener(this);
        btn = findViewById(R.id.btnLogDisp);
        btn.setOnClickListener(this);
        btn = findViewById(R.id.btnSaveSp);
        btn.setOnClickListener(this);
        btn = findViewById(R.id.btnLogin);
        btn.setOnClickListener(this);
        btn = findViewById(R.id.btnCancel);
        btn.setOnClickListener(this);
    }

    //判断用户是否是合法用户，是返回true,否则返回false
    boolean checkInfo(String name) {
        boolean flag = false;

        return Arrays.binarySearch(personInfos, name) >= 0;

    }

    //各个按钮的响应程序写在这里
    @SuppressLint("NonConstantResourceId")//避免报错
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnToastDisp:
                dispInputByToast();
                break;
            case R.id.btnLogDisp:
                break;
            case R.id.btnSaveSp:
                break;
            case R.id.btnLogin:
                Intent intent = new Intent(this, PersonalInfo_Activity.class);
                startActivity(intent);
                break;
            case R.id.btnCancel:
                break;
            default:
                break;
        }
    }

    private void dispInputByToast() {
        EditText etUser = (EditText) findViewById(R.id.edit1);
        EditText etPass = (EditText) findViewById(R.id.edit2);
        //请自己根据逻辑填写判断是否合法用户逻辑程序
        String inputUserName = etUser.getText().toString();
        String inputPassword = etPass.getText().toString();
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}
