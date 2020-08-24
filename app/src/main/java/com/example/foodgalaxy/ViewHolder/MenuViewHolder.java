package com.example.foodgalaxy.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgalaxy.Interface.ItemClickListener;
import com.example.foodgalaxy.R;


public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtRestaurantName;
    public ImageView imageView;
    public Button buttonDirection;
    

    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView){
        super(itemView);

        txtRestaurantName = (TextView)itemView.findViewById(R.id.menu_name);
        imageView = (ImageView)itemView.findViewById(R.id.menu_image);
        buttonDirection = itemView.findViewById(R.id.menu_direction);

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
