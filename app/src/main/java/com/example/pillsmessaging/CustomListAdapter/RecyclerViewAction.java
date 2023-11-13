package com.example.pillsmessaging.CustomListAdapter;

public interface RecyclerViewAction { /// мы реализуем этот интерфейс в классе обработчике и передаем этот класс как ре
    void itemClickListener(int position);
    void checkBoxClickListener(int position);

    void descriptionClickListener(int position, String description);

}
