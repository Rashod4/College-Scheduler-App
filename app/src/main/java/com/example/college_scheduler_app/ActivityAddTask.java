package com.example.college_scheduler_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityAddTask extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_add_view);

        Button add_task = findViewById(R.id.add_button);
        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTask = findViewById(R.id.edit_task);
                EditText editDate = findViewById(R.id.edit_date);
                EditText editCourse = findViewById(R.id.edit_course);
                EditText editTime = findViewById(R.id.edit_time);
                EditText editLocation = findViewById(R.id.edit_location);

                String task = editTask.getText().toString().trim();
                String date = editDate.getText().toString().trim();
                String course = editCourse.getText().toString().trim();
                String time = editTime.getText().toString().trim();
                String location = editLocation.getText().toString().trim();

                Intent intent = new Intent(ActivityAddTask.this, ActivityToDo.class);
                intent.putExtra("task", task);
                intent.putExtra("date", date);
                intent.putExtra("course", course);
                intent.putExtra("time", time);
                intent.putExtra("location", location);
                setResult(RESULT_OK, intent);
                finish();

                // Clear the EditText fields after adding the item
                editTask.getText().clear();
                editDate.getText().clear();
                editCourse.getText().clear();
                editTime.getText().clear();
                editLocation.getText().clear();
            }
        });
    }
}
