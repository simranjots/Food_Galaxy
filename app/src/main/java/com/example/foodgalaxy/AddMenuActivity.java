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

public class AddMenuActivity extends AppCompatActivity {

    EditText menu_name,menu_price,menu_description;
    ImageView menu_imageView;
    Button menu_add;

    FirebaseDatabase database;
    DatabaseReference menu;

    long id ;
    int n_id;

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
                            id = (snapshot.getChildrenCount());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                n_id = (int) id + 1;
                Menu menu_data = new Menu(n_id,menu_name.getText().toString(),Double.parseDouble(menu_price.getText().toString()),menu_description.getText().toString(), Common.currentRestaurant.getId(),Common.isPredefine,"Demo");
                menu.child(String.valueOf(n_id)).setValue(menu_data);
                Toast.makeText(AddMenuActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddMenuActivity.this,adminOptions.class));
                finish();
            }
        });

    }
}