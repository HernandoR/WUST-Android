package com.example.android_lab4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 *通过类定义动态注册广播接收者
 */
public class MyDynamicReceiver extends BroadcastReceiver {
    private static final String mTag="DynamicBroadcast";

    public void onReceive(Context context, Intent intent){
        Log.i(mTag,"通过类自定义广播接收者,接收到了消息");
        Log.i(mTag,intent.getAction());//获取广播行为
    }
}

