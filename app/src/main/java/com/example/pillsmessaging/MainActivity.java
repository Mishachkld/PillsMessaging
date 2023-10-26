package com.example.pillsmessaging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null)
            setFragment(new PillListFragment());

        add_button = findViewById(R.id.add_button);
        //viewModel = new ViewModelProvider(this).get(PillsViewModel.class);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddItemDialogFragment(view.getContext()).show(getSupportFragmentManager(), AddItemDialogFragment.TAG); /// Добавить снэкбар после добавления элимента
                //Snackbar.make(view, "One object added", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.list_fragment, fragment)
                .commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_favorite:
                if (viewModel != null){
                    viewModel.setIsNeedOnlyAvailable(!viewModel.getIsNeedOnlyAvailable());
                }
                break;


        }


        return super.onOptionsItemSelected(item);
    }
}
