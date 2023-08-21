package com.example.pillsmessaging.CustomListAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pillsmessaging.DataBasePills.ItemPill;
import com.example.pillsmessaging.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class PillAdapter extends RecyclerView.Adapter<PillAdapter.ViewHolder> {

    private List<ItemPill> data;
    private RecyclerViewAction recyclerViewInterface;


    public PillAdapter(List<ItemPill> data, RecyclerViewAction recyclerViewAction) {
        this.recyclerViewInterface = recyclerViewAction;
        if (data != null)
            this.data = data;
        else
            this.data = new ArrayList<>();
    }


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.pills_list_item, viewGroup, false);

        return new ViewHolder(view, recyclerViewInterface);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        if (!data.isEmpty()) {
            viewHolder.getDescription().setText(data.get(position).getDescription());
            viewHolder.getTime().setText(String.valueOf(data.get(position).getId()));
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<ItemPill> data) {
        this.data = data;
        notifyDataSetChanged();

    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView time;
        private final TextView pillTextView;


        public ViewHolder(View view, RecyclerViewAction recyclerViewInterface) {
            super(view);
            pillTextView = view.findViewById(R.id.description_text);
            time = view.findViewById(R.id.time_text);
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Snackbar.make(view, "Element " + getAdapterPosition() + " clicked.", Snackbar.LENGTH_LONG).show();
                    return true;
                }
            });


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.itemClickListener(position);
                        }

                    }
                }
            });



            // Define click listener for the ViewHolder's View

        }


        public TextView getDescription() {
            return pillTextView;
        }

        public TextView getTime() {
            return time;
        }

    }


}

