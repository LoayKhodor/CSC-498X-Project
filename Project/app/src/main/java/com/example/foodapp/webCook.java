package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webCook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_cook);
        WebView view = (WebView) findViewById(R.id.web);
        String dish = "";
        view.getSettings().setJavaScriptEnabled(true); //loads js
        view.setWebViewClient(new WebViewClient()); //client inside mobile app
        Intent intent = getIntent();
        if(intent.hasExtra("Dish"))
         dish = intent.getStringExtra("Dish");
        if (dish.equals("")) {
            view.loadUrl("https://www.google.com/");
        } else {
            view.loadUrl("https://www.google.com/search?q=how+to+cook+" + dish);
        }
    }

    public void cook2(View view) { //from nav
        Intent intent = new Intent(getApplicationContext(), dishes.class);
        startActivity(intent);
        finish();
    }

    public void webSearch(View view) { //from nav
        //nothing
    }

    public void menu(View view) {
        Intent intent = new Intent(getApplicationContext(), cook.class);
        startActivity(intent);
        finish();
    }
}