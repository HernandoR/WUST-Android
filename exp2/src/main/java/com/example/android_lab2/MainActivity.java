package com.example.android_lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //定义对应布局文件中的控件，定义域变量
    EditText et_name; //姓名
    EditText et_sno;  //学号
    EditText et_telno; //电话
    Spinner sp_music; //喜欢的音乐下拉框
    Button btn_tel1, btn_msg, btn_musicPlay, btn_musicStop, btn_saveFile; //所有按钮
    MediaPlayer mp;

    int Music_R_Id[] = {R.raw.music2, R.raw.music1, R.raw.music3};  //实现和喜欢的音乐下拉框选项对应的音乐资源文件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_telno = findViewById(R.id.et_telno);
        et_name = findViewById(R.id.et_name);
        et_sno = findViewById(R.id.et_no);
        sp_music = findViewById(R.id.sp_music);

        btn_tel1 = findViewById(R.id.btn_tel1);
        btn_tel1.setOnClickListener(this); //设置按钮的Click监听器
        btn_musicStop = findViewById(R.id.btn_MusicStop);
        btn_musicStop.setOnClickListener(this);
        btn_musicPlay = findViewById(R.id.btn_MusicPlay);
        btn_musicPlay.setOnClickListener(this);
        //请按照上面的代码书写格式，设置其他按钮的Click监听器

    }

    //拨打电话1功能   不需要申请权限
    // 参数tel为需要拨打的电话
    void fun_tel1(String tel) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);  //拨号
        intent.setData(Uri.parse("tel:" + tel));  //意图数据=>打电话
                /*intent.setAction(Intent.ACTION_VIEW);//用于显示用户的数据，会根据用户的数据类型打开相应的Activity
                intent.setData(Uri.parse("tel:15527643858"));*/
        startActivity(intent);

    }

    //发送短信功能  不需要申请权限  参数msgInfo为发送的短信信息
    void fun_Msg(String msgInfo) {

    }

    //播放音乐功能
    void fun_musicPlay(int music_res_id) {
        mp = MediaPlayer.create(this, music_res_id);
        mp.start();
        btn_musicPlay.setEnabled(false);
        btn_musicStop.setEnabled(true);

    }

    //停止播放音乐功能
    void fun_musicStop() {

    }

    //信息保存功能  存储到外部文件，需要申请权限
    void fun_SaveFile() {
        String info;
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("dataInfo", MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            info = "name:" + et_name.getText().toString() + "\n";
            writer.write(info);
            info = "sno:" + et_sno.getText().toString() + "\n";
            writer.write(info);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_tel1:
                //如果是打电话1按钮，传递合适的参数给fun_tel1()函数，实现功能
                fun_tel1(et_telno.getText().toString());
                break;
            case R.id.btn_MusicPlay:
                int i = (int) sp_music.getSelectedItemId();
                int r = Music_R_Id[i];
                fun_musicPlay(r);
                break;
            case R.id.btn_MusicStop:

                //填写代码，调用合适的函数实现停止播放功能
                break;
            case R.id.btn_Msg:
                //填写代码，调用合适的函数实现发短信功能
                break;
            case R.id.btn_saveFile:
                //填写代码，调用合适的函数实现保存信息到外部文件功能

                break;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {  //所需关键权限
                    fun_SaveFile();
                } else {
                    Toast.makeText(this, "没有写外部文件的权限", Toast.LENGTH_LONG).show();
                    finish();
                }
        }
    }
}
