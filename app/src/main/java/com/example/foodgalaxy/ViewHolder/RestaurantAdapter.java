package com.example.foodgalaxy.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.OrderStatus;
import com.example.foodgalaxy.R;
import com.example.foodgalaxy.Restaurant.RestaurantDetailPage;
import com.example.foodgalaxy.Restaurant.RestaurantsList;
import com.example.foodgalaxy.menu.FoodDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends  RecyclerView.Adapter<RestaurantAdapter.ViewHolder>  {

    private List<Restaurant> restaurants;
    private Context mContext;

    public RestaurantAdapter(List<Restaurant> restaurants, Context mContext)
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
        Picasso.with(this.mContext).load(restaurants.get(position).getImageLink()).into(holder.image);
        holder.direct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), OrderStatus.class));
            }
        });

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
        ImageView image;
        Button direct;
        LinearLayout restaurantList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.menu_name);
            image = itemView.findViewById(R.id.menu_image);
            direct = itemView.findViewById(R.id.menu_direction);
            restaurantList = itemView.findViewById(R.id.click);

        }
    }
}
