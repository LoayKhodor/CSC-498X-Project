package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class dishes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);
    }

    public void searchDish(View view) { //url should be altered here
        Intent intent = new Intent(getApplicationContext(), webCook.class);
        startActivity(intent);
        finish();
    }
    public void searchDish2(View view) {
        Intent intent = new Intent(getApplicationContext(), webCook.class);
        startActivity(intent);
        finish();
    }
    public void cook(View view){
        Intent intent = new Intent(getApplicationContext(), cook.class);
        startActivity(intent);
        finish();
    }
}