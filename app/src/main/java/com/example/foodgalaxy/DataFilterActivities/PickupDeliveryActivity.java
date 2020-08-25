package com.example.foodgalaxy.DataFilterActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodgalaxy.Common.Common;
import com.example.foodgalaxy.Database.Database;
import com.example.foodgalaxy.DeliveryAddressDetails;
import com.example.foodgalaxy.R;

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
                Common.isDeliver = false;
                Intent i1 = new Intent(PickupDeliveryActivity.this, SizeAndDateActivity.class);
                startActivity(i1);
            }
        });
    }

    public void deliveryClicked(){
        Common.isDeliver = true;
        Database db = new Database(getBaseContext());
        db.cleanCart();
        Intent i2 = new Intent(PickupDeliveryActivity.this, SizeAndDateActivity.class);
        startActivity(i2);
    }
}
