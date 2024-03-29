package com.example.college_scheduler_app;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;
import java.util.Locale;


public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {
    Context context;
    List<CardModel> details;
    LinearLayout llrow;
    int hour, minute;
    private String selectedTimeString;


    public CardAdapter(Context context, List<CardModel> details) {
        this.context = context;
        this.details = details;
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardViewHolder(LayoutInflater.from(context).inflate(R.layout.card_item_view, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.task.setText(details.get(position).getTask());
        holder.date.setText(details.get(position).getDate());
        holder.course.setText(details.get(position).getCourse());
        holder.time.setText(details.get(position).getTime());
        holder.location.setText(details.get(position).getLocation());
        if (details.get(position).getDone() == true) {
            holder.task.setTextColor(Color.GREEN);
        } else {
            holder.task.setTextColor(Color.BLACK);
        }
        if (details.get(position).getVisibility() == true){
            holder.itemView.setVisibility(View.VISIBLE);
        } else {
            holder.itemView.setVisibility(View.GONE);
        }
        




        holder.llrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.edit_task_view);


                EditText editTask = dialog.findViewById(R.id.edit_task);
                EditText editDate = dialog.findViewById(R.id.edit_date);
                EditText editCourse = dialog.findViewById(R.id.edit_course);
                //EditText editTime = dialog.findViewById(R.id.edit_time);
                Button timeButton = dialog.findViewById(R.id.edit_time);
                EditText editLocation = dialog.findViewById(R.id.edit_location);
                Button add_button = dialog.findViewById(R.id.add_button);
                TextView title = dialog.findViewById(R.id.title);
                Button finished_button = dialog.findViewById(R.id.finished_button);


                title.setText("Edit Task");
                add_button.setText("Update");


                editTask.setText(details.get(holder.getAdapterPosition()).getTask());
                editDate.setText(details.get(holder.getAdapterPosition()).getDate());
                editCourse.setText(details.get(holder.getAdapterPosition()).getCourse());
                timeButton.setText(details.get(holder.getAdapterPosition()).getTime());
                editLocation.setText(details.get(holder.getAdapterPosition()).getLocation());

                //the time button listener is used for updating the time in the dialog
                timeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                hour = selectedHour;
                                minute = selectedMinute;
                                selectedTimeString = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
                                timeButton.setText(selectedTimeString);
                            }
                        };
                        TimePickerDialog timePickerDialog = new TimePickerDialog(context, onTimeSetListener, hour, minute, true);
                        timePickerDialog.show();
                    }
                });

                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String task = editTask.getText().toString();
                        String date = editDate.getText().toString();
                        String course = editCourse.getText().toString();
                        String time = timeButton.getText().toString(); //CHANGE THIS LINE
                        String location = editLocation.getText().toString();

                        if (task.isEmpty() || date.isEmpty() || course.isEmpty() || time.isEmpty() || location.isEmpty()) {
                            // Show a toast message indicating that all fields must be filled
                            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                            return; // Exit the method if any field is empty
                        } else {
                            if (!isValidDateFormat(date)) {
                                Toast.makeText(context, "Please enter the date in the format mm-dd-yyyy", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }


                        CardModel updatedTask = new CardModel(task, date, course, time, location);
                        details.set(holder.getAdapterPosition(), updatedTask);
                        notifyItemChanged(holder.getAdapterPosition());

                        // Update SharedPreferences
                        ((ActivityToDo) context).updateTaskInSharedPreferences(holder.getAdapterPosition(), updatedTask);


                        dialog.dismiss();
                    }
                });
                dialog.show();


                Button delete = dialog.findViewById(R.id.tasks_back);
                delete.setText("Delete");
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int adapterPosition = holder.getAdapterPosition();



                        // Remove the item from SharedPreferences
                        ((ActivityToDo) context).deleteTaskFromSharedPreferences(adapterPosition);

                        // Remove the item from the list
                        details.remove(adapterPosition);

                        // Update positions in SharedPreferences if necessary
                        for (int i = adapterPosition; i < details.size(); i++) {
                            ((ActivityToDo) context).updateTaskInSharedPreferences(i, details.get(i));
                        }

                        // Notify the adapter about the removal
                        notifyItemRemoved(adapterPosition);

                        dialog.dismiss();
                    }
                });
                finished_button.setOnClickListener(new View.OnClickListener() { // Set OnClickListener for finished_button
                    @Override
                    public void onClick(View v) {
                        // Perform your desired action when finished_button is clicked
                        Log.d("CardAdapter", "Finished Button Clicked");

                        CardModel cardModel = details.get(holder.getAdapterPosition());

                        // Toggle the value of done field
                        cardModel.setDone(!cardModel.getDone());

                        if (cardModel.getDone()) {
                            holder.task.setTextColor(Color.GREEN);
                        } else {
                            // Set default text color for tasks
                            holder.task.setTextColor(Color.BLACK);
                        }


                        // Dismiss the dialog
                        dialog.dismiss();
                    }
                });


            }
        });
    }

    private boolean isValidDateFormat(String date) {
        // Define regex pattern for mm-dd-yyyy format
        String regexPattern = "(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])-(19|20)\\d\\d";
        // Check if the date matches the regex pattern
        return date.matches(regexPattern);
    }


    @Override
    public int getItemCount() {
        return details.size();
    }
}
