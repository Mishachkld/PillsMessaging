package com.example.pillsmessaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.pillsmessaging.DialogFragments.AddItemDialogFragment;
import com.example.pillsmessaging.ProjectStructure.PillsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton add_button;
    private PillsViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
          setFragment(new PillListFragment());

        add_button = findViewById(R.id.add_button);
        viewModel = new ViewModelProvider(this).get(PillsViewModel.class);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 new AddItemDialogFragment(view.getContext()).show(getSupportFragmentManager(),AddItemDialogFragment.TAG ); /// Добавить снэкбар после добавления элимента
                //Snackbar.make(view, "One object added", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void setFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.list_fragment, fragment)
                .commit();

    }

}