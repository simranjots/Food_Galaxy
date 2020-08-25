package com.example.foodgalaxy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodgalaxy.Common.Common;

public class adminOptions extends AppCompatActivity {

    Button btnmenu, btnpackages, btnpastorders, btncurrentorders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_panel);


        btnmenu = (Button) findViewById(R.id.menuPage);
        btnpackages = (Button) findViewById(R.id.pmenuPage);
        btnpastorders = (Button) findViewById(R.id.pastOrders);
        btncurrentorders = (Button) findViewById(R.id.currentOrders);

        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.isPredefine = false;

                startActivity(new Intent(adminOptions.this,adminMenu.class));

            }
        });

        btnpackages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.isPredefine = true;
                startActivity(new Intent(adminOptions.this,adminMenu.class));

            }
        });

        btnpastorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.isCurrentOrder = false;
                startActivity(new Intent(adminOptions.this,pastOrders.class));

            }
        });

        btncurrentorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.isCurrentOrder = true;

                startActivity(new Intent(adminOptions.this,currentOrdersList.class));

            }
        });


    }
}
