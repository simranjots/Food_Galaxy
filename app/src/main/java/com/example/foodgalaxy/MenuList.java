package com.example.foodgalaxy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgalaxy.Model.FoodStyle;
import com.example.foodgalaxy.Model.Menu;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.ViewHolder.MenuAdapter;
import com.example.foodgalaxy.ViewHolder.RestaurantAdapter;
import com.example.foodgalaxy.ViewHolder.RestaurantDetailViewPagerAdapter;

import java.util.ArrayList;


public class MenuList extends AppCompatActivity {
    ArrayList<Menu> menuList = new ArrayList<Menu>();
    int restaurant_Id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_list);
        addData();

        menuList = filterdata(menuList);

        RecyclerView recyclerView = findViewById(R.id.menuRecycler);
        MenuAdapter rcAdap = new MenuAdapter(menuList,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rcAdap);
    }

    public void addData(){
        menuList.add(new Menu(1,"Chicken", "60 Castlegrove",20, 50, 1, true,"forexample.com"));
        menuList.add(new Menu(2,"Second restaurant", "60 Castlegrove",10, 50, 1, false,"forexample.com"));
    }

    public ArrayList<Menu> filterdata(ArrayList<Menu> menuList){

        ArrayList<Menu> result = new ArrayList<Menu>();
        restaurant_Id = new RestaurantDetailViewPagerAdapter(getSupportFragmentManager()).getRestaurant_Id();






        return result;



    }

}
