package com.example.foodgalaxy.menu;

import android.content.Context;
import android.content.Intent;
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

import com.example.foodgalaxy.Interface.ItemClickListener;
import com.example.foodgalaxy.Model.Menu;
import com.example.foodgalaxy.R;
import com.example.foodgalaxy.ViewHolder.MenuAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

public class PreDefinedMenuFragment extends Fragment {

    View view;
    RecyclerView menuRecyclerView;
    ArrayList<Menu> menuList = new ArrayList<Menu>();
    int restaurant_Id;
    FirebaseDatabase database;
    DatabaseReference menuRef;
    FirebaseRecyclerAdapter<Menu, MenuAdapter> adapter;
    RecyclerView recyclerView;

    public PreDefinedMenuFragment(){

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menulist,container,false);

        recyclerView = view.findViewById(R.id.menuRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadMenu();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Init Firebase
        database= FirebaseDatabase.getInstance();
        menuRef = database.getReference("Menu");

        menuList = filterdata(menuList);

    }


    private void loadMenu() {


        adapter =  new FirebaseRecyclerAdapter<Menu, MenuAdapter>(Menu.class,R.layout.menus,MenuAdapter.class,menuRef) {
            @Override
            protected void populateViewHolder(MenuAdapter viewHolder, Menu model, int position) {

                ArrayList<Menu> menus = new ArrayList<Menu>();
                double price = 12.9;


                viewHolder.name.setText(model.getName());

                viewHolder.price.setText(model.getPrice() + "$");

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Get categoryId and send to new activity
                        Intent RestaurantList = new Intent(getContext(), FoodDetail.class);
                        RestaurantList.putExtra("menuDetail",adapter.getRef(position).getKey());
                        startActivity(RestaurantList);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }


//    public void addData(){
//        menuList.add(new Menu("3","Meat Board", 12.9,"Chicken, Salami, Steak, Smoked beacon", 2, true,"https://cdn0.wideopeneats.com/wp-content/uploads/2018/03/different-types-of-meat-720x405.png"));
//        menuList.add(new Menu("2","Fries", 13.9,"Sweet Potato fries", 2, false,"https://img.apmcdn.org/4b2716626c9ff3f6e5dfebe520eb592c33cf1e7b/uncropped/941f50-splendid-table-french-fries.jpg"));
//    }

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

