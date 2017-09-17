package com.shahriar.hasan.monthlyexpenserecorder.Dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.shahriar.hasan.monthlyexpenserecorder.R;

/**
 * Created by H. M. Shahriar on 9/17/2017.
 */

public class AddCategoryDialog extends DialogFragment {

    String[] types = new String[]{
            "Income",
            "Expense"
    };

    // Boolean array for initial selected items
    final boolean[] checkedType = new boolean[]{
            true, // Income
            false // Expense
    };
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.add_category_dialog, null))
                // Add action buttons
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

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
