package com.example.college_scheduler_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //private static final int REQUEST_ADD_CLASS = 1;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Item> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_list_view);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        items = new ArrayList<>();
        adapter = new MyAdapter(this, items);
        recyclerView.setAdapter(adapter);

        SharedPreferences sp = getApplication().getSharedPreferences("ClassDetails", Context.MODE_PRIVATE);
        int taskCount = sp.getInt("taskCount", 0);

        // Retrieve all tasks from SharedPreferences and add them to the list
        for (int i = 0; i < taskCount; i++) {
            String className = sp.getString("className" + i, "");
            String professor = sp.getString("professor" + i, "");
            String section = sp.getString("section" + i, "");
            String roomNumber = sp.getString("roomNumber" + i, "");
            items.add(new Item(className, professor, section, roomNumber));
        }
        adapter.notifyDataSetChanged();

        Button add_classes = findViewById(R.id.add_classes);
        add_classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityAddClass.class);
                //startActivityForResult(intent, REQUEST_ADD_CLASS);
                startActivity(intent);
            }
        });

        Button to_do_button = findViewById(R.id.to_do_button);
        to_do_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityToDo.class);
                startActivity(intent);
            }
        });

    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_ADD_CLASS && resultCode == RESULT_OK && data != null) {
//            String className = data.getStringExtra("className");
//            String professor = data.getStringExtra("professor");
//            String section = data.getStringExtra("section");
//            String roomNumber = data.getStringExtra("roomNumber");
//
//            items.add(new Item(className, professor, section, roomNumber));
//            adapter.notifyDataSetChanged();
//        }
//    }

}