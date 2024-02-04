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

public class ActivityAddClass extends AppCompatActivity {
    private SharedPreferences sp;
    int taskCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_add_view);

        sp = getSharedPreferences("ClassDetails", Context.MODE_PRIVATE);
        taskCount = sp.getInt("taskCount", 0); // Retrieve the task count

        EditText classNameEditText = findViewById(R.id.edit_text_class_name);
        EditText professorEditText = findViewById(R.id.edit_text_professor);
        EditText sectionEditText = findViewById(R.id.edit_text_section);
        EditText roomNumberEditText = findViewById(R.id.edit_text_room_number);

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String className = classNameEditText.getText().toString().trim();
                String professor = professorEditText.getText().toString().trim();
                String section = sectionEditText.getText().toString().trim();
                String roomNumber = roomNumberEditText.getText().toString().trim();

                if (className.isEmpty() || professor.isEmpty() || section.isEmpty() || roomNumber.isEmpty()) {
                    Toast.makeText(ActivityAddClass.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("className" + taskCount, className);
                    editor.putString("professor" + taskCount, professor);
                    editor.putString("section" + taskCount, section);
                    editor.putString("roomNumber" + taskCount, roomNumber);
                    editor.putInt("taskCount", taskCount + 1); // Increment the task count
                    editor.apply();
                    Toast.makeText(ActivityAddClass.this, "Task Details Added", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(ActivityAddClass.this, MainActivity.class);
//                    intent.putExtra("className", className);
//                    intent.putExtra("professor", professor);
//                    intent.putExtra("section", section);
//                    intent.putExtra("roomNumber", roomNumber);
//                    setResult(RESULT_OK, intent);
//                    finish();
                }

                // Clear the EditText fields after adding the item
                classNameEditText.getText().clear();
                professorEditText.getText().clear();
                sectionEditText.getText().clear();
                roomNumberEditText.getText().clear();

            }
        });
        Button classes_button = findViewById(R.id.classes_button);
        classes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAddClass.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
