package se.munhunger.highlygroceries.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import se.munhunger.highlygroceries.R;
import se.munhunger.highlygroceries.service.ListService;

/**
 * Created by munHunger on 2018-01-20.
 */

public class NewListFragment extends DialogFragment {

    public void setDialogListener(DialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    public interface DialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    private DialogListener dialogListener;

    private ListService listService = new ListService();
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = getActivity().getLayoutInflater().inflate(R.layout.create_list, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.create_list)
                .setView(view)
                .setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TextView textField = view.findViewById(R.id.newListName);
                        listService.createList(textField.getText().toString());
                        NewListFragment.this.getDialog().dismiss();
                        if(dialogListener != null)
                            dialogListener.onDialogPositiveClick(NewListFragment.this);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NewListFragment.this.getDialog().cancel();
                        if(dialogListener != null)
                            dialogListener.onDialogNegativeClick(NewListFragment.this);
                    }
                });
        return builder.create();
    }
}
