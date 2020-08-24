//package com.example.foodgalaxy.menu;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.foodgalaxy.Cart;
//import com.example.foodgalaxy.Database.Database;
//import com.example.foodgalaxy.Model.CartItem;
//import com.example.foodgalaxy.Model.Menu;
//import com.example.foodgalaxy.Model.User;
//import com.example.foodgalaxy.R;
//import com.example.foodgalaxy.ShowCart;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.squareup.picasso.Picasso;
//import java.util.List;
//
//public class MenuDetail extends AppCompatActivity {
//
//
//    ImageView imageView;
//    TextView name, price, description;
//    Button addToCart;
//    Button viewCart;
//    String m;
//    String pdm;
//    FirebaseDatabase database;
//    DatabaseReference menu;
//    String pid,mid;
//    Menu menuList;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu_detail);
//
//        imageView = (ImageView) findViewById(R.id.menuImage);
//        name = (TextView) findViewById(R.id.m_Name);
//        price = (TextView) findViewById(R.id.m_Price);
//        description = (TextView) findViewById(R.id.m_description);
//        addToCart = (Button) findViewById(R.id.btnAddToCart);
//        viewCart = (Button) findViewById(R.id.viewCart);
//
//
//
//        //Init Firebase
//        database= FirebaseDatabase.getInstance();
//        menu = database.getReference("Menu");
//
//        m = getIntent().getStringExtra("menuDetail");
//        pdm = getIntent().getStringExtra("preDefinedmenuDetail");
//
//        if(m != null)
//        {
//            menu.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                     menuList = snapshot.child(m).getValue(Menu.class);
//                    Picasso.with(MenuDetail.this).load(menuList.getImageLink()).placeholder(R.drawable.powered_by_google_dark).into(imageView);
//                    name.setText(menuList.getName());
//                    price.setText(menuList.getPrice() + "$");
//                    description.setText(menuList.getDescription());
//                    mid = menuList.getId();
//                    //Toast.makeText(MenuDetail.this, id, Toast.LENGTH_SHORT).show();
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//
//
//        }
//        if(pdm != null)
//        {
//            menu.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                     menuList = snapshot.child(m).getValue(Menu.class);
//                    Picasso.with(MenuDetail.this).load(menuList.getImageLink()).placeholder(R.drawable.powered_by_google_dark).into(imageView);
//                    name.setText(menuList.getName());
//                    price.setText(menuList.getPrice() + "$");
//                    description.setText(menuList.getDescription());
//                    pid = menuList.getId();
//                    Toast.makeText(MenuDetail.this, pid, Toast.LENGTH_SHORT).show();
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//
//        }
//        enableViewCart(viewCart);
//
//        addToCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              //  Toast.makeText(MenuDetail.this, id, Toast.LENGTH_SHORT).show();
//                addDataToCart();
//            }
//        });
//    }
//
//    public void addDataToCart(){
//
//        if(m != null)
//        {
//            addItemToCart(Integer.parseInt(mid));
//            recreate();
//        }
//        if(pdm != null)
//        {
//            addItemToCart(Integer.parseInt(pid));
//            recreate();
//        }
//    }
//
//    public void addItemToCart(int item){
//        Database db = new Database(getBaseContext());
//        List<CartItem> listOfitems = db.getCarts();
//        int quantity = 1;
//        boolean flag = true;
//
//        if(listOfitems.size() > 0) {
//            for (CartItem i : listOfitems) {
//                if (i.getMenuId() == Integer.toString(item))
//                {
//                   quantity  = Integer.parseInt(i.getQuantity()) + 1;
//                   i.setQuantity(Integer.toString(quantity));
//                    Toast.makeText(getBaseContext(), "Quantity Increased", Toast.LENGTH_SHORT).show();
//                   flag = false;
//                   break;
//                }
//                else{
//
//                }
//            }
//            if(flag)
//            {
//
//                quantity = 1;
//                CartItem cartItem = new CartItem(menuList.getId(),menuList.getName(),Double.toString(menuList.getPrice()),Integer.toString(quantity)," too","");
//                listOfitems.add(cartItem);
//                Toast.makeText(getBaseContext(), "Cart Updated", Toast.LENGTH_SHORT).show();
//            }
//            db.cleanCart();
//            for(CartItem dataToSave : listOfitems)
//            {
//                db.addToCart(dataToSave);
//
//            }
//        }
//        else{
//            CartItem cartItem = new CartItem(menuList.getId(),menuList.getName(),Double.toString(menuList.getPrice()),Integer.toString(quantity),"too","");
//            db.addToCart(cartItem);
//            Toast.makeText(getBaseContext(), "Item Added", Toast.LENGTH_SHORT).show();
//        }
//
//
//    }
//
//    public void enableViewCart(Button viewCart){
//        Database db = new Database(getBaseContext());
//
//        if(db.getCarts().size() > 0)
//        {
//            viewCart.setVisibility(View.VISIBLE);
//        }
//        else
//        {
//            viewCart.setVisibility(View.INVISIBLE);
//        }
//
//        viewCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MenuDetail.this, Cart.class);
//                startActivity(i);
//            }
//        });
//    }
//}