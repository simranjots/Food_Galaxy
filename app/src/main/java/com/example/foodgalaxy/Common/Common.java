package com.example.foodgalaxy.Common;

import com.example.foodgalaxy.Model.Restaurant;
import com.example.foodgalaxy.Model.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class Common {

    public static User currentUser;
    public static GoogleSignInAccount  GoogleUser;
    public static boolean isDeliver = false;
    public static String dateOfBooking;
    public static Restaurant currentRestaurant;
    public static boolean isCurrentOrder = false;

}
