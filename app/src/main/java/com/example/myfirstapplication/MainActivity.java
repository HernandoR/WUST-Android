package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.Start_Main);
        btn.setOnClickListener(new MyClickListener());
        txt = findViewById(R.id.String_Main);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        Log.i("lifeCycle", "onPause: 暂停");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    class MyClickListener implements View.OnClickListener {
        int i = 0;

        @Override
        public void onClick(View v) {
            i++;
            txt.setText(String.format(getResources().getString(R.string.Click_Button), i));
        }
    }
}