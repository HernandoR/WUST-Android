package com.example.broadc;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.content.Intent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    boolean isCast;
    SmsReceiver smsReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smsReceiver = new SmsReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver,filter);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.RECEIVE_SMS"}, 1);
        }




        Button button = findViewById(R.id.button);
        Intent intent = getIntent();
        isCast = intent.getBooleanExtra("iscast", true);
        button.setEnabled(isCast);
        if (isCast) Toast.makeText(this, "正在播放音乐……",Toast.LENGTH_SHORT).show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                switch (id) {
                    case R.id.button:
//                        MediaPlayer mp = MediaPlayer.create(this,R.raw.ferry);
//                        mp.pause();
                        //显式服务调用意图（非绑定式）
                        Intent intent=new Intent(MainActivity.this,MyAudioService.class);
                        //在Activity组件里，停止音乐播放服务
                        stopService(intent);

                }

            }
        });
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode){
//            case 1:
//                if(grantResults[0]!=PackageManager.PERMISSION_GRANTED){
//                    Toast.makeText(this, "未授权，无法实现预定的功能！", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//        }
//    }
//    @Override
//    protected void onDestroy() {  //按手机返回键时触发
//        super.onDestroy();
//        //创建组件对象
//        ComponentName receiver = new ComponentName(this,SmsReceiver.class);
//        //获取包管理器对象
//        PackageManager pm = getPackageManager();
//        //禁用一个静态注册的广播接收者
//        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
//    }

}

