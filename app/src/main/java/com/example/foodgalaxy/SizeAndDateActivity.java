package com.example.foodgalaxy;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;

import com.example.foodgalaxy.Common.MultiSelectionSpinner;
import com.example.foodgalaxy.Model.FoodStyle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class SizeAndDateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText size, dateOfBooking, timeOfBooking;
    MultiSelectionSpinner style;
    Button searchbtn, dateBtn, timeBtn;
    private int mYear, mMonth, mDay, mHour, mMinute;
    boolean IsDelivery;
    private String dateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_size_and_date);

        style = (MultiSelectionSpinner) findViewById(R.id.style);
        size = (EditText) findViewById(R.id.size);
        dateOfBooking = (EditText) findViewById(R.id.dateBooking);
        timeOfBooking = (EditText) findViewById(R.id.dateTimeOfBooking);
        searchbtn = (Button) findViewById(R.id.btnSearchRest);
        dateBtn = (Button) findViewById(R.id.btn_date);
        timeBtn = (Button) findViewById(R.id.btn_time);

        dateTime = dateOfBooking.getText().toString() + " " + timeOfBooking.getText().toString();

        ArrayList<FoodStyle> categories = new ArrayList<FoodStyle>();
        categories.add(new FoodStyle(1,"Indian"));
        categories.add(new FoodStyle(2,"Chinese"));
        categories.add(new FoodStyle(3,"Mexican"));
        categories.add(new FoodStyle(4,"Italian"));

        style.setItems(categories);

        ArrayList<FoodStyle> categorySelected = new ArrayList<FoodStyle>();
        categorySelected.add(new FoodStyle(1,"Indian"));

        style.setSelection(categorySelected);

        IsDelivery = getIntent().getBooleanExtra("IsDelivery", false);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(SizeAndDateActivity.this, RestaurantsList.class);
                i1.putExtra("size", size.getText().toString());
                i1.putExtra("style", style.getSelectedItems());
                i1.putExtra("IsDelivery", IsDelivery);
                i1.putExtra("dateTime",dateTime);
                startActivity(i1);
            }
        });

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateSelection();
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSelection();
            }
        });


    }



    public void dateSelection(){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        dateOfBooking.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    public void timeSelection(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        timeOfBooking.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}