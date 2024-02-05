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

public class ActivityToDo extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<CardModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_view);

        recyclerView = findViewById(R.id.CardRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new CardAdapter(this, list);
        recyclerView.setAdapter(adapter);

        SharedPreferences sp = getApplication().getSharedPreferences("TaskDetails", Context.MODE_PRIVATE);
        int taskCount = sp.getInt("taskCount", 0);

        // Retrieve all tasks from SharedPreferences and add them to the list
        for (int i = 0; i < taskCount; i++) {
            String task = sp.getString("task" + i, "");
            String date = sp.getString("date" + i, "");
            String course = sp.getString("course" + i, "");
            String time = sp.getString("time" + i, "");
            String location = sp.getString("location" + i, "");
            list.add(new CardModel(task, date, course, time, location));
        }
        adapter.notifyDataSetChanged();

        Button classes_button = findViewById(R.id.classes_button2);
        classes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button add_task_button = findViewById(R.id.add_tasks);
        add_task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityToDo.this, ActivityAddTask.class);
                startActivity(intent);
            }
        });

    }
    // Method to update SharedPreferences when a task is edited
    void updateTaskInSharedPreferences(int position, CardModel updatedTask) {
        SharedPreferences sp = getSharedPreferences("TaskDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("task" + position, updatedTask.getTask());
        editor.putString("date" + position, updatedTask.getDate());
        editor.putString("course" + position, updatedTask.getCourse());
        editor.putString("time" + position, updatedTask.getTime());
        editor.putString("location" + position, updatedTask.getLocation());

        editor.apply();
    }

    void deleteTaskFromSharedPreferences(int position) {
        SharedPreferences sp = getSharedPreferences("TaskDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        // Remove task details corresponding to the given position
        editor.remove("task" + position);
        editor.remove("date" + position);
        editor.remove("course" + position);
        editor.remove("time" + position);
        editor.remove("location" + position);

        // Update task count if necessary
        int taskCount = sp.getInt("taskCount", 0);
        if (taskCount > 0) {
            editor.putInt("taskCount", taskCount - 1);
        }

        editor.apply();
    }


}