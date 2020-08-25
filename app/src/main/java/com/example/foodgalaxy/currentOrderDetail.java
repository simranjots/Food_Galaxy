package com.example.foodgalaxy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodgalaxy.Common.Common;
import com.example.foodgalaxy.Model.CartItem;
import com.example.foodgalaxy.Model.Orders;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.Model.User;
import com.example.foodgalaxy.ViewHolder.adminPanelAdapter;
import com.example.foodgalaxy.ViewHolder.currentOrderMenuAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class currentOrderDetail extends AppCompatActivity {

    TextView orderNo, userName, address;
    Button completeOrder;

    FirebaseDatabase database;
    DatabaseReference orders;
    DatabaseReference users;
    RecyclerView recyclerView;
    String status = "";
    ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
    Orders o1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order_detail);


        orderNo = (TextView) findViewById(R.id.orderDetailId);
        userName = (TextView) findViewById(R.id.orderUserName);
        address = (TextView) findViewById(R.id.orderDetailAddress);
        completeOrder = (Button) findViewById(R.id.completeOrderBtn);

        //Init Firebase
        database= FirebaseDatabase.getInstance();
        orders = database.getReference().child("Orders");
        users = database.getReference().child("User");

        if(Common.isCurrentOrder) {
            o1 = getIntent().getParcelableExtra("currentOrder");
        }
        else
        {
            o1 = getIntent().getParcelableExtra("pastOrder");
        }
        if(o1!=null)
        {
            orderNo.setText(Long.toString(o1.getId()));
            address.setText(o1.getDeliveryAddress());
        }

        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Iterable<DataSnapshot> children = snapshot.getChildren();
                    for(DataSnapshot data : children)
                    {
                        User r = data.getValue(User.class);
                        if(r!=null) {
                            if (r.getId() == o1.getU_Id()) {
                                userName.setText(r.getName());
                                break;
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        orders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Iterable<DataSnapshot> children = snapshot.getChildren();
                    for(DataSnapshot data : children)
                    {
                        Orders o = data.getValue(Orders.class);
                        if(o.getId() == o1.getId()) {
                            cartItems.addAll(o.getFoods());
                        }
                    }
                    recyclerView = findViewById(R.id.orderMenuListRecycler);
                    currentOrderMenuAdapter rcdp = new currentOrderMenuAdapter(cartItems, currentOrderDetail.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(currentOrderDetail.this));
                    recyclerView.setAdapter(rcdp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        if(Common.isCurrentOrder) {
            Toast.makeText(currentOrderDetail.this, "O_Id" +  o1.getId() , Toast.LENGTH_SHORT).show();

            completeOrder.setVisibility(View.VISIBLE);

            completeOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!o1.getDeliveryAddress().isEmpty())
                    {
                    status = "1";
                    }
                    else
                    {
                        status = "2";
                    }


                    orders.child(Long.toString(o1.getId())).child("status").setValue(status);
                    startActivity(new Intent(currentOrderDetail.this,aPanel.class));
                    finish();

                }

            });
        }
        else
        {
            completeOrder.setVisibility(View.INVISIBLE);
        }
    }
}