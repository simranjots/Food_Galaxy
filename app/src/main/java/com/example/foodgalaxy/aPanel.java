package com.example.foodgalaxy;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgalaxy.Common.Common;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.ViewHolder.adminPanelAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class aPanel extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference restaurant;
    RecyclerView recyclerView;

    ArrayList<Restaurant> restaurantsList = new ArrayList<Restaurant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_restaurants_list);

        //Init Firebase
        database= FirebaseDatabase.getInstance();
        restaurant = database.getReference().child("Restaurant");

        restaurant.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Iterable<DataSnapshot> children = snapshot.getChildren();
                    for(DataSnapshot data : children)
                    {
                        Restaurant r = data.getValue(Restaurant.class);
                        if(r.getUid() == Common.currentUser.getId()) {
                            restaurantsList.add(r);
                        }
                    }
                    recyclerView = findViewById(R.id.adminRestaurantRecycler);
                    adminPanelAdapter rcdp = new adminPanelAdapter(restaurantsList, aPanel.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(aPanel.this));
                    recyclerView.setAdapter(rcdp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
