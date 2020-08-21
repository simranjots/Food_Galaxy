package com.example.foodgalaxy.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgalaxy.Model.Menu;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.R;
import com.example.foodgalaxy.RestaurantDetailPage;

import java.util.ArrayList;

public class MenuAdapter extends  RecyclerView.Adapter<MenuAdapter.ViewHolder>  {
    private ArrayList<Menu> menus;
    private Context mContext;

    public MenuAdapter(ArrayList<Menu> menus, Context mContext)
    {
        this.menus = menus;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.menus, parent, false);
        MenuAdapter.ViewHolder holder = new MenuAdapter.ViewHolder(listItem);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, final int position) {

        holder.name.setText(menus.get(position).getName());
        holder.price.setText((int) menus.get(position).getPrice());



        holder.menuList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), RestaurantDetailPage.class);
                intent.putExtra("menuDetail",menus.get(position));
                v.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView price;
        RelativeLayout menuList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Menu_Name);
            price = itemView.findViewById(R.id.Menu_Price);
            menuList = itemView.findViewById(R.id.menuList);

        }
    }
}
