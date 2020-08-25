package com.example.foodgalaxy.Restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.example.foodgalaxy.Database.Database;
import com.example.foodgalaxy.Model.Menu;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.R;
import com.example.foodgalaxy.ViewHolder.RestaurantDetailViewPagerAdapter;
import com.example.foodgalaxy.menu.MenuFragment;
import com.example.foodgalaxy.menu.PreDefinedMenuFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RestaurantDetailPage extends AppCompatActivity {

    TabLayout tableLayout;
    ViewPager viewPager;
    //String restaurant;
    Restaurant rest;
    int r_Id = 0;
    Database databases;
    FirebaseDatabase database;
    DatabaseReference foodList;
    long id ;

    TextView restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail_page);


        //Firebase
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Restaurant");

        restaurantName = (TextView) findViewById(R.id.RestaurantName);
        Restaurant restaurant = getIntent().getParcelableExtra("restaurantDetails");
        restaurantName.setText(restaurant.getName());
        r_Id = restaurant.getId();

        SharedPreferences sharedPreferences = getSharedPreferences("R_Id",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Rest_Id",r_Id);
        editor.commit();


        viewPager = (ViewPager) findViewById(R.id.detailViewPager);

        RestaurantDetailViewPagerAdapter vpAdapter = new RestaurantDetailViewPagerAdapter(getSupportFragmentManager());

        vpAdapter.addFragments(new MenuFragment(), "Menu");
        vpAdapter.addFragments(new PreDefinedMenuFragment(), "Packages");

        viewPager.setAdapter(vpAdapter);

        tableLayout = (TabLayout) findViewById(R.id.detailTabLayout);
        tableLayout.setupWithViewPager(viewPager);



    }



}