package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class dishes extends AppCompatActivity {
    ArrayList<String> allDishes = new ArrayList<String>();
    TextView dish1;
    TextView dish2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);
        dish1 = (TextView) findViewById(R.id.dish1);
        dish2 = (TextView) findViewById(R.id.dish2);
        Intent intent = getIntent();
        if(intent.hasExtra("Dishes")) {
            allDishes = intent.getStringArrayListExtra("Dishes");
            Log.i("ArrayList", allDishes.toString());


            if (!allDishes.isEmpty())
                dish1.setText(allDishes.get(0));
            if (allDishes.size() > 1)
                dish2.setText(allDishes.get(1));
        }
    }

    public void searchDish(View view) { //url should be altered here
        int currentTag = Integer.parseInt(String.valueOf(view.getTag()));
        Log.i("Tag", String.valueOf(currentTag));
        Intent intent = new Intent(getApplicationContext(), webCook.class);
        if (currentTag == 0) {
            intent.putExtra("Dish", dish1.getText());
        } else if (currentTag == 1) {
            intent.putExtra("Dish", dish2.getText());
        }
        startActivity(intent);
        finish();
    }

    public void searchDish2(View view) {
        Intent intent = new Intent(getApplicationContext(), webCook.class);
        startActivity(intent);
        finish();
    }

    public void cook(View view) {
        Intent intent = new Intent(getApplicationContext(), cook.class);
        startActivity(intent);
        finish();
    }
}