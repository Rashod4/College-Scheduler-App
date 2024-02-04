package com.example.college_scheduler_app;

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


//        list.add(new CardModel("Exam 1", "02/03/24", "CS1332", "11:59", "CULC"));
//        list.add(new CardModel("Homework 1", "02/03/24", "CS2050", "11:59", "home"));
//        adapter.notifyDataSetChanged();

        Button classes_button = findViewById(R.id.classes_button2);
        classes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}