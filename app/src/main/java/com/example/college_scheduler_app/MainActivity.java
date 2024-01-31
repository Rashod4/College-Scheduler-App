package com.example.college_scheduler_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.college_scheduler_app.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.class_add_view); //CHANGE THIS BACK TO activity_main

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        items = new ArrayList<>();
        adapter = new MyAdapter(this, items);
        recyclerView.setAdapter(adapter);

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

                if (!className.isEmpty() && !professor.isEmpty() && !section.isEmpty()) {
                    items.add(new Item(className, professor, section, roomNumber));
                    adapter.notifyDataSetChanged(); //this triggers the RecyclerView to update and display the new items

                    // Clear the EditText fields after adding the item
                    classNameEditText.getText().clear();
                    professorEditText.getText().clear();
                    sectionEditText.getText().clear();
                    roomNumberEditText.getText().clear();
                } else {
                    // Show a message indicating that all fields are required
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        Button classesButton = findViewById(R.id.classes_button);
//        classesButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}