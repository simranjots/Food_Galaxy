package com.example.foodgalaxy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgalaxy.Common.Common;
import com.example.foodgalaxy.Model.Menu;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.ViewHolder.adminMenuAdapter;
import com.example.foodgalaxy.ViewHolder.adminPanelAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class adminMenu extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference menu;
    RecyclerView recyclerView;
    Button addMenu;

    ArrayList<Menu> menuList = new ArrayList<Menu>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        addMenu = (Button) findViewById(R.id.addMenuBtn);

        //Init Firebase
        database= FirebaseDatabase.getInstance();
        menu = database.getReference().child("Menu");

        menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Iterable<DataSnapshot> children = snapshot.getChildren();
                    for(DataSnapshot data : children)
                    {
                        Menu m = data.getValue(Menu.class);
                        if(m.getR_Id() == Common.currentRestaurant.getId() && m.isPredefinedMenu() == Common.isPredefine) {
                            menuList.add(m);
                        }
                    }
                    recyclerView = findViewById(R.id.adminMenusRecycler);
                    adminMenuAdapter rcdp = new adminMenuAdapter(menuList, adminMenu.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(adminMenu.this));
                    recyclerView.setAdapter(rcdp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminMenu.this,AddMenuActivity.class));
            }
        });

    }
}
