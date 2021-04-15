package com.example.android_lab4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 通过AS向导自定义的广播，静态注册方式
 */

public class MyStaticReceiver extends BroadcastReceiver {

    private static final String mTag="StaticReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.i(mTag,"通过AS向导自定义广播接收者,接收到了消息");
        Log.i(mTag,intent.getAction());//获取广播行为
    }
}