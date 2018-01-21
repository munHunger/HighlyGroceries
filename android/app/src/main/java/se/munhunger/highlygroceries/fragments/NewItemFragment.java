package se.munhunger.highlygroceries.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import se.munhunger.highlygroceries.R;
import se.munhunger.highlygroceries.service.ListService;

/**
 * Created by munHunger on 2018-01-21.
 */

public class NewItemFragment extends DialogFragment {

    public void setDialogListener(NewItemFragment.DialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    public interface DialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String success);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    private NewItemFragment.DialogListener dialogListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = getActivity().getLayoutInflater().inflate(R.layout.create_item, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.create_list)
                .setView(view)
                .setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TextView textField = view.findViewById(R.id.newListName);
                        NewItemFragment.this.getDialog().dismiss();
                        if(dialogListener != null)
                            dialogListener.onDialogPositiveClick(NewItemFragment.this, textField.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NewItemFragment.this.getDialog().cancel();
                        if(dialogListener != null)
                            dialogListener.onDialogNegativeClick(NewItemFragment.this);
                    }
                });
        return builder.create();
    }
}
