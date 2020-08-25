package com.example.foodgalaxy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodgalaxy.Common.Common;
import com.example.foodgalaxy.Model.Orders;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.ViewHolder.adminPanelAdapter;
import com.example.foodgalaxy.ViewHolder.currentOrderAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class currentOrdersList extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference orders;
    RecyclerView recyclerView;

    ArrayList<Orders> orderList = new ArrayList<Orders>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_orders_list);

        //Init Firebase
        database= FirebaseDatabase.getInstance();
        orders = database.getReference().child("Orders");

        orders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Iterable<DataSnapshot> children = snapshot.getChildren();
                    for(DataSnapshot data : children)
                    {
                        Orders o = data.getValue(Orders.class);
                        if(o.getR_Id() == Common.currentRestaurant.getId() && o.getStatus().equals("0")) {
                            orderList.add(o);
                        }
                    }
                    recyclerView = findViewById(R.id.adminCurrentOrdersRecycler);
                    currentOrderAdapter rcdp = new currentOrderAdapter(orderList, currentOrdersList.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(currentOrdersList.this));
                    recyclerView.setAdapter(rcdp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}