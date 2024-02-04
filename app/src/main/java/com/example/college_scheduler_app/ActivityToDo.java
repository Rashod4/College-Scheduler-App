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

public class ActivityToDo extends AppCompatActivity {
    private static final int REQUEST_ADD_TASK = 1;
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
                startActivityForResult(intent, REQUEST_ADD_TASK);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_TASK && resultCode == RESULT_OK && data != null) {
            String task = data.getStringExtra("task");
            String date = data.getStringExtra("date");
            String course = data.getStringExtra("course");
            String time = data.getStringExtra("time");
            String location = data.getStringExtra("location");

            list.add(new CardModel(task, date, course, time, location));
            adapter.notifyDataSetChanged();
        }
    }

}