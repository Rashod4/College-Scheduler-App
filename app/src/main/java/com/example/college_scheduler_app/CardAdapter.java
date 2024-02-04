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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {
    Context context;
    List<CardModel> details;
    LinearLayout llrow;

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

        holder.llrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.task_add_view);

                EditText editTask = dialog.findViewById(R.id.edit_task);
                EditText editDate = dialog.findViewById(R.id.edit_date);
                EditText editCourse = dialog.findViewById(R.id.edit_course);
                EditText editTime = dialog.findViewById(R.id.edit_time);
                EditText editLocation = dialog.findViewById(R.id.edit_location);
                Button add_button = dialog.findViewById(R.id.add_button);
                TextView title = dialog.findViewById(R.id.title);

                title.setText("Edit Task");
                add_button.setText("Update");

                editTask.setText(details.get(holder.getAdapterPosition()).getTask());
                editDate.setText(details.get(holder.getAdapterPosition()).getDate());
                editCourse.setText(details.get(holder.getAdapterPosition()).getCourse());
                editTime.setText(details.get(holder.getAdapterPosition()).getTime());
                editLocation.setText(details.get(holder.getAdapterPosition()).getLocation());

                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String task = editTask.getText().toString();
                        String date = editDate.getText().toString();
                        String course = editCourse.getText().toString();
                        String time = editTime.getText().toString();
                        String location = editLocation.getText().toString();

                        CardModel updatedTask = new CardModel(task, date, course, time, location);
                        details.set(holder.getAdapterPosition(), updatedTask);
                        notifyItemChanged(holder.getAdapterPosition());

                        // Update SharedPreferences
                        ((ActivityToDo) context).updateTaskInSharedPreferences(holder.getAdapterPosition(), updatedTask);

                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return details.size();
    }
}
