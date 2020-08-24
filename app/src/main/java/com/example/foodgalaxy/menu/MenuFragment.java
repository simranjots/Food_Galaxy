package com.example.foodgalaxy.menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgalaxy.FoodDetail;
import com.example.foodgalaxy.Interface.ItemClickListener;
import com.example.foodgalaxy.Model.Menu;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.R;
import com.example.foodgalaxy.ResetPasswordActivity;
import com.example.foodgalaxy.RestaurantDetailPage;
import com.example.foodgalaxy.RestaurantsList;
import com.example.foodgalaxy.ViewHolder.MenuAdapter;
import com.example.foodgalaxy.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    View view;
    ArrayList<Menu> menuList = new ArrayList<Menu>();
    int restaurant_Id;
    FirebaseDatabase database;
    DatabaseReference menuRef;
    FirebaseRecyclerAdapter<Menu, MenuAdapter> adapter;
    RecyclerView recyclerView;

    public MenuFragment(){

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



                viewHolder.name.setText(model.getName());

                viewHolder.price.setText(Double.toString(model.getPrice()) + "$");

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Get categoryId and send to new activity
                        Intent RestaurantList = new Intent(getContext(), FoodDetail.class);
                        RestaurantList.putExtra("menuDetail",adapter.getRef(position).getKey());
                        startActivity(RestaurantList);
                        Toast.makeText(getContext(), model.getId(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

//    public void addData(){
//        menuList.add(new Menu("1","Chicken", "12.9","Best chicken tikka with seasoning", 2, false,"https://imagesvc.meredithcorp.io/v3/mm/image?q=85&c=sc&poi=face&url=https%3A%2F%2Fcdn-image.foodandwine.com%2Fsites%2Fdefault%2Ffiles%2Fstyles%2F4_3_horizontal_-_1200x900%2Fpublic%2Findiana-style-fried-chicken-ft-recipe0620.jpg%3Fitok%3DiK4ZWHUz"));
//        menuList.add(new Menu("2","Fries", "13.9","Sweet Potato fries", 2, false, "https://img.apmcdn.org/4b2716626c9ff3f6e5dfebe520eb592c33cf1e7b/uncropped/941f50-splendid-table-french-fries.jpg"));
//    }

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