package com.example.android_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
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
        EditText etUser = findViewById(R.id.edit1);
        EditText etPass = findViewById(R.id.edit2);
        String inputUserName = etUser.getText().toString();
        String inputPassword = etPass.getText().toString();
        switch (id) {
            case R.id.btnToastDisp:
                dispInputByToast(inputUserName);
                break;
            case R.id.btnLogDisp:
                Log.i("MainActivity_Log", "onClick: Current input usr name：" + inputUserName);
                break;
            case R.id.btnSaveSp:
                SaveSp(inputUserName, inputPassword);
                break;
            case R.id.btnLogin:
                if (loginCheck(inputUserName, inputPassword)) {
                    Intent intent = new Intent(this, PersonalInfo_Activity.class);
                    intent.putExtra("loginUserName", inputUserName);
                    startActivity(intent);
                }
                break;
            case R.id.btnCancel:
                this.finish();
                break;
            default:
                break;
        }
    }

    boolean userCheck(String inputName) {
//        boolean tag= false;
        for (String name : personInfos) {
            if (inputName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    boolean passwordCheck(String inputName, String inputPassword) {
        String password = this.getSharedPreferences("User_Pass", MODE_PRIVATE).getString(inputName, "1234567");
        return inputPassword.equals(password);
    }

    boolean loginCheck(String inputName, String inputPassword) {
        if (!userCheck(inputName)) {
            Toast.makeText(this, "用户名不存在", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (passwordCheck(inputName, inputPassword)) {
                return true;
            } else {
                Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }

    private void SaveSp(String inputUserName, String inputPassword) {
        SharedPreferences UsrSp = this.getSharedPreferences("User_Pass", MODE_PRIVATE);
        if (UsrSp.edit()
                .putString(inputUserName, inputPassword)
                .commit()) {
            Toast.makeText(this, "存储成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "存储失败", Toast.LENGTH_SHORT).show();
        }
    }


    private void dispInputByToast(String inputUserName) {
        //请自己根据逻辑填写判断是否合法用户逻辑程序
        Toast.makeText(this, "当前输入的用户名为" + inputUserName, Toast.LENGTH_SHORT).show();
    }
}
