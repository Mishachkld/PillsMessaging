package com.example.pillsmessaging;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pillsmessaging.CustomListAdapter.PillAdapter;
import com.example.pillsmessaging.CustomListAdapter.RecyclerViewAction;
import com.example.pillsmessaging.DataBasePills.ItemPill;
import com.example.pillsmessaging.ProjectStructure.PillsViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class PillListFragment extends Fragment implements RecyclerViewAction {

    private RecyclerView recyclerView;
    private PillsViewModel viewModel;
    private List<ItemPill> data;
    private PillAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pill_list, container, false);
        if (rootView != null) {
            recyclerView = rootView.findViewById(R.id.recycler_view_list_items);
            recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
            viewModel = new ViewModelProvider(this).get(PillsViewModel.class);
            viewModel.getAllPills().getValue();
//          data = new ArrayList<>();  data.add(new ItemPill("Amigos"));
            adapter = new PillAdapter(data, this);
            recyclerView.setAdapter(adapter);
        }
        return rootView;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel.getAllPills().observe(getViewLifecycleOwner(), itemPills -> {
            data = itemPills;
            adapter.updateData(data);
        });


    }

    private void deleteItemOnClick(ItemPill itemPill) {
        Snackbar.make(recyclerView, "Удалить элемент? ", Snackbar.LENGTH_LONG).setAction("Удалить", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.deleteItem(itemPill);
            }
        }).show();


    }


    @Override
    public void itemClickListener(int position) {
        deleteItemOnClick(data.get(position));
    }

    @Override
    public void checkBoxClickListener(int position) {
        data.get(position).setAvailable(!data.get(position).isAvailable());
        viewModel.updateOneItem(data.get(position));
        String text;
        if (data.get(position).isAvailable())
            text = "Notification On";
        else
            text = "Notification Off";
        Snackbar.make(recyclerView, text, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void descriptionClickListener(int position, String description) {
        data.get(position).setDescription(description);
        viewModel.updateOneItem(data.get(position));
        Snackbar.make(recyclerView, "Updated", Snackbar.LENGTH_SHORT).show();
    }
}