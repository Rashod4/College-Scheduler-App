package com.example.college_scheduler_app;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    List<Item> items;
    LinearLayout llrow;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.className.setText(items.get(position).getClassName());
        holder.professor.setText(items.get(position).getProfessor());
        holder.roomNumber.setText(items.get(position).getRoomNumber());
        holder.section.setText(items.get(position).getSection());
        holder.location.setText(items.get(position).getLocation());
        holder.time.setText(items.get(position).getTime());
        holder.repeatingDays.setText(items.get(position).getRepeatingDays());

        holder.llrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.class_add_view);

                EditText editClass = dialog.findViewById(R.id.edit_text_class_name);
                EditText editProfessor = dialog.findViewById(R.id.edit_text_professor);
                EditText editSection = dialog.findViewById(R.id.edit_text_section);
                EditText editRoomNumber = dialog.findViewById(R.id.edit_text_room_number);
                EditText editLocation = dialog.findViewById(R.id.edit_text_location);
                Button timeButton = dialog.findViewById(R.id.edit_text_time);
                EditText editRepeatingDays = dialog.findViewById(R.id.edit_repeating_days); // Access from dialog view

                Button add_button = dialog.findViewById(R.id.add_button);
                TextView title = dialog.findViewById(R.id.title);

                title.setText("Edit Class");
                add_button.setText("Update");

                editClass.setText(items.get(position).getClassName());
                editProfessor.setText(items.get(position).getProfessor());
                editSection.setText(items.get(position).getSection());
                editRoomNumber.setText(items.get(position).getRoomNumber());
                editLocation.setText(items.get(position).getLocation());
                timeButton.setText(items.get(position).getTime());
                editRepeatingDays.setText(items.get(position).getRepeatingDays());

                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String className = editClass.getText().toString();
                        String professor = editProfessor.getText().toString();
                        String section = editSection.getText().toString();
                        String roomNumber = editRoomNumber.getText().toString();
                        String location = editLocation.getText().toString();
                        String time = timeButton.getText().toString();
                        String repeatingDays = editRepeatingDays.getText().toString();

                        if (className.isEmpty() || professor.isEmpty() || section.isEmpty() || roomNumber.isEmpty() || location.isEmpty() || time.isEmpty() || repeatingDays.isEmpty()) {
                            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Item updatedClass = new Item(className, professor, section, roomNumber, location, time, repeatingDays);
                        items.set(position, updatedClass);
                        notifyItemChanged(position);

                        // Update SharedPreferences
                        ((MainActivity) context).updateTaskInSharedPreferences(position, updatedClass);

                        dialog.dismiss();
                    }
                });
                dialog.show();

                Button delete = dialog.findViewById(R.id.classes_button);
                delete.setText("Delete");
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Remove the item from SharedPreferences
                        ((MainActivity) context).deleteTaskFromSharedPreferences(position);

                        // Remove the item from the list
                        items.remove(position);

                        // Update positions in SharedPreferences if necessary
                        for (int i = position; i < items.size(); i++) {
                            ((MainActivity) context).updateTaskInSharedPreferences(i, items.get(i));
                        }

                        // Notify the adapter about the removal
                        notifyItemRemoved(position);

                        dialog.dismiss();
                    }
                });

                // Add click listeners to day buttons
                dialog.findViewById(R.id.monday).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleDayInRepeatingDays(editRepeatingDays, "Monday");
                    }
                });

                dialog.findViewById(R.id.tuesday).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleDayInRepeatingDays(editRepeatingDays, "Tuesday");
                    }
                });

                dialog.findViewById(R.id.wednesday).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleDayInRepeatingDays(editRepeatingDays, "Wednesday");
                    }
                });

                dialog.findViewById(R.id.thursday).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleDayInRepeatingDays(editRepeatingDays, "Thursday");
                    }
                });

                dialog.findViewById(R.id.friday).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleDayInRepeatingDays(editRepeatingDays, "Friday");
                    }
                });
            }
        });
    }

    private void toggleDayInRepeatingDays(EditText editRepeatingDays, String day) {
        String repeatingDays = editRepeatingDays.getText().toString().trim();
        if (repeatingDays.isEmpty()) {
            repeatingDays = day;
        } else {
            if (repeatingDays.contains(day)) {
                repeatingDays = repeatingDays.replace(day, "").trim();
            } else {
                repeatingDays += " " + day;
            }
        }
        editRepeatingDays.setText(repeatingDays);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
