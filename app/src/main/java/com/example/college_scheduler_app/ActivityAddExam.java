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

public class ActivityAddExam extends AppCompatActivity {
    private SharedPreferences sp;
    int taskCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_add_view);

        sp = getSharedPreferences("TaskDetails", Context.MODE_PRIVATE);
        taskCount = sp.getInt("taskCount", 0); // Retrieve the task count

        EditText editTask = findViewById(R.id.edit_task);
        EditText editDate = findViewById(R.id.edit_date);
        EditText editCourse = findViewById(R.id.edit_course);
        EditText editTime = findViewById(R.id.edit_time);
        EditText editLocation = findViewById(R.id.edit_location);

        Button add_task = findViewById(R.id.add_button);
        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = editTask.getText().toString().trim();
                String date = editDate.getText().toString().trim();
                String course = editCourse.getText().toString().trim();
                String time = editTime.getText().toString().trim();
                String location = editLocation.getText().toString().trim();

                if (task.isEmpty() || date.isEmpty() || course.isEmpty() || time.isEmpty() || location.isEmpty()) {
                    Toast.makeText(ActivityAddExam.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (!isValidDateFormat(date)) {
                        Toast.makeText(ActivityAddExam.this, "Please enter the date in the format mm-dd-yyyy", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("task" + taskCount, task);
                    editor.putString("date" + taskCount, date);
                    editor.putString("course" + taskCount, course);
                    editor.putString("time" + taskCount, time);
                    editor.putString("location" + taskCount, location);
                    editor.putInt("taskCount", taskCount + 1); // Increment the task count
                    editor.apply();
                    Toast.makeText(ActivityAddExam.this, "Task Details Added", Toast.LENGTH_SHORT).show();

                    // Clear the EditText fields after adding the item
                    editTask.getText().clear();
                    editDate.getText().clear();
                    editCourse.getText().clear();
                    editTime.getText().clear();
                    editLocation.getText().clear();
                }
            }
            private boolean isValidDateFormat(String date) {
                // Define regex pattern for mm-dd-yyyy format
                String regexPattern = "(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])-(19|20)\\d\\d";
                // Check if the date matches the regex pattern
                return date.matches(regexPattern);
            }
        });

        Button tasks_button = findViewById(R.id.tasks_back);
        tasks_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAddExam.this, ActivityToDo.class);
                startActivity(intent);
            }
        });
    }
}
