package com.example.foodgalaxy;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodgalaxy.LoginSignup.SignInActivity;

public class SplashScreen extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(SplashScreen.this, SignInActivity.class));

    }
}
