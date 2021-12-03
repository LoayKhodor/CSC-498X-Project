package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class cook extends AppCompatActivity {
    ArrayList<String> ingredients = new ArrayList<String>();
    int total = 0;
    String r1 = "";
    String r2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ingredients.add("Loay Khodor");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);
        VideoView bg = (VideoView) findViewById(R.id.bg3);

        DownloadTask2 taskCountAllIng = new DownloadTask2();
        try {
          r1 =taskCountAllIng.execute("http://localhost:8080/foodApp/CountIng.php").get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("Total", String.valueOf(total));
        DownloadTask taskGetAllIngredients = new DownloadTask();
        try {
            r2 = taskGetAllIngredients.execute("http://localhost:8080/foodApp/GetAllIngredients.php").get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ingredients);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.ingredient);
        acTextView.setThreshold(1);
        acTextView.setAdapter(adapter);


    }

    public void cook(View view) { //from icon cook
        Intent intent = new Intent(getApplicationContext(), dishes.class);
        startActivity(intent);
        finish();
    }

    public void cook2(View view) { //from nav
        Intent intent = new Intent(getApplicationContext(), dishes.class);
        startActivity(intent);
        finish();
    }

    public void webSearch(View view) { //from nav
        Intent intent = new Intent(getApplicationContext(), webCook.class);
        startActivity(intent);
        finish();
    }

    public void menu(View view) {
        //nothing
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String res = "";
            URL url;
            HttpURLConnection urlConnection;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    res += current;
                    data = reader.read();
                    Log.i("Task1", res);
                }

                return res;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject json = new JSONObject(s);
                String ings = json.getString("Ringredient");
//                JSONArray allIngs = new JSONArray(ings);
                Log.i("INGREDIENTS", ings);
                ingredients.add(ings);
//                for (int i = 0; i < allIngs.length(); i++) {
//                    JSONObject ingr = allIngs.getJSONObject(i);
//                }
                Log.i( "Arraylist:" ,  ingredients.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
    }

    public class DownloadTask2 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject json = new JSONObject(s);
                total = Integer.parseInt(json.getString("COUNT(*)"));
                Log.i("TOTAL COUNT", String.valueOf(total));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}