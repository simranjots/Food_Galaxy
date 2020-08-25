package com.example.foodgalaxy.menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.foodgalaxy.Cart;
import com.example.foodgalaxy.Database.Database;
import com.example.foodgalaxy.Model.CartItem;
import com.example.foodgalaxy.Model.Menu;
import com.example.foodgalaxy.Model.Rating;
import com.example.foodgalaxy.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class FoodDetail extends AppCompatActivity implements RatingDialogListener {

    TextView food_name, food_price, food_description;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart,btnRating;
    ElegantNumberButton numberButton;
    //RatingBar ratingBar;

    String m;
    String pdm;
    String foodId = "";

    FirebaseDatabase database;
    DatabaseReference menu;
    String pid,mid;
    DatabaseReference ratingTbl;

    Menu currentFood;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);


        //Firebse
        database = FirebaseDatabase.getInstance();
        menu = database.getReference("Menu");
        ratingTbl = database.getReference("Rating");


        //INit view

        numberButton = (ElegantNumberButton) findViewById(R.id.number_button);
        btnCart = (FloatingActionButton) findViewById(R.id.btnCart);
        btnRating = findViewById(R.id.btn_rating);
        //ratingBar = findViewById(R.id.ratingBar);

        m = getIntent().getStringExtra("menuDetail");
        pdm = getIntent().getStringExtra("preDefinedmenuDetail");


        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRatingDialog();
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Toast.makeText(FoodDetail.this, foodId, Toast.LENGTH_SHORT).show();
                // new view inflate for selecting nearby places option

                AlertDialog.Builder builder = new AlertDialog.Builder( FoodDetail.this );
                // builder.setTitle( "Edit Employee" );
                LayoutInflater inflater = LayoutInflater.from( FoodDetail.this );
                View v = inflater.inflate( R.layout.custom_food_layout,null );
                builder.setView( v );
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                final Spinner spinnerSpiceLevel = v.findViewById( R.id.spinnerSpicyLevel );
                final EditText comment = v.findViewById(R.id.edtcommentfood);
                Button buttonEdit = v.findViewById( R.id.button_addcart );

                buttonEdit.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new Database(getBaseContext()).addToCart(new CartItem(
                                foodId,
                                currentFood.getName(),
                                String.valueOf(currentFood.getPrice()),
                                numberButton.getNumber(),
                                spinnerSpiceLevel.getSelectedItem().toString(),
                                comment.getText().toString()

                        ));

                       // Toast.makeText(FoodDetail.this, "Added to Cart", Toast.LENGTH_SHORT).show();

                        alertDialog.dismiss();

                        Snackbar snackbar = Snackbar.make(view,"Added To Cart", Snackbar.LENGTH_SHORT).setAction("Go to Cart", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent cartIntent = new Intent(FoodDetail.this, Cart.class);
                                startActivity(cartIntent);
                            }
                        });
                        snackbar.show();



                    }
                } );
            }
        });

        food_description = (TextView) findViewById(R.id.food_description);
        food_name = (TextView) findViewById(R.id.food_name);
        food_price = (TextView) findViewById(R.id.food_price);
        food_image = (ImageView) findViewById(R.id.img_food);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        getDetailFood(m,pdm);

    }

    private void getRating(String foodId)
    {
        com.google.firebase.database.Query foodRating = ratingTbl.orderByChild("foodId").equalTo(foodId);

        foodRating.addValueEventListener(new ValueEventListener() {
            int count=0,sum=0;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    Rating item = postSnapshot.getValue(Rating.class);
                    sum+=Integer.parseInt(item.getRateValue());
                    count++;
                    //Toast.makeText(getApplicationContext(),"COunt "+count+" Sum "+sum, Toast.LENGTH_LONG).show();
                }
                if(count!=0) {
                    float average = sum/count;
                    //ratingBar.setRating(average);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getDetailFood(String m,String pdm) {
        if(m != null)
        {
            menu.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    currentFood= snapshot.child(m).getValue(Menu.class);

                    Picasso.with(FoodDetail.this).load(currentFood.getImageLink()).placeholder(R.drawable.powered_by_google_dark).into(food_image);
                    collapsingToolbarLayout.setTitle(currentFood.getName());
                    food_price.setText("$"+currentFood.getPrice());
                    food_name.setText(currentFood.getName());
                    food_description.setText(currentFood.getDescription());
                    foodId = currentFood.getId();
                    mid = foodId;

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
        if(pdm != null)
        {
            menu.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    currentFood= snapshot.child(pdm).getValue(Menu.class);

                    Picasso.with(FoodDetail.this).load(currentFood.getImageLink()).placeholder(R.drawable.powered_by_google_dark).into(food_image);
                    collapsingToolbarLayout.setTitle(currentFood.getName());
                    food_price.setText("$"+currentFood.getPrice());
                    food_name.setText(currentFood.getName());
                    food_description.setText(currentFood.getDescription());
                    foodId = currentFood.getId();
                    pid = foodId;


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }

    private void showRatingDialog()
    {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("submit")
                .setNegativeButtonText("Cancel")
                .setNoteDescriptions(Arrays.asList("Very Bad","Not Good","Quite Good","Very Good","Excellent"))
                .setDefaultRating(1)
                .setTitle("Rate this Food")
                .setDescription("Plase provide a rating and feedback")
                .setTitleTextColor(R.color.colorPrimary)
                .setDescriptionTextColor(R.color.colorPrimary)
                .setHint("Please write your comment here")
                .setHintTextColor(R.color.colorAccent)
                .setCommentTextColor(android.R.color.white)
                .setCommentBackgroundColor(R.color.btn_login)
                .setWindowAnimation(R.style.RatingDialogFadeAnim)
                .create(FoodDetail.this)
                .show();

    }

    @Override
    public void onNegativeButtonClicked() {

    }

    public String getAlphaNumericString(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    @Override
    public void onPositiveButtonClicked(int i, @NotNull String s)

    {
   Rating rating;
//        String tempName="abc";
//        if(Common.googleUser!=null) {
////           rating = new Rating(Common.googleUser.getEmail(), foodId, String.valueOf(i), s);
//            tempName = Common.googleUser.getToken();
//        }
//        else
//        {
//            //rating = new Rating(Common.currentUser.getPhone(), foodId, String.valueOf(i), s);
//            if(Common.currentUser!=null)
//            tempName = Common.currentUser.getPhone();
//        }
        String s1 = getAlphaNumericString(5);
        rating = new Rating(s1, foodId, String.valueOf(i), s);


        ratingTbl.child(s1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if(dataSnapshot.child(s1).exists())
                {
                    ratingTbl.child(s1).removeValue();
                    ratingTbl.child(s1).setValue(rating);
                }
                else
                {
                    ratingTbl.child(s1).setValue(rating);
                }
                //Toast.makeText(getApplicationContext(),"Thank you for Feed back",Toast.LENGTH_LONG).show();
                new AlertDialog.Builder(FoodDetail.this)
                        .setTitle("Feedback")
                        .setMessage("Thankyou for your valuable Feedback")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
