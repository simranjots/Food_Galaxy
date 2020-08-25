package com.example.foodgalaxy.Restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodgalaxy.Cart;
import com.example.foodgalaxy.Common.Common;
import com.example.foodgalaxy.LoginSignup.SignInActivity;
import com.example.foodgalaxy.MainActivity;
import com.example.foodgalaxy.Model.FoodStyle;
import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.OrderStatus;
import com.example.foodgalaxy.R;
import com.example.foodgalaxy.ViewHolder.MenuViewHolder;
import com.example.foodgalaxy.ViewHolder.RestaurantAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button signOut;
    private FirebaseAuth auth;
    FirebaseAuth firebaseAuth;

    FirebaseDatabase db;
    DatabaseReference category;

    TextView txtFullName;
    RecyclerView.LayoutManager layoutManager;
    private final int REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationProviderClient;
    LocationCallback locationCallback;
    LocationRequest locationRequest;
    private SupportMapFragment fragment;
    GoogleSignInClient googleSignInClient;


    List<Restaurant> restaurantsList = new ArrayList<Restaurant>();
    int size;
    ArrayList<FoodStyle> listOfStyles = new ArrayList<FoodStyle>();

    FirebaseDatabase database;
    DatabaseReference restaurant;
    FirebaseRecyclerAdapter<Restaurant,MenuViewHolder> adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        if(!checkPermission())
        {
            requestPermission();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Restaurants");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent cartIntent = new Intent(RestaurantsList.this, Cart.class);
            startActivity(cartIntent);
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //set NAme for User
        View headerView= navigationView.getHeaderView(0);
        txtFullName = (TextView)headerView.findViewById(R.id.txtFullName);
        if(Common.GoogleUser==null || Common.currentUser==null){
            txtFullName.setText("Email");
        }else if(Common.GoogleUser != null) {
            txtFullName.setText(Common.GoogleUser.getEmail());
        }else if(Common.currentUser != null){
            txtFullName.setText(Common.currentUser.getName());
        }



        signOut = (Button) findViewById(R.id.sign_out);
        googleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN);
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();



        //Init Firebase
        database= FirebaseDatabase.getInstance();
        restaurant = database.getReference().child("Restaurant");


        restaurant.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Iterable<DataSnapshot> children = snapshot.getChildren();
                    for(DataSnapshot data : children)
                    {
                        Restaurant r = data.getValue(Restaurant.class);
                        restaurantsList.add(r);
                    }

                    restaurantsList = getfilterdata(restaurantsList);
                    recyclerView = findViewById(R.id.recycler_menu);
                    RestaurantAdapter rcdp = new RestaurantAdapter(restaurantsList,RestaurantsList.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(RestaurantsList.this));
                    recyclerView.setAdapter(rcdp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public List<Restaurant> getfilterdata(List<Restaurant> restaurantsList){

        ArrayList<Restaurant> result = new ArrayList<Restaurant>();
        size = Integer.parseInt(getIntent().getStringExtra("size"));
        listOfStyles = getIntent().getParcelableArrayListExtra("style");

        for (Restaurant r : restaurantsList)
        {
            if((size >= r.getcMinsize() && size <= r.getcMaxsize()) && Common.isDeliver == r.isDelivery() ){
                for(FoodStyle f : listOfStyles)
                {
                    if(f.getId() == r.getFs_id())
                    {
                        result.add(r);
                    }
                }
            }
        }

        return result;



    }
    private Boolean checkPermission()
    {
        int permissionState = ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION );
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE );

    }

    //sign out method
    public void signOut() {
        // Firebase sign out
        firebaseAuth.signOut();
        auth.signOut();

        // Google sign out
        googleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Signed Out",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cart) {
            Intent cartIntent = new Intent(RestaurantsList.this,Cart.class);
            startActivity(cartIntent);

        } else if (id == R.id.nav_orders) {
            Intent orderIntent = new Intent(RestaurantsList.this, OrderStatus.class);
            startActivity(orderIntent);

        } else if (id == R.id.log_out) {

            signOut();
            startActivity(new Intent(RestaurantsList.this, SignInActivity.class));
            finish();



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}