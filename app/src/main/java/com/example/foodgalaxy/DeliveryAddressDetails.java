package com.example.foodgalaxy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodgalaxy.Model.Address;

public class DeliveryAddressDetails extends AppCompatActivity {

    private EditText unitNo, street, city, postal, country;
    private Button deliverHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_details_page);

        unitNo = (EditText) findViewById(R.id.unit_number);
        street = (EditText) findViewById(R.id.street_add);
        city = (EditText) findViewById(R.id.city);
        postal = (EditText) findViewById(R.id.postal_code);
        country = (EditText) findViewById(R.id.country);
        deliverHere = (Button) findViewById(R.id.btnDelivereHere);

        deliverHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliverHereClicked();
            }
        });

    }

    public void deliverHereClicked(){
        Address deliveryAddress = new Address(unitNo.getText().toString(),street.getText().toString(),city.getText().toString(),postal.getText().toString(),country.getText().toString());
        Intent i = new Intent(DeliveryAddressDetails.this, SizeAndDateActivity.class);
        i.putExtra("address",deliveryAddress);
        startActivity(i);

    }


}
