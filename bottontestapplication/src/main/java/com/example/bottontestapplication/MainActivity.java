package com.example.bottontestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_ok, btn_cancel;
    TextView txt;
    int ClickTimes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_ok = (Button) findViewById(R.id.main_Login);
        btn_cancel = (Button) findViewById(R.id.main_Cancel);
        txt = (TextView) findViewById(R.id.textView);

        btn_cancel.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_Cancel:
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                break;
            case R.id.main_Login:
                ClickTimes++;
                break;


        }
        txt.setText(String.format(getString(R.string.main_msg), ClickTimes));
    }


}