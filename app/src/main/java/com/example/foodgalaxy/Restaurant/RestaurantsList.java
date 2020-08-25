package com.example.foodgalaxy.Restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.foodgalaxy.Model.FoodStyle;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.R;
import com.example.foodgalaxy.ViewHolder.MenuViewHolder;
import com.example.foodgalaxy.ViewHolder.RestaurantAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsList extends AppCompatActivity {

    List<Restaurant> restaurantsList = new ArrayList<Restaurant>();
    boolean IsDelivery;
    int size;
    ArrayList<FoodStyle> listOfStyles = new ArrayList<FoodStyle>();

    FirebaseDatabase database;
    DatabaseReference restaurant;
    FirebaseRecyclerAdapter<Restaurant,MenuViewHolder> adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_list);

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
                        restaurantsList.add(r);
                    }

                    restaurantsList = getfilterdata(restaurantsList);
                    recyclerView = findViewById(R.id.restaurantRecycler);
                    RestaurantAdapter rcdp = new RestaurantAdapter(restaurantsList,RestaurantsList.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(RestaurantsList.this));
                    recyclerView.setAdapter(rcdp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public List<Restaurant> getfilterdata(List<Restaurant> restaurantsList){

        ArrayList<Restaurant> result = new ArrayList<Restaurant>();
        size = Integer.parseInt(getIntent().getStringExtra("size"));
        IsDelivery = getIntent().getBooleanExtra("isDelivery", false);
        listOfStyles = getIntent().getParcelableArrayListExtra("style");

        for (Restaurant r : restaurantsList)
        {
            if((size >= r.getcMinsize() && size <= r.getcMaxsize()) && IsDelivery == r.isDelivery() ){
                for(FoodStyle f : listOfStyles)
                {
                    if(f.getId() == r.getFs_id())
                    {
                        result.add(r);
                    }
                }
            }
        }

        return result;



    }

}