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

import androidx.gridlayout.widget.GridLayout;

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
    ArrayList<String> AllIngredients = new ArrayList<String>();
    ArrayList<String> AllDishes = new ArrayList<String>();
    int total = 0;
    public static int counter = 1;
    AutoCompleteTextView txt1;
    AutoCompleteTextView txt2;
    AutoCompleteTextView txt3;
    AutoCompleteTextView txt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);
        VideoView bg = (VideoView) findViewById(R.id.bg3);
        txt1 = (AutoCompleteTextView) findViewById(R.id.ingredient);
        txt2 = (AutoCompleteTextView) findViewById(R.id.ingredient1);
        txt3 = (AutoCompleteTextView) findViewById(R.id.ingredient2);
        txt4 = (AutoCompleteTextView) findViewById(R.id.ingredient3);
        Log.i("Total", String.valueOf(total));
        AllDishes.clear();
        AllIngredients.clear();
        DownloadTask taskGetAllIngredients = new DownloadTask();
        try {
            taskGetAllIngredients.execute("http://10.0.2.2:8080/foodApp/GetAllIngredients.php").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cook(View view) { //from icon cook
        DownloadTask2 task = new DownloadTask2();
        String url = "http://10.0.2.2:8080/foodApp/GetDishByIng.php?ing=";


        if (txt1.getText().length() > 0) {
            url += txt1.getText() + ",";
        }
        if (txt2.getText().length() > 0) {
            url += txt2.getText() + ",";
        }
        if (txt3.getText().length() > 0) {
            url += txt3.getText() + ",";
        }
        if (txt4.getText().length() > 0) {
            url += txt4.getText() + ",";
        }

        try {
            task.execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void webSearch(View view) { //from nav
        Intent intent = new Intent(getApplicationContext(), webCook.class);
        intent.putExtra("Dish", "");
        startActivity(intent);
        finish();
    }

    public void menu(View view) {
        //nothing
    }

    public void clear(View view) {
        int tagz = Integer.parseInt(String.valueOf(view.getTag()));
        Log.i("TAG", String.valueOf(tagz));
        check(tagz);
    }

    public void check(int tag) {

        if (tag == Integer.parseInt(String.valueOf(txt1.getTag()))) {
            txt1.setText("");
        } else if (tag == Integer.parseInt(String.valueOf(txt2.getTag()))) {
            txt2.setText("");
        } else if (tag == Integer.parseInt(String.valueOf(txt3.getTag()))) {
            txt3.setText("");
        } else if (tag == Integer.parseInt(String.valueOf(txt4.getTag()))) {
            txt4.setText("");
        }


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
                JSONArray arr = new JSONArray(s);
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject ingrd = arr.getJSONObject(i);
                    Log.i("INGREDIENTS: ", ingrd.getString("Ringredient"));
                    AllIngredients.add(ingrd.getString("Ringredient"));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, AllIngredients);

                AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.ingredient);
                acTextView.setThreshold(1);
                acTextView.setAdapter(adapter);
                AutoCompleteTextView acTextView2 = (AutoCompleteTextView) findViewById(R.id.ingredient1);
                acTextView2.setThreshold(1);
                acTextView2.setAdapter(adapter);
                AutoCompleteTextView acTextView3 = (AutoCompleteTextView) findViewById(R.id.ingredient2);
                acTextView3.setThreshold(1);
                acTextView3.setAdapter(adapter);
                AutoCompleteTextView acTextView4 = (AutoCompleteTextView) findViewById(R.id.ingredient3);
                acTextView4.setThreshold(1);
                acTextView4.setAdapter(adapter);

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    public class DownloadTask2 extends AsyncTask<String, Void, String> {

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
                JSONArray arr = new JSONArray(s);
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject dishes = arr.getJSONObject(i);
                    Log.i("Dishes: ", dishes.getString("name"));
                    AllDishes.add(dishes.getString("name"));
                }
                Intent intent = new Intent(getApplicationContext(), dishes.class);
                intent.putStringArrayListExtra("Dishes", AllDishes);
                startActivity(intent);
                finish();
                AllDishes.clear();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}