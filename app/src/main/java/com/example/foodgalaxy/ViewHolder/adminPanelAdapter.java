package com.example.foodgalaxy.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgalaxy.Common.Common;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.adminOptions;
import com.example.foodgalaxy.menu.FoodDetail;
import com.example.foodgalaxy.Model.Menu;
import com.example.foodgalaxy.R;


import java.util.ArrayList;

public class adminPanelAdapter extends  RecyclerView.Adapter<adminPanelAdapter.ViewHolder>  {
    private ArrayList<Restaurant> rList;
    private Context mContext;

    public adminPanelAdapter(ArrayList<Restaurant> rList, Context mContext)
    {
        this.rList = rList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public adminPanelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.admin_restaurant_layout, parent, false);
        adminPanelAdapter.ViewHolder holder = new adminPanelAdapter.ViewHolder(listItem);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull adminPanelAdapter.ViewHolder holder, final int position) {

        holder.name.setText(rList.get(position).getName());
        holder.address.setText(rList.get(position).getAddress());


        holder.restList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), adminOptions.class);
                intent.putExtra("adminRestDetail",rList.get(position));
                Common.currentRestaurant = rList.get(position);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView address;
        RelativeLayout restList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.adminRestaurantName);
            address = itemView.findViewById(R.id.adminRestAddress);
            restList = itemView.findViewById(R.id.adminRestaurantList);

        }
    }
}


