package com.example.foodgalaxy.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgalaxy.Interface.ItemClickListener;
import com.example.foodgalaxy.Model.Menu;
import com.example.foodgalaxy.R;
import com.example.foodgalaxy.RestaurantDetailPage;
import com.example.foodgalaxy.menu.MenuDetail;

import java.util.ArrayList;

public class MenuAdapter extends  RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView name;
    public TextView price;

    private ItemClickListener itemClickListener;

    public MenuAdapter(View itemView){
        super(itemView);

        name = itemView.findViewById(R.id.Menu_Name);
        price = itemView.findViewById(R.id.Menu_Price);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),false);

    }

    public void setItemClickListener(ItemClickListener categoryId) {
        this.itemClickListener = categoryId;
    }


}
