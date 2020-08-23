package com.example.foodgalaxy.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;


import com.example.foodgalaxy.Model.CartItem;
import com.example.foodgalaxy.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;


public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME="SerweDB.db";
    private static final int DB_VER=1;
    public Database(Context context){
        super(context,DB_NAME,null,DB_VER);
    }

    public List<CartItem> getCarts()
    {
        SQLiteDatabase db = getReadableDatabase();

        createTable(db);
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"MenuId","name","price","quantity"};
        String sqlTable="CartItems";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<CartItem> result = new ArrayList<>();
        if(c.moveToFirst())
        {
            do{
                result.add(new CartItem(c.getString(c.getColumnIndex("MenuId")),
                        c.getString(c.getColumnIndex("name")),
                        c.getString(c.getColumnIndex("price")),
                        c.getString(c.getColumnIndex("quantity"))
                ));
            }while (c.moveToNext());
        }
        return result;
    }

    public void addToCart(CartItem cartItem)
    {
        SQLiteDatabase db = getReadableDatabase();
        createTable(db);
        String query = String.format("INSERT INTO CartItems(MenuId,name,price,quantity) VALUES('%s','%s','%s','%s');",
                cartItem.getMenuId(),
                cartItem.getName(),
                cartItem.getPrice(),
                cartItem.getQuantity());
        db.execSQL(query);

    }

    public void cleanCart()
    {
        SQLiteDatabase db = getReadableDatabase();
        createTable(db);
        String query = String.format("DELETE FROM CartItems");
        db.execSQL(query);

    }

public void createTable(SQLiteDatabase db){
    db.execSQL("create table if not exists " + "CartItems" + " (" +
            //Column name     Type of variable
            "MenuId" + " Text , " +
            "name" + " TEXT , " +
            "price" + " TEXT , " +
            "quantity" + " TEXT );"
    );
}

}
