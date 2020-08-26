package com.example.foodgalaxy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodgalaxy.Common.Common;
import com.example.foodgalaxy.Model.Menu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddMenuActivity extends AppCompatActivity {

    EditText menu_name,menu_price,menu_description;
    ImageView menu_imageView;
    Button menu_add;
    ArrayList<Menu> menuList = new ArrayList<Menu>();

    FirebaseDatabase database;
    DatabaseReference menu;

    int id = 0;
    int n_id = 0;
    boolean flag= true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        menu_name = findViewById(R.id.admin_menu_name);
        menu_price = findViewById(R.id.admin_menu_price);
        menu_description = findViewById(R.id.admin_menu_description);
        menu_imageView = findViewById(R.id.admin_menu_image);
        menu_add = findViewById(R.id.admin_menu_add_button);


        //Init Firebase
        database= FirebaseDatabase.getInstance();
        menu = database.getReference().child("Menu");



        menu_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                menu.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            Iterable<DataSnapshot> children = snapshot.getChildren();
                            for(DataSnapshot data : children)
                            {
                                Menu m = data.getValue(Menu.class);
                                menuList.add(m);
                            }

                            if(menuList.size() == 0)
                            {
                                id = 1;
                            }
                            else
                            {
                                id = menuList.size() + 1;
                            }
                            if(flag)
                            {
                                addData(id);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }

    public void addData(int size)
    {
        Menu menu_data = new Menu( size ,menu_name.getText().toString(),Double.parseDouble(menu_price.getText().toString()),menu_description.getText().toString(), Common.currentRestaurant.getId(),Common.isPredefine,"Demo");
        menu.child(Integer.toString(size )).setValue(menu_data);
        Toast.makeText(AddMenuActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AddMenuActivity.this,adminOptions.class));
        finish();
        flag = false;

    }

}