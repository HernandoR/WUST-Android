package com.example.broadc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class SmsReceiver extends BroadcastReceiver {
    // MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {

        // TODO: This method is called when the BroadcastReceiver is receiving
        // 在广播组件里，通过上下文对象启动音乐播放服务组件
        context.startService(new Intent(context, MyAudioService.class));
    }
}