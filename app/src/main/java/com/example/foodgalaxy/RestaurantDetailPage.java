package com.example.foodgalaxy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.ViewHolder.RestaurantDetailViewPagerAdapter;
import com.example.foodgalaxy.menu.MenuFragment;
import com.example.foodgalaxy.menu.PreDefinedMenuFragment;
import com.google.android.material.tabs.TabLayout;

public class RestaurantDetailPage extends AppCompatActivity {

    TabLayout tableLayout;
    ViewPager viewPager;

    TextView restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail_page);

        restaurantName = (TextView) findViewById(R.id.RestaurantName);

        Restaurant restaurant = getIntent().getParcelableExtra("restaurantDetails");

        restaurantName.setText(restaurant.getName());


        viewPager = (ViewPager) findViewById(R.id.detailViewPager);

        RestaurantDetailViewPagerAdapter vpAdapter = new RestaurantDetailViewPagerAdapter(getSupportFragmentManager());

        vpAdapter.addFragments(new MenuFragment(), "Menu", restaurant.getId());
        vpAdapter.addFragments(new PreDefinedMenuFragment(), "Packages", restaurant.getId());

        viewPager.setAdapter(vpAdapter);

        tableLayout = (TabLayout) findViewById(R.id.detailTabLayout);
        tableLayout.setupWithViewPager(viewPager);


    }
}