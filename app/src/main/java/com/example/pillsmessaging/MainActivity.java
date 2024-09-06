package com.example.pillsmessaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import com.example.pillsmessaging.Constants.Constants;
import com.example.pillsmessaging.DialogFragments.AddItemDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton add_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null)
            setFragment(new PillListFragment());

        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener((view) -> {
                new AddItemDialogFragment(view.getContext()).show(getSupportFragmentManager(), AddItemDialogFragment.TAG); /// Добавить снэкбар после добавления элимента
                //Snackbar.make(view, "One object added", Snackbar.LENGTH_LONG).show();
        });
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.list_fragment, fragment)
                .commit();
    }
}
