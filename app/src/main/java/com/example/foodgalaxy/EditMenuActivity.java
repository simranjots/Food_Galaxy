package com.example.foodgalaxy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.foodgalaxy.Common.Common;
import com.example.foodgalaxy.Model.Menu;
import com.example.foodgalaxy.Model.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditMenuActivity extends AppCompatActivity {


   public EditText menu_name,menu_price,menu_description;
   public  ImageView menu_imageView;
    Button menu_add;
    Menu r;

    FirebaseDatabase database;
    DatabaseReference menu;

    long id ;
    int n_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);



        menu_name = findViewById(R.id.edadmin_menu_name);
        menu_price = findViewById(R.id.edadmin_menu_price);
        menu_description = findViewById(R.id.edadmin_menu_description);
        menu_imageView = findViewById(R.id.edadmin_menu_image);
        menu_add = findViewById(R.id.edadmin_menu_add_button);


        //Init Firebase
        database= FirebaseDatabase.getInstance();
        menu = database.getReference().child("Menu");

        set_Data();

        menu_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Menu menu_data = new Menu(r.getId(),menu_name.getText().toString(),Double.parseDouble(menu_price.getText().toString()),menu_description.getText().toString(), Common.currentRestaurant.getId(),Common.isPredefine,"Demo");
                        menu.child(String.valueOf(n_id)).setValue(menu_data);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


    }

    public void set_Data(){
        menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                r = snapshot.child().getValue(Menu.class);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        menu_name.setText(r.getName());
        menu_price.setText(String.valueOf(r.getPrice()));
        menu_description.setText(r.getDescription());
    }

}