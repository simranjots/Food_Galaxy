package com.example.foodgalaxy.menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgalaxy.Model.Menu;
import com.example.foodgalaxy.R;
import com.example.foodgalaxy.RestaurantDetailPage;
import com.example.foodgalaxy.ViewHolder.MenuAdapter;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    View view;
    RecyclerView menuRecyclerView;
    ArrayList<Menu> menuList = new ArrayList<Menu>();
    int restaurant_Id;

    public MenuFragment(){

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menulist,container,false);
        menuRecyclerView = (RecyclerView) view.findViewById(R.id.menuRecycler);
        MenuAdapter rcAdap = new MenuAdapter(menuList,getContext());
        menuRecyclerView.setHasFixedSize(true);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        menuRecyclerView.setAdapter(rcAdap);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addData();
        menuList = filterdata(menuList);

    }


    public void addData(){
        menuList.add(new Menu(1,"Chicken", 12.9,"Best chicken tikka with seasoning", 2, false,"https://imagesvc.meredithcorp.io/v3/mm/image?q=85&c=sc&poi=face&url=https%3A%2F%2Fcdn-image.foodandwine.com%2Fsites%2Fdefault%2Ffiles%2Fstyles%2F4_3_horizontal_-_1200x900%2Fpublic%2Findiana-style-fried-chicken-ft-recipe0620.jpg%3Fitok%3DiK4ZWHUz"));
        menuList.add(new Menu(2,"Fries", 13.9,"Sweet Potato fries", 2, false, "https://img.apmcdn.org/4b2716626c9ff3f6e5dfebe520eb592c33cf1e7b/uncropped/941f50-splendid-table-french-fries.jpg"));
    }

    public ArrayList<Menu> filterdata(ArrayList<Menu> menuList){

        ArrayList<Menu> result = new ArrayList<Menu>();
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                "R_Id", Context.MODE_PRIVATE);

        restaurant_Id = sharedPref.getInt("Rest_Id",0);


        for(Menu m : menuList)
        {
            if(m.isPackage() == false && m.getR_Id() == restaurant_Id )
            {
                result.add(m);
            }
        }

        return result;



    }


}