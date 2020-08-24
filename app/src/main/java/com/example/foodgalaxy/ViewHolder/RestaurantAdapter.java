package com.example.foodgalaxy.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.R;
import com.example.foodgalaxy.Restaurant.RestaurantDetailPage;

import java.util.ArrayList;

public class RestaurantAdapter extends  RecyclerView.Adapter<RestaurantAdapter.ViewHolder>  {

    private ArrayList<Restaurant> restaurants;
    private Context mContext;

    public RestaurantAdapter(ArrayList<Restaurant> restaurants, Context mContext)
    {
        this.restaurants = restaurants;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.menu_item, parent, false);
        RestaurantAdapter.ViewHolder holder = new RestaurantAdapter.ViewHolder(listItem);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.ViewHolder holder, final int position) {

        holder.name.setText(restaurants.get(position).getName());



        holder.restaurantList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), RestaurantDetailPage.class);
                intent.putExtra("restaurantDetails",restaurants.get(position));
                v.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        LinearLayout restaurantList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.menu_name);
            restaurantList = itemView.findViewById(R.id.click);

        }
    }
}
