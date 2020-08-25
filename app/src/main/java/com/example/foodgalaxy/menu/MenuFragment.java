package com.example.foodgalaxy.menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgalaxy.Interface.ItemClickListener;
import com.example.foodgalaxy.Model.Menu;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.R;
import com.example.foodgalaxy.Restaurant.RestaurantsList;
import com.example.foodgalaxy.ViewHolder.MenuAdapter;
import com.example.foodgalaxy.ViewHolder.RestaurantAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    View view;
    ArrayList<Menu> menuList = new ArrayList<Menu>();
    int restaurant_Id = 0;
    FirebaseDatabase database;
    DatabaseReference menuRef;
    //FirebaseRecyclerAdapter<Menu, MenuAdapter> adapter;
    RecyclerView recyclerView;

    public MenuFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menulist,container,false);

        database= FirebaseDatabase.getInstance();
        menuRef = database.getReference().child("Menu");

        menuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Iterable<DataSnapshot> children = snapshot.getChildren();
                    for(DataSnapshot data : children)
                    {
                        Menu m = data.getValue(Menu.class);
                        menuList.add(m);
                    }

                    menuList = filterdata(menuList);
                    recyclerView = view.findViewById(R.id.menuRecycler);
                    MenuAdapter rcdp = new MenuAdapter(menuList, getActivity());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(rcdp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public ArrayList<Menu> filterdata(ArrayList<Menu> menuList){

        ArrayList<Menu> result = new ArrayList<Menu>();
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                "R_Id", Context.MODE_PRIVATE);

        restaurant_Id = sharedPref.getInt("Rest_Id",0);




        for(Menu m : menuList)
        {
            if(m.isPredefinedMenu() == false && m.getR_Id() == restaurant_Id )
            {
                result.add(m);
            }
        }

        return result;
    }


}