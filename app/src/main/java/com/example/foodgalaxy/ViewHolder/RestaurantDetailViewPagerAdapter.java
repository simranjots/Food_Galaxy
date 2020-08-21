package com.example.foodgalaxy.ViewHolder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class RestaurantDetailViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private ArrayList<String> tilteList = new ArrayList<>();
    int restaurant_Id = 0;

    public RestaurantDetailViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tilteList.get(position);
    }

    @Override
    public int getCount() {
        return tilteList.size();
    }

    public void addFragments(Fragment fragment, String title, int restaurant_Id)
    {
        fragmentList.add(fragment);
        tilteList.add(title);
        this.restaurant_Id = restaurant_Id;
    }

    public int getRestaurant_Id()
    {
        return restaurant_Id;
    }
}
