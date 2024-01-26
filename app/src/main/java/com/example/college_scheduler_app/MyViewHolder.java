package com.example.college_scheduler_app;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView className, professor, section, roomNumber;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        className = itemView.findViewById(R.id.className);
        professor = itemView.findViewById(R.id.professor);
        section = itemView.findViewById(R.id.section);
        roomNumber = itemView.findViewById(R.id.roomNumber);
    }
}
