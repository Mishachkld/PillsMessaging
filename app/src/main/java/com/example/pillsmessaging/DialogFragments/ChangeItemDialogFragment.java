package com.example.pillsmessaging.DialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pillsmessaging.DataBasePills.ItemPill;
import com.example.pillsmessaging.ProjectStructure.PillsViewModel;
import com.example.pillsmessaging.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

public class ChangeItemDialogFragment extends DialogFragment {

    private ItemPill item;
    private PillsViewModel viewModel;
    private EditText editText;
    private MaterialButton addButton;
    private MaterialTextView textDescription;



    public ChangeItemDialogFragment(ItemPill item) {
        this.item = item;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(R.string.dialog_title_update_item);
        View view = getLayoutInflater().inflate(R.layout.add_dialog, null);
        builder.setView(view);

        viewModel = new ViewModelProvider(requireActivity()).get(PillsViewModel.class);
        addButton = view.findViewById(R.id.add_button);
        editText = view.findViewById(R.id.text_input_edit_text);
        textDescription = view.findViewById(R.id.text_item_description);
        addButton.setText(R.string.dialog_btn_text_update_item);
        textDescription.setText(item.getDescription());

        addButton.setOnClickListener(v -> {
            String text = editText.getText().toString();
            if (!text.equals("")) {
                item.setDescription(text);
                viewModel.updateOneItem(item);
                dismiss();
            }
        });


        return builder.create();
    }
}
