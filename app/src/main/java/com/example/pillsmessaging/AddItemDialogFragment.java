package com.example.pillsmessaging;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.pillsmessaging.DataBasePills.ItemPill;
import com.example.pillsmessaging.ProjectStructure.PillsViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

public class AddItemDialogFragment extends DialogFragment {
    public static final String TAG = "TAG_DIALOG";
    private final Context context;
    private EditText editText;
    private PillsViewModel viewModel;
    private MaterialButton addButton;

    public AddItemDialogFragment( Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        View view = getLayoutInflater().inflate(R.layout.add_dialog, null);
        builder.setView(view);

        viewModel = new ViewModelProvider(this).get(PillsViewModel.class);
        addButton = view.findViewById(R.id.add_button);
        editText = view.findViewById(R.id.text_input_edit_text);
        addItem();


        builder.setTitle(R.string.dialog_title);
        return builder.create();
    }



    private void addItem() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                if(!text.equals("")){
                    viewModel.insert(new ItemPill(text));
                    dismiss();
                }
            }
        });

    }
}
