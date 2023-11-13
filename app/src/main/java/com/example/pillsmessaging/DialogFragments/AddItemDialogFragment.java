package com.example.pillsmessaging.DialogFragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.pillsmessaging.DataBasePills.ItemPill;
import com.example.pillsmessaging.ProjectStructure.PillsViewModel;
import com.example.pillsmessaging.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

public class AddItemDialogFragment extends DialogFragment {
    public static final String TAG = "TAG_DIALOG";
    private final Context context;
    private EditText editText;
    private PillsViewModel viewModel;
    private MaterialButton addButton;
    private MaterialTextView text;

    public AddItemDialogFragment( Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        View view = getLayoutInflater().inflate(R.layout.add_dialog, null);
        builder.setView(view);
        builder.setTitle(R.string.dialog_title);

        viewModel = new ViewModelProvider(requireActivity()).get(PillsViewModel.class);
        addButton = view.findViewById(R.id.add_button);
        editText = view.findViewById(R.id.text_input_edit_text);
        text = view.findViewById(R.id.text_item_description);
        text.setText("");
        addItem();


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
