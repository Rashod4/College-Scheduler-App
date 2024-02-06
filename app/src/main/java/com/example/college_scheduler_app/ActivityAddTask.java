package com.example.college_scheduler_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityAddTask extends AppCompatActivity {
    private SharedPreferences sp;
    int taskCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_add_view);

        sp = getSharedPreferences("TaskDetails", Context.MODE_PRIVATE);
        taskCount = sp.getInt("taskCount", 0); // Retrieve the task count

        EditText editTask = findViewById(R.id.edit_task);
        EditText editDate = findViewById(R.id.edit_date);
        EditText editCourse = findViewById(R.id.edit_course);


        Button add_task = findViewById(R.id.add_button);
        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = editTask.getText().toString().trim();
                String date = editDate.getText().toString().trim();
                String course = editCourse.getText().toString().trim();

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("task" + taskCount, task);
                editor.putString("date" + taskCount, date);
                editor.putString("course" + taskCount, course);
                editor.putInt("taskCount", taskCount + 1); // Increment the task count
                editor.apply();
                Toast.makeText(ActivityAddTask.this, "Task Details Added", Toast.LENGTH_SHORT).show();

                // Clear the EditText fields after adding the item
                editTask.getText().clear();
                editDate.getText().clear();
                editCourse.getText().clear();
            }
        });

        Button tasks_button = findViewById(R.id.tasks_back);
        tasks_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAddTask.this, ActivityToDo.class);
                startActivity(intent);
            }
        });
    }
}

