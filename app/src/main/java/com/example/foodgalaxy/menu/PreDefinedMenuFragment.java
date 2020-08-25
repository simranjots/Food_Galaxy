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
import com.example.foodgalaxy.R;
import com.example.foodgalaxy.ViewHolder.PredefinedMenuAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class PreDefinedMenuFragment extends Fragment {

    View view;
    RecyclerView menuRecyclerView;
    ArrayList<Menu> menuList = new ArrayList<Menu>();
    int restaurant_Id;
    FirebaseDatabase database;
    DatabaseReference menuRef;
   // FirebaseRecyclerAdapter<Menu, MenuAdapter> adapter;
    RecyclerView recyclerView;

    public PreDefinedMenuFragment(){

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.predefinedmenulist,container,false);

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
                    Toast.makeText(getActivity(), "size:" + menuList.size(),
                            Toast.LENGTH_LONG).show();
                    Toast.makeText(getActivity(), "1: " + menuList.get(0).ipackage(),
                            Toast.LENGTH_LONG).show();
                    Toast.makeText(getActivity(), "2: " + menuList.get(1).ipackage(),
                            Toast.LENGTH_LONG).show();

                    menuList = filterdata(menuList);
                    recyclerView = view.findViewById(R.id.predefinedMenuRecycler);
                    PredefinedMenuAdapter rcdp = new PredefinedMenuAdapter(menuList, getActivity());
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
        //Init Firebase

    }


    public ArrayList<Menu> filterdata(ArrayList<Menu> menuList){

        ArrayList<Menu> result = new ArrayList<Menu>();
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                "R_Id", Context.MODE_PRIVATE);

        restaurant_Id = sharedPref.getInt("Rest_Id",0);

        Toast.makeText(getActivity(), "Rest:" + restaurant_Id,
                Toast.LENGTH_LONG).show();


        for(Menu m : menuList)
        {
            Toast.makeText(getActivity(), "package:" + m.ipackage(),
                    Toast.LENGTH_LONG).show();
            if(m.ipackage() && m.getR_Id() == restaurant_Id)
            {
                result.add(m);
            }
        }

        return result;



    }
}

