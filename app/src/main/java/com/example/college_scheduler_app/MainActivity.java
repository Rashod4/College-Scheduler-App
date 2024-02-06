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
            String location = sp.getString("location" + i, "");
            String time = sp.getString("time" + i, "");
            String repeatingDays = sp.getString("repeatingDays" + i, "");
            items.add(new Item(className, professor, section, roomNumber, location, time, repeatingDays));
        }
        adapter.notifyDataSetChanged();

        Button add_classes = findViewById(R.id.add_classes);
        add_classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityAddClass.class);
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
    void updateTaskInSharedPreferences(int position, Item updatedClass) {
        SharedPreferences sp = getSharedPreferences("ClassDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("className" + position, updatedClass.getClassName());
        editor.putString("professor" + position, updatedClass.getProfessor());
        editor.putString("section" + position, updatedClass.getSection());
        editor.putString("roomNumber" + position, updatedClass.getRoomNumber());
        editor.putString("location" + position, updatedClass.getLocation());
        editor.putString("time" + position, updatedClass.getTime());
        editor.putString("repeatingDays" + position, updatedClass.getRepeatingDays());

        editor.apply();
    }

    void deleteTaskFromSharedPreferences(int position) {
        SharedPreferences sp = getSharedPreferences("ClassDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        // Remove task details corresponding to the given position
        editor.remove("className" + position);
        editor.remove("professor" + position);
        editor.remove("section" + position);
        editor.remove("roomNumber" + position);
        editor.remove("location" + position);
        editor.remove("time" + position);
        editor.remove("repeatingDays" + position);

        // Update task count if necessary
        int taskCount = sp.getInt("taskCount", 0);
        if (taskCount > 0) {
            editor.putInt("taskCount", taskCount - 1);
        }

        editor.apply();
    }

}