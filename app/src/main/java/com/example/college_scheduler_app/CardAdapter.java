package com.example.college_scheduler_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {
    Context context;
    List<CardModel> details;

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
    }


    @Override
    public int getItemCount() {
        return details.size();
    }
}
