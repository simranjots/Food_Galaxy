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

        import com.example.foodgalaxy.menu.FoodDetail;
        import com.example.foodgalaxy.Model.Menu;
        import com.example.foodgalaxy.R;


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

        double price = menus.get(position).getPrice();
        holder.name.setText(menus.get(position).getName());
        holder.price.setText(Double.toString(price) + "$");



        holder.menuList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), FoodDetail.class);
                intent.putExtra("menuDetail",menus.get(position).getId());
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

