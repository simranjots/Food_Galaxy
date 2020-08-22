package com.example.foodgalaxy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;

import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.ViewHolder.RestaurantDetailViewPagerAdapter;
import com.example.foodgalaxy.menu.MenuFragment;
import com.example.foodgalaxy.menu.PreDefinedMenuFragment;
import com.google.android.material.tabs.TabLayout;

public class RestaurantDetailPage extends AppCompatActivity {

    TabLayout tableLayout;
    ViewPager viewPager;
    SharedPreferences sharedpreferences;
    Restaurant restaurant;
    int r_Id = 0;

    TextView restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail_page);

        restaurantName = (TextView) findViewById(R.id.RestaurantName);
        restaurant = getIntent().getParcelableExtra("restaurantDetails");
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