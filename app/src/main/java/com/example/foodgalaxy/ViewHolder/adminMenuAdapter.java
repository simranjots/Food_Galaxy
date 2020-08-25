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

public class adminMenuAdapter extends  RecyclerView.Adapter<adminMenuAdapter.ViewHolder>  {
    private ArrayList<Menu> rList;
    private Context mContext;

    public adminMenuAdapter(ArrayList<Menu> rList, Context mContext)
    {
        this.rList = rList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public adminMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.admin_menu_layout, parent, false);
        adminMenuAdapter.ViewHolder holder = new adminMenuAdapter.ViewHolder(listItem);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull adminMenuAdapter.ViewHolder holder, final int position) {

        holder.name.setText(rList.get(position).getName());
        holder.description.setText(rList.get(position).getDescription());
        holder.price.setText("$ " + Double.toString(rList.get(position).getPrice()));


        holder.menuList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), adminOptions.class);
                intent.putExtra("adminMenuDetail",rList.get(position));
                //Common.currentRestaurant = rList.get(position);
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
        TextView description;
        TextView price;
        RelativeLayout menuList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.adminMName);
            description = itemView.findViewById(R.id.adminMDescription);
            price = itemView.findViewById(R.id.adminMPrice);
            menuList = itemView.findViewById(R.id.adminMenuList);

        }
    }
}


