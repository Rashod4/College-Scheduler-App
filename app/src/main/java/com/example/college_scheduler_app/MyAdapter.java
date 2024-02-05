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

        holder.llrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.class_add_view);

                EditText editClass = dialog.findViewById(R.id.edit_text_class_name);
                EditText editProfessor = dialog.findViewById(R.id.edit_text_professor);
                EditText editSection = dialog.findViewById(R.id.edit_text_section);
                EditText editRoomNumber = dialog.findViewById(R.id.edit_text_room_number);

                Button add_button = dialog.findViewById(R.id.add_button);
                TextView title = dialog.findViewById(R.id.title);

                title.setText("Edit Class");
                add_button.setText("Update");

                editClass.setText(items.get(holder.getAdapterPosition()).getClassName());
                editProfessor.setText(items.get(holder.getAdapterPosition()).getProfessor());
                editSection.setText(items.get(holder.getAdapterPosition()).getSection());
                editRoomNumber.setText(items.get(holder.getAdapterPosition()).getRoomNumber());

                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String className = editClass.getText().toString();
                        String professor = editProfessor.getText().toString();
                        String section = editSection.getText().toString();
                        String roomNumber = editRoomNumber.getText().toString();

                        Item updatedClass = new Item(className, professor, section, roomNumber);
                        items.set(holder.getAdapterPosition(), updatedClass);
                        notifyItemChanged(holder.getAdapterPosition());

                        // Update SharedPreferences
                        //((MainActivity) context).updateTaskInSharedPreferences(holder.getAdapterPosition(), updatedClass);

                        dialog.dismiss();
                    }
                });
                dialog.show();

                Button delete = dialog.findViewById(R.id.classes_button);
                delete.setText("Delete");
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int adapterPosition = holder.getAdapterPosition();

                        // Remove the item from SharedPreferences
                        ((MainActivity) context).deleteTaskFromSharedPreferences(adapterPosition);

                        // Remove the item from the list
                        items.remove(adapterPosition);

                        // Update positions in SharedPreferences if necessary
                        for (int i = adapterPosition; i < items.size(); i++) {
                            ((MainActivity) context).updateTaskInSharedPreferences(i, items.get(i));
                        }

                        // Notify the adapter about the removal
                        notifyItemRemoved(adapterPosition);

                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

