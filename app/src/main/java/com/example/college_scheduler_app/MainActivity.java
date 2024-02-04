package com.example.college_scheduler_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ADD_CLASS = 1;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Item> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.class_list_view); //CHANGE THIS BACK TO activity_main

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        items = new ArrayList<>();
        adapter = new MyAdapter(this, items);
        recyclerView.setAdapter(adapter);

//        Bundle bundle = getIntent().getExtras();
//        String className = null;
//        String professor = null;
//        String section = null;
//        String roomNumber = null;
//        if (bundle != null) {
//            className = bundle.getString("className");
//            professor = bundle.getString("professor");
//            section = bundle.getString("section");
//            roomNumber = bundle.getString("roomNumber");
//
//
//            items.add(new Item(className, professor, section, roomNumber));
//            adapter.notifyDataSetChanged(); // Update the RecyclerView to display the new items
//
//        }


        Button add_classes = findViewById(R.id.add_classes);
        add_classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityAddClass.class);
                startActivityForResult(intent, REQUEST_ADD_CLASS);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_CLASS && resultCode == RESULT_OK && data != null) {
            String className = data.getStringExtra("className");
            String professor = data.getStringExtra("professor");
            String section = data.getStringExtra("section");
            String roomNumber = data.getStringExtra("roomNumber");

            items.add(new Item(className, professor, section, roomNumber));
            adapter.notifyDataSetChanged();
        }
    }

}