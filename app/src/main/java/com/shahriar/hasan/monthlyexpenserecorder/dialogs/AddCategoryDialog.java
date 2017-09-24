package com.shahriar.hasan.monthlyexpenserecorder.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shahriar.hasan.monthlyexpenserecorder.interfaces.AddCategoryDialogListener;
import com.shahriar.hasan.monthlyexpenserecorder.R;

/**
 * Created by H. M. Shahriar on 9/17/2017.
 */

public class AddCategoryDialog extends DialogFragment {

    AddCategoryDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the AddCategoryDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (AddCategoryDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement AddCategoryDialogListener");
        }
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View dialogView = inflater.inflate(R.layout.add_category_dialog, null);
        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        final EditText categoryName = (EditText)dialogView.findViewById(R.id.categoryName);
                        final EditText categoryDescription = (EditText) dialogView.findViewById(R.id.categoryDescription);
                        RadioGroup radioGroup = (RadioGroup) dialogView.findViewById(R.id.radio);
                        // get selected radio button from radioGroup
                        int selectedId = radioGroup.getCheckedRadioButtonId();

                        // find the radiobutton by returned id
                        RadioButton radioButton = (RadioButton) dialogView.findViewById(selectedId);
                        String tag = radioButton.getTag().toString();
                        String name = categoryName.getText().toString();
                        String desc = categoryDescription.getText().toString();
                        System.out.println("Category Name " + name + " Category Description "+ desc);
                        mListener.onSaveCategoryClicked(name,desc, tag.equals("1"));
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddCategoryDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
