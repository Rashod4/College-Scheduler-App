package com.example.college_scheduler_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityAddClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_add_view);

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText classNameEditText = findViewById(R.id.edit_text_class_name);
                EditText professorEditText = findViewById(R.id.edit_text_professor);
                EditText sectionEditText = findViewById(R.id.edit_text_section);
                EditText roomNumberEditText = findViewById(R.id.edit_text_room_number);

                String className = classNameEditText.getText().toString().trim();
                String professor = professorEditText.getText().toString().trim();
                String section = sectionEditText.getText().toString().trim();
                String roomNumber = roomNumberEditText.getText().toString().trim();

                if (className.isEmpty() || professor.isEmpty() || section.isEmpty() || roomNumber.isEmpty()) {
                    Toast.makeText(ActivityAddClass.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else {
//                    SharedPreferences mySharedPreferences = ActivityAddClass.this.getSharedPreferences("ClassInfo", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = mySharedPreferences.edit();
//                    editor.putString("className", className);
//                    editor.putString("professor", professor);
//                    editor.putString("section", section);
//                    editor.putString("roomNumber", roomNumber);
//                    editor.apply();
                    Intent intent = new Intent();
                    intent.putExtra("className", className);
                    intent.putExtra("professor", professor);
                    intent.putExtra("section", section);
                    intent.putExtra("roomNumber", roomNumber);
                    setResult(RESULT_OK, intent);
                    finish();
                }

                // Clear the EditText fields after adding the item
                classNameEditText.getText().clear();
                professorEditText.getText().clear();
                sectionEditText.getText().clear();
                roomNumberEditText.getText().clear();

//                if (!className.isEmpty() && !professor.isEmpty() && !section.isEmpty()) {
//                    items.add(new Item(className, professor, section, roomNumber));
//                    adapter.notifyDataSetChanged(); // Update the RecyclerView to display the new items
//
//                    // Clear the EditText fields after adding the item
//                    classNameEditText.getText().clear();
//                    professorEditText.getText().clear();
//                    sectionEditText.getText().clear();
//                    roomNumberEditText.getText().clear();
//                } else {
//                    // Show a message indicating that all fields are required
//                    Toast.makeText(ActivityAddClass.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//                }

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
