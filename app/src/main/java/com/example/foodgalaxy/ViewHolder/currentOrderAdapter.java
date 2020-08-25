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
import com.example.foodgalaxy.Model.Orders;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.adminOptions;
import com.example.foodgalaxy.R;
import com.example.foodgalaxy.currentOrderDetail;


import java.util.ArrayList;

public class currentOrderAdapter extends  RecyclerView.Adapter<currentOrderAdapter.ViewHolder>  {
    private ArrayList<Orders> rList;
    private Context mContext;

    public currentOrderAdapter(ArrayList<Orders> rList, Context mContext)
    {
        this.rList = rList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public currentOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.current_orders_layout, parent, false);
        currentOrderAdapter.ViewHolder holder = new currentOrderAdapter.ViewHolder(listItem);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull currentOrderAdapter.ViewHolder holder, final int position) {

        holder.orderNo.setText(Long.toString(rList.get(position).getId()));
        holder.status.setText(convertCodeToStatus(rList.get(position).getStatus()));
        holder.address.setText(rList.get(position).getDeliveryAddress());


        holder.orderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), currentOrderDetail.class);
                intent.putExtra("currentOrder",rList.get(position));
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rList.size();
    }

    private String convertCodeToStatus(String status) {
        if (status.equals("0"))
            return "Placed";
        else if (status.equals("1"))
            return "Shipped";
        else
            return "Ready to pickup";
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView orderNo;
        TextView status;
        TextView address;
        RelativeLayout orderList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNo = itemView.findViewById(R.id.currentOrderNo);
            status = itemView.findViewById(R.id.currentOrderStatus);
            address = itemView.findViewById(R.id.currentOrderAddress);
            orderList = itemView.findViewById(R.id.currentOrdersList);

        }
    }
}


