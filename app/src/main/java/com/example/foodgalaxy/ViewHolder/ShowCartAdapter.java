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

import com.example.foodgalaxy.Model.CartItem;
import com.example.foodgalaxy.Model.Menu;
import com.example.foodgalaxy.R;

import java.util.ArrayList;
import java.util.List;

public class ShowCartAdapter extends  RecyclerView.Adapter<ShowCartAdapter.ViewHolder> {
    private List<CartItem> cartItems;
    private Context mContext;

    public ShowCartAdapter(List<CartItem> cartItems, Context mContext)
    {
        this.cartItems = cartItems;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.show_cart_items, parent, false);
        ShowCartAdapter.ViewHolder holder = new ShowCartAdapter.ViewHolder(listItem);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       // double price = cartItems.get(position).getPrice();

        holder.name.setText(cartItems.get(position).getName());

        holder.price.setText(cartItems.get(position).getPrice() + "$");
        holder.quantityNo.setText(cartItems.get(position).getQuantity());



//        holder.cartItems.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(v.getContext(), MenuDetail.class);
//                intent.putExtra("menuDetail",menus.get(position));
//                v.getContext().startActivity(intent);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView price, quantityNo;
        RelativeLayout cartItems;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cartMenuName);
            price = itemView.findViewById(R.id.cartMenuPrice);
            quantityNo = itemView.findViewById(R.id.cartQuantityNo);
            cartItems = itemView.findViewById(R.id.cartItems);

        }
    }
}



