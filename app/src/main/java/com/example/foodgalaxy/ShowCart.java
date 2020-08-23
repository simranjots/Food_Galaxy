package com.example.foodgalaxy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodgalaxy.Database.Database;
import com.example.foodgalaxy.Model.CartItem;
import com.example.foodgalaxy.ViewHolder.RestaurantAdapter;
import com.example.foodgalaxy.ViewHolder.ShowCartAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShowCart extends AppCompatActivity {

    Button placeOrder;
    TextView totalPrice;
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);

        placeOrder = (Button) findViewById(R.id.placeOrder);
        totalPrice = (TextView) findViewById(R.id.totalPrice);


        Database db = new Database(getBaseContext());
        List<CartItem> list  = db.getCarts();

        RecyclerView recyclerView = findViewById(R.id.showCartRecyclerView);
        ShowCartAdapter rcAdap = new ShowCartAdapter(list,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rcAdap);

        for(CartItem item : list)
        {
            double price = Double.parseDouble(item.getPrice()) * Integer.parseInt(item.getQuantity());
            total = total + price;
        }

        totalPrice.setText("Total Price: " +Double.toString(total) + "$");

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowCart.this,OrderPlaced.class);
                startActivity(i);
            }
        });



    }
}