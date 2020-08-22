package com.example.foodgalaxy.menu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.foodgalaxy.Model.Menu;
import com.example.foodgalaxy.R;
import com.squareup.picasso.Picasso;

public class MenuDetail extends AppCompatActivity {

    ImageView imageView;
    TextView name, price, description;
    Button addToCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        imageView = (ImageView) findViewById(R.id.menuImage);
        name = (TextView) findViewById(R.id.m_Name);
        price = (TextView) findViewById(R.id.m_Price);
        description = (TextView) findViewById(R.id.m_description);
        addToCart = (Button) findViewById(R.id.btnAddToCart);



        Menu m = getIntent().getParcelableExtra("menuDetail");
        Menu pdm = getIntent().getParcelableExtra("preDefinedmenuDetail");

        if(m != null)
        {

            Picasso.with(MenuDetail.this).load(m.getImageLink()).placeholder(R.drawable.powered_by_google_dark).into(imageView);

            name.setText(m.getName());
            price.setText(Double.toString(m.getPrice()) + "$");
            description.setText(m.getDescription());
        }
        if(pdm != null)
        {
            Picasso.with(MenuDetail.this).load(m.getImageLink()).placeholder(R.drawable.powered_by_google_dark).into(imageView);
            name.setText(m.getName());
            price.setText(Double.toString(m.getPrice()) + "$");
            description.setText(m.getDescription());
        }

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToCart();
            }
        });
    }
    public void addDataToCart(){


    }
}