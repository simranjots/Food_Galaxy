package com.example.foodgalaxy.Common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.foodgalaxy.Model.FoodStyle;

import java.util.ArrayList;
import java.util.Arrays;

public class MultiSelectionSpinner extends androidx.appcompat.widget.AppCompatSpinner implements
        DialogInterface.OnMultiChoiceClickListener {

    ArrayList<FoodStyle> items = null;
    boolean[] selection = null;
    ArrayAdapter adapter;

    public MultiSelectionSpinner(Context context) {
        super(context);

        adapter = new ArrayAdapter(context,
                android.R.layout.simple_spinner_item);
        super.setAdapter(adapter);
    }

    public MultiSelectionSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        adapter = new ArrayAdapter(context,
                android.R.layout.simple_spinner_item);
        super.setAdapter(adapter);
    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (selection != null && which < selection.length) {
            selection[which] = isChecked;

            adapter.clear();
            adapter.add(buildSelectedItemString());
        } else {
            throw new IllegalArgumentException(
                    "Argument 'which' is out of bounds.");
        }
    }

    @Override
    public boolean performClick() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        String[] itemNames = new String[items.size()];

        for (int i = 0; i < items.size(); i++) {
            itemNames[i] = items.get(i).getName();
        }

        builder.setMultiChoiceItems(itemNames, selection, this);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {
                // Do nothing
            }
        });

        builder.show();

        return true;
    }

    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        throw new RuntimeException(
                "setAdapter is not supported by MultiSelectSpinner.");
    }

    public void setItems(ArrayList<FoodStyle> items) {
        this.items = items;
        selection = new boolean[this.items.size()];
        adapter.clear();
        adapter.add("");
        Arrays.fill(selection, false);
    }

    public void setSelection(ArrayList<FoodStyle> selection) {
        for (int i = 0; i < this.selection.length; i++) {
            this.selection[i] = false;
        }

        for (FoodStyle sel : selection) {
            for (int j = 0; j < items.size(); ++j) {
                if (items.get(j).getName().equals(sel.getName())) {
                    this.selection[j] = true;
                }
            }
        }

        adapter.clear();
        adapter.add(buildSelectedItemString());
    }

    private String buildSelectedItemString() {
        StringBuilder sb = new StringBuilder();
        boolean foundOne = false;

        for (int i = 0; i < items.size(); ++i) {
            if (selection[i]) {
                if (foundOne) {
                    sb.append(", ");
                }

                foundOne = true;

                sb.append(items.get(i).getName());
            }
        }

        return sb.toString();
    }

    public ArrayList<FoodStyle> getSelectedItems() {
        ArrayList<FoodStyle> selectedItems = new ArrayList<>();

        for (int i = 0; i < items.size(); ++i) {
            if (selection[i]) {
                selectedItems.add(items.get(i));
            }
        }

        return selectedItems;
    }
}
