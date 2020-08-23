package com.example.foodgalaxy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodgalaxy.Model.FoodStyle;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.ViewHolder.RestaurantAdapter;

import java.util.ArrayList;

public class RestaurantsList extends AppCompatActivity {

    ArrayList<Restaurant> restaurantsList = new ArrayList<Restaurant>();
    boolean IsDelivery;
    int size;
    ArrayList<FoodStyle> listOfStyles = new ArrayList<FoodStyle>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_list);
        addData();

        restaurantsList = filterdata(restaurantsList);

        RecyclerView recyclerView = findViewById(R.id.restaurantRecycler);
        RestaurantAdapter rcAdap = new RestaurantAdapter(restaurantsList,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rcAdap);
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