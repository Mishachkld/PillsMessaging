package com.example.pillsmessaging.CustomListAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pillsmessaging.DataBasePills.ItemPill;
import com.example.pillsmessaging.R;

import java.util.List;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ViewHolder> {
    private List<ItemPill> data;


    public CustomListAdapter(List<ItemPill> data) {
        this.data = data;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<ItemPill> data){
        this.data = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.pills_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.pillTextView.setText(data.get(position).getDescription());
        //holder.time.setText(data.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextClock time;
        private final TextView pillTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            pillTextView = itemView.findViewById(R.id.description_text);
            time = itemView.findViewById(R.id.time_text);
        }
    }


    static class PillDiff extends DiffUtil.ItemCallback<ItemPill> {

        @Override
        public boolean areItemsTheSame(@NonNull ItemPill oldItem, @NonNull ItemPill newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ItemPill oldItem, @NonNull ItemPill newItem) {
            return oldItem.getDescription().equals(newItem.getDescription());
        }
    }
}
