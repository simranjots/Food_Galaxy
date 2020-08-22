package com.example.foodgalaxy.menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.example.foodgalaxy.ViewHolder.MenuAdapter;


import java.util.ArrayList;

public class PreDefinedMenuFragment extends Fragment {

    View view;
    RecyclerView menuRecyclerView;
    ArrayList<Menu> menuList = new ArrayList<Menu>();
    int restaurant_Id;

    public PreDefinedMenuFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.predefinedmenulist,container,false);
        menuRecyclerView = (RecyclerView) view.findViewById(R.id.predefinedMenuRecycler);
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
        menuList.add(new Menu(1,"Meat Board", 12.9,"Chicken, Salami, Steak, Smoked beacon", 2, true,"https://cdn0.wideopeneats.com/wp-content/uploads/2018/03/different-types-of-meat-720x405.png"));
        menuList.add(new Menu(2,"Fries", 13.9,"Sweet Potato fries", 2, false,"https://img.apmcdn.org/4b2716626c9ff3f6e5dfebe520eb592c33cf1e7b/uncropped/941f50-splendid-table-french-fries.jpg"));
    }

    public ArrayList<Menu> filterdata(ArrayList<Menu> menuList){

        ArrayList<Menu> result = new ArrayList<Menu>();
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                "R_Id", Context.MODE_PRIVATE);

        restaurant_Id = sharedPref.getInt("Rest_Id",0);

        for(Menu m : menuList)
        {
            if(m.isPackage() == true && m.getR_Id() == restaurant_Id)
            {
                result.add(m);
            }
        }

        return result;



    }
}

