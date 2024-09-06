package com.example.pillsmessaging.DialogFragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pillsmessaging.DataBasePills.ItemPill;
import com.example.pillsmessaging.ProjectStructure.PillsViewModel;
import com.example.pillsmessaging.R;
import com.example.pillsmessaging.TimeSaver.TimePickerFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class AddItemDialogFragment extends DialogFragment {
    public static final String TAG = "TAG_DIALOG";
    private EditText editTextDescription;
    private EditText editTextTime;
    private PillsViewModel viewModel;
    private MaterialButton addButton;
    private MaterialTextView textInfo;

    public AddItemDialogFragment(Context context) {
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        View view = getLayoutInflater().inflate(R.layout.add_dialog, null);
        builder.setView(view);
        builder.setTitle(R.string.dialog_title);

        viewModel = new ViewModelProvider(requireActivity()).get(PillsViewModel.class);
        addButton = view.findViewById(R.id.add_button);
        editTextDescription = view.findViewById(R.id.text_input_edit_text);
        editTextTime = view.findViewById(R.id.time_text_input_edit_text);
        textInfo = view.findViewById(R.id.text_item_description);
        textInfo.setText("");
        addItem();


        return builder.create();
    }


    private void addItem() {
        createTimePickerFragment();
        buttonAddClickListener();
    }

    private void createTimePickerFragment() {
        editTextTime.setOnClickListener((View v) -> {
            TimePickerFragment timePickerFragment = new TimePickerFragment();
            timePickerFragment.show(requireActivity().getSupportFragmentManager(), "TAG_time_picker");
        });
    }

    private void buttonAddClickListener() {
        addButton.setOnClickListener(v -> {
            String timeText = editTextTime.getText().toString();
            String textDescription = editTextDescription.getText().toString();
            if (!textDescription.equals("")) {
                viewModel.insert(new ItemPill(timeText, textDescription));
                dismiss();
            }
        });
    }

}
