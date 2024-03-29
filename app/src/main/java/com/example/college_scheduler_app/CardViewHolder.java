package com.example.college_scheduler_app;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardViewHolder extends RecyclerView.ViewHolder {
    TextView task, date, course, time, location;
    LinearLayout llrow;
    CheckBox done;

    public CardViewHolder(@NonNull View itemView) {
        super(itemView);
        task = itemView.findViewById(R.id.task);
        //done = itemView.findViewById(R.id.checkbox);
        date = itemView.findViewById(R.id.date);
        course = itemView.findViewById(R.id.course);
        time = itemView.findViewById(R.id.timeDate);
        location = itemView.findViewById(R.id.location);
        llrow = itemView.findViewById(R.id.llrow);
        //done = itemView.findViewById(R.id.done_button);
    }
}
