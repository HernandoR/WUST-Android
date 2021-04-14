package com.example.android_lab;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashSet;

public class PersonalInfo_Activity extends AppCompatActivity implements View.OnClickListener {
    EditText etName;
    Spinner spClass;
    CheckBox cbHobby1;
    CheckBox cbHobby2;
    CheckBox cbHobby3;
    CheckBox cbHobby4;
    RadioGroup rgGrade;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info_);
        etName = findViewById(R.id.et_name);
        spClass = findViewById(R.id.dropdown_class);
        cbHobby1 = findViewById(R.id.checkBox);
        cbHobby2 = findViewById(R.id.checkBox2);
        cbHobby3 = findViewById(R.id.checkBox3);
        cbHobby4 = findViewById(R.id.checkBox4);
        rgGrade = findViewById(R.id.grade_group);
        btnSubmit = findViewById(R.id.button);
        btnSubmit.setOnClickListener(this);
        etName.setText(this.getIntent().getStringExtra("loginUserName"));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button:
                dispInputByToast();
                break;
            default:
                break;
        }
    }

    private void dispInputByToast() {
        Toast.makeText(this, "开始检查", Toast.LENGTH_SHORT).show();
        Set<CheckBox> CheckBoxes = new HashSet<CheckBox>();
        CheckBoxes.add(cbHobby1);
        CheckBoxes.add(cbHobby2);
        CheckBoxes.add(cbHobby3);
        CheckBoxes.add(cbHobby4);
        String hobbys = "";
        for (CheckBox checkBox : CheckBoxes) {
            if (checkBox.isChecked()) {
                hobbys += checkBox.getText() + " ";
            }
        }
        RadioButton rb = findViewById(rgGrade.getCheckedRadioButtonId());

        String msg = "姓名：" + etName.getText().toString() + "\n" +
                "班级：" + spClass.getSelectedItem().toString() + "\n" +
                "爱好：" + hobbys + "\n" +
                "等级：" + rb.getText().toString() + "\n";
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

