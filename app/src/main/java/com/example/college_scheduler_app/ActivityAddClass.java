package com.example.college_scheduler_app;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ActivityAddClass extends AppCompatActivity {
    private SharedPreferences sp;
    int taskCount = 0;
    Button timeButton;
    int hour, minute;
    private String selectedTimeString;

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
        EditText locationEditText = findViewById(R.id.edit_location);
        //EditText timeEditText = findViewById(R.id.edit_text_time);
        timeButton = findViewById(R.id.edit_text_time);
        //EditText repeatingDaysEditText = findViewById(R.id.edit_repeating_days);

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String className = classNameEditText.getText().toString().trim();
                String professor = professorEditText.getText().toString().trim();
                String section = sectionEditText.getText().toString().trim();
                String roomNumber = roomNumberEditText.getText().toString().trim();
                String location = locationEditText.getText().toString().trim();
                String time = timeButton.getText().toString().trim();
                //String repeatingDays = repeatingDaysEditText.getText().toString().trim();

                if (className.isEmpty() || professor.isEmpty() || section.isEmpty() || roomNumber.isEmpty() || location.isEmpty() || time.isEmpty()) {
                    Toast.makeText(ActivityAddClass.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("className" + taskCount, className);
                    editor.putString("professor" + taskCount, professor);
                    editor.putString("section" + taskCount, section);
                    editor.putString("roomNumber" + taskCount, roomNumber);
                    editor.putString("location" + taskCount, location);
                    editor.putString("time" + taskCount, selectedTimeString);
                    //editor.putString("repeatingDays" + taskCount, repeatingDays);
                    editor.putInt("taskCount", taskCount + 1); // Increment the task count
                    editor.apply();
                    Toast.makeText(ActivityAddClass.this, "Task Details Added", Toast.LENGTH_SHORT).show();
//
                    // Clear the EditText fields after adding the item
                    classNameEditText.getText().clear();
                    professorEditText.getText().clear();
                    sectionEditText.getText().clear();
                    roomNumberEditText.getText().clear();
                    locationEditText.getText().clear();
                    timeButton.setText("");
                    //timeEditText.getText().clear();
                    //repeatingDaysEditText.getText().clear();
                }


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

        Button mondayButton = findViewById(R.id.monday);
        mondayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = timeButton.getText().toString().trim();

                if (time.isEmpty()) {
                    Toast.makeText(ActivityAddClass.this, "Please select a time first", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if "Monday" is already in the time string
                boolean containsMonday = time.contains("Monday ");

                if (containsMonday) {
                    // If Monday is already in the time string, remove it
                    time = time.replace("Monday ", "");
                } else {
                    // If Monday is not in the time string, add it
                    time = "Monday " + time;
                }

                // Update the selectedTimeString
                selectedTimeString = time;

                // Update the timeButton text
                timeButton.setText(selectedTimeString);
            }
        });

        Button tuesdayButton = findViewById(R.id.tuesday);
        tuesdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = timeButton.getText().toString().trim();

                if (time.isEmpty()) {
                    Toast.makeText(ActivityAddClass.this, "Please select a time first", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if "Tuesday" is already in the time string
                boolean containsTuesday = time.contains("Tuesday ");

                if (containsTuesday) {
                    // If Tuesday is already in the time string, remove it
                    time = time.replace("Tuesday ", "");
                } else {
                    // If Tuesday is not in the time string, add it
                    time = "Tuesday " + time;
                }

                // Update the selectedTimeString
                selectedTimeString = time;

                // Update the timeButton text
                timeButton.setText(selectedTimeString);
            }
        });

// Similarly for Wednesday, Thursday, and Friday buttons

        // Wednesday button
        Button wednesdayButton = findViewById(R.id.wednesday);
        wednesdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = timeButton.getText().toString().trim();

                if (time.isEmpty()) {
                    Toast.makeText(ActivityAddClass.this, "Please select a time first", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if "Wednesday" is already in the time string
                boolean containsWednesday = time.contains("Wednesday ");

                if (containsWednesday) {
                    // If Wednesday is already in the time string, remove it
                    time = time.replace("Wednesday ", "");
                } else {
                    // If Wednesday is not in the time string, add it
                    time = "Wednesday " + time;
                }

                // Update the selectedTimeString
                selectedTimeString = time;

                // Update the timeButton text
                timeButton.setText(selectedTimeString);
            }
        });

// Thursday button
        Button thursdayButton = findViewById(R.id.thursday);
        thursdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = timeButton.getText().toString().trim();

                if (time.isEmpty()) {
                    Toast.makeText(ActivityAddClass.this, "Please select a time first", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if "Thursday" is already in the time string
                boolean containsThursday = time.contains("Thursday ");

                if (containsThursday) {
                    // If Thursday is already in the time string, remove it
                    time = time.replace("Thursday ", "");
                } else {
                    // If Thursday is not in the time string, add it
                    time = "Thursday " + time;
                }

                // Update the selectedTimeString
                selectedTimeString = time;

                // Update the timeButton text
                timeButton.setText(selectedTimeString);
            }
        });

// Friday button
        Button fridayButton = findViewById(R.id.friday);
        fridayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = timeButton.getText().toString().trim();

                if (time.isEmpty()) {
                    Toast.makeText(ActivityAddClass.this, "Please select a time first", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if "Friday" is already in the time string
                boolean containsFriday = time.contains("Friday ");

                if (containsFriday) {
                    // If Friday is already in the time string, remove it
                    time = time.replace("Friday ", "");
                } else {
                    // If Friday is not in the time string, add it
                    time = "Friday " + time;
                }

                // Update the selectedTimeString
                selectedTimeString = time;

                // Update the timeButton text
                timeButton.setText(selectedTimeString);
            }
        });
    }
    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                selectedTimeString = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
                timeButton.setText(selectedTimeString);
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);
        timePickerDialog.show();
    }
}
