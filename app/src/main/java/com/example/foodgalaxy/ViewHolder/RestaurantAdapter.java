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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgalaxy.Model.Orders;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.OrderStatus;
import com.example.foodgalaxy.R;
import com.example.foodgalaxy.Restaurant.RestaurantDetailPage;
import com.example.foodgalaxy.Restaurant.RestaurantsList;
import com.example.foodgalaxy.menu.FoodDetail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends  RecyclerView.Adapter<RestaurantAdapter.ViewHolder>  {

    private List<Restaurant> restaurants;
    private Context mContext;
    int count = 0;

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


        FirebaseDatabase database;
        DatabaseReference orders;

        database= FirebaseDatabase.getInstance();
        orders = database.getReference().child("Orders");
        count = 0;

        holder.name.setText(restaurants.get(position).getName());
        Picasso.with(this.mContext).load(restaurants.get(position).getImageLink()).into(holder.image);
        holder.address.setText(restaurants.get(position).getAddress());


        orders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                count = 0;
                if(snapshot.exists()){
                    Iterable<DataSnapshot> children = snapshot.getChildren();
                    for(DataSnapshot data : children)
                    {
                        Orders o = data.getValue(Orders.class);
                        if(o.getR_Id() == restaurants.get(position).getId())
                        {
                            count++;
                        }

                    }
                    holder.orderCount.setText("Orders done " + count);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
        TextView address;
        TextView orderCount;
        LinearLayout restaurantList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.menu_name);
            image = itemView.findViewById(R.id.menu_image);
            address = itemView.findViewById(R.id.menu_address);
            orderCount = itemView.findViewById(R.id.ordersDone);
            restaurantList = itemView.findViewById(R.id.click);

        }
    }
}
