package com.example.pillsmessaging.CustomListAdapter;

public interface RecyclerViewAction {
    void itemClickListener(int position);
    void checkBoxClickListener(int position);

    void descriptionClickListener(int position, String description);

}
