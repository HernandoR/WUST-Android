package com.example.android_lab4;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StaticActivity extends AppCompatActivity {
    ListView lv_list;


    private String[] data = {"Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.staticview);

        this.lv_list = this.findViewById(R.id.lv_list);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_expandable_list_item_1, this.data);
        ListView listView = (ListView) this.findViewById(R.id.lv_list);
        listView.setAdapter(adapter);

        this.lv_list.setAdapter(adapter);

        this.lv_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String result = parent.getItemAtPosition(position).toString();
                        Toast.makeText(StaticActivity.this,
                                "点击了" + result, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
