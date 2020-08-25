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
import com.example.foodgalaxy.Model.CartItem;
import com.example.foodgalaxy.Model.Orders;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.adminOptions;
import com.example.foodgalaxy.R;


import java.util.ArrayList;

public class currentOrderMenuAdapter extends  RecyclerView.Adapter<currentOrderMenuAdapter.ViewHolder>  {
    private ArrayList<CartItem> rList;
    private Context mContext;

    public currentOrderMenuAdapter(ArrayList<CartItem> rList, Context mContext)
    {
        this.rList = rList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public currentOrderMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.order_menu_detial_layout, parent, false);
        currentOrderMenuAdapter.ViewHolder holder = new currentOrderMenuAdapter.ViewHolder(listItem);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull currentOrderMenuAdapter.ViewHolder holder, final int position) {

        holder.name.setText(rList.get(position).getName());
        holder.quantity.setText("Quantity: "  + rList.get(position).getQuantity());
        holder.spicy.setText(rList.get(position).getSpicy());
    }

    @Override
    public int getItemCount() {
        return rList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView quantity;
        TextView spicy;
        RelativeLayout menuList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.orderMenuName);
            quantity = itemView.findViewById(R.id.orderMenuQuantity);
            spicy = itemView.findViewById(R.id.orderMenuSpicyLevel  );
            menuList = itemView.findViewById(R.id.orderMenuList);

        }
    }
}


