package com.example.college_scheduler_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityToDo extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<CardModel> list;
    private List<CardModel> courseList;
    private List<CardModel> dateList;
    private List<CardModel> doneList;


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
                Intent intent = new Intent(ActivityToDo.this, MainActivity.class);
                startActivity(intent);
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

        Button sortButton = findViewById(R.id.sortButton);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });

    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.sort_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.courseSort) {
                    courseSort(list);
                } else if (itemId == R.id.dateSort) {
                    dateSort(list);
                }
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        popupMenu.show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    void courseSort(List<CardModel> list) {
        int n = list.size();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (list.get(i).getCourse().compareTo(list.get(i + 1).getCourse()) > 0) {
                    CardModel temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    void dateSort(List<CardModel> list) {
        int n = list.size();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (compareDate(list.get(i).getDate(), list.get(i + 1).getDate()) > 0) {
                    CardModel temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    int compareDate(String date1, String date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        try {
            Date parsedDate1 = dateFormat.parse(date1);
            Date parsedDate2 = dateFormat.parse(date2);
            return parsedDate1.compareTo(parsedDate2);
        } catch (ParseException e) {
            return 0;
        }
    }
}