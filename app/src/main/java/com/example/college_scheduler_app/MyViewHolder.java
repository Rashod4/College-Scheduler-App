package com.example.college_scheduler_app;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView className, professor, section, roomNumber, time, location, repeatingDays;
    LinearLayout llrow;
    Button mondayButton, tuesdayButton, wednesdayButton, thursdayButton, fridayButton;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        className = itemView.findViewById(R.id.className);
        professor = itemView.findViewById(R.id.professor);
        section = itemView.findViewById(R.id.section);
        roomNumber = itemView.findViewById(R.id.roomNumber);
        time = itemView.findViewById(R.id.timee);
        location = itemView.findViewById(R.id.location);
        repeatingDays = itemView.findViewById(R.id.repeatingDays);
        llrow = itemView.findViewById(R.id.llrow);



        // Initialize day buttons
        mondayButton = itemView.findViewById(R.id.monday);
        tuesdayButton = itemView.findViewById(R.id.tuesday);
        wednesdayButton = itemView.findViewById(R.id.wednesday);
        thursdayButton = itemView.findViewById(R.id.thursday);
        fridayButton = itemView.findViewById(R.id.friday);
    }
}

