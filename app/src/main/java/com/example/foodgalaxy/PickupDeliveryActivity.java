package com.example.foodgalaxy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PickupDeliveryActivity extends AppCompatActivity {

    private Button btnDelivery, btnPickup;
    public boolean isDelivery = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickup_delivery_page);

        btnDelivery = (Button) findViewById(R.id.btnDelivery);
        btnPickup = (Button) findViewById(R.id.btnPickup);

        btnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliveryClicked();
            }
        });

        btnPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDelivery = false;
                Intent i1 = new Intent(PickupDeliveryActivity.this, SizeAndDateActivity.class);
                i1.putExtra("IsDelivery", isDelivery);
                startActivity(i1);
            }
        });
    }

    public void deliveryClicked(){
        isDelivery = true;
        Intent i2 = new Intent(PickupDeliveryActivity.this, DeliveryAddressDetails.class);
        i2.putExtra("IsDelivery", isDelivery);
        startActivity(i2);
    }
}
