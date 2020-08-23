package com.example.foodgalaxy.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodgalaxy.Database.Database;
import com.example.foodgalaxy.Model.CartItem;
import com.example.foodgalaxy.Model.Menu;
import com.example.foodgalaxy.R;
import com.example.foodgalaxy.ShowCart;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MenuDetail extends AppCompatActivity {

    ImageView imageView;
    TextView name, price, description;
    Button addToCart;
    Button viewCart;
    Menu m;
    Menu pdm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        imageView = (ImageView) findViewById(R.id.menuImage);
        name = (TextView) findViewById(R.id.m_Name);
        price = (TextView) findViewById(R.id.m_Price);
        description = (TextView) findViewById(R.id.m_description);
        addToCart = (Button) findViewById(R.id.btnAddToCart);
        viewCart = (Button) findViewById(R.id.viewCart);



        m = getIntent().getParcelableExtra("menuDetail");
        pdm = getIntent().getParcelableExtra("preDefinedmenuDetail");

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
        enableViewCart(viewCart);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToCart();
            }
        });
    }
    public void addDataToCart(){

        if(m != null)
        {

            addItemToCart(m);
            recreate();
        }
        if(pdm != null)
        {
            addItemToCart(pdm);
            recreate();
        }
    }

    public void addItemToCart(Menu item){
        Database db = new Database(getBaseContext());
        List<CartItem> listOfitems = db.getCarts();
        int quantity = 1;
        boolean flag = true;

        if(listOfitems.size() > 0) {
            for (CartItem i : listOfitems) {
                if (Integer.parseInt(i.getMenuId()) == item.getId())
                {
                   quantity  = Integer.parseInt(i.getQuantity()) + 1;
                   i.setQuantity(Integer.toString(quantity));
                    Toast.makeText(getBaseContext(), "Quantity Increased", Toast.LENGTH_SHORT).show();
                   flag = false;
                   break;
                }
                else{

                }
            }
            if(flag)
            {
                quantity = 1;
                CartItem cartItem = new CartItem(Integer.toString(item.getId()),item.getName(),Double.toString(item.getPrice()),Integer.toString(quantity));
                listOfitems.add(cartItem);
                Toast.makeText(getBaseContext(), "Cart Updated", Toast.LENGTH_SHORT).show();
            }
            db.cleanCart();
            for(CartItem dataToSave : listOfitems)
            {
                db.addToCart(dataToSave);

            }
        }
        else{
            CartItem cartItem = new CartItem(Integer.toString(item.getId()),item.getName(),Double.toString(item.getPrice()),Integer.toString(quantity));
            db.addToCart(cartItem);
            Toast.makeText(getBaseContext(), "Item Added", Toast.LENGTH_SHORT).show();
        }


    }

    public void enableViewCart(Button viewCart){
        Database db = new Database(getBaseContext());

        if(db.getCarts().size() > 0)
        {
            viewCart.setVisibility(View.VISIBLE);
        }
        else
        {
            viewCart.setVisibility(View.INVISIBLE);
        }

        viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuDetail.this, ShowCart.class);
                startActivity(i);
            }
        });
    }
}