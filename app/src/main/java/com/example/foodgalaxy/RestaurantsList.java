package com.example.foodgalaxy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foodgalaxy.Interface.ItemClickListener;
import com.example.foodgalaxy.Model.FoodStyle;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.ViewHolder.MenuViewHolder;
import com.example.foodgalaxy.ViewHolder.RestaurantAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestaurantsList extends AppCompatActivity {

    ArrayList<Restaurant> restaurantsList = new ArrayList<Restaurant>();
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
        addData();


        //Init Firebase
        database= FirebaseDatabase.getInstance();
        restaurant = database.getReference("Restaurant");
        restaurantsList = filterdata(restaurantsList);

       recyclerView = findViewById(R.id.restaurantRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadMenu();

    }

    private void loadMenu() {


        adapter = new FirebaseRecyclerAdapter<Restaurant, MenuViewHolder>(Restaurant.class,R.layout.menu_item,MenuViewHolder.class,restaurant) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Restaurant model, int position) {
                viewHolder.txtRestaurantName.setText(model.getName());

                Picasso.with(getBaseContext()).load(model.getImageLink())
                        .into(viewHolder.imageView);
                final Restaurant clickItem = model;
                viewHolder.buttonDirection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent foodList = new Intent(RestaurantsList.this,ResetPasswordActivity.class);
                        startActivity(foodList);
                    }
                });
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Get categoryId and send to new activity
                        Intent RestaurantList = new Intent(RestaurantsList.this,RestaurantDetailPage.class);
                        RestaurantList.putExtra("restaurantDetails",adapter.getRef(position).getKey());
                        startActivity(RestaurantList);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    public void addData(){
        restaurantsList.add(new Restaurant(1,"Fisrt restaurant", "60 Castlegrove",20, 50, 1, true,"forexample.com"));
        restaurantsList.add(new Restaurant(2,"Second restaurant", "60 Castlegrove",10, 50, 1, false,"forexample.com"));
    }

    public ArrayList<Restaurant> filterdata(ArrayList<Restaurant> restaurantsList){

        ArrayList<Restaurant> result = new ArrayList<Restaurant>();
        size = Integer.parseInt(getIntent().getStringExtra("size"));
        IsDelivery = getIntent().getBooleanExtra("IsDelivery", false);
        listOfStyles = getIntent().getParcelableArrayListExtra("style");


        for (Restaurant r : restaurantsList)
        {
            if((size >= r.getcMinSize() && size <= r.getcMaxSize()) && IsDelivery == r.isDelivery() ){
                for(FoodStyle f : listOfStyles)
                {
                    if(f.getId() == r.getFS_Id())
                    {
                        result.add(r);
                    }
                }
            }



        }

        return result;



    }

}