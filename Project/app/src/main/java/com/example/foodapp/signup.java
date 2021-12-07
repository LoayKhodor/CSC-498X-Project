package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class signup extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        VideoView bg = (VideoView) findViewById(R.id.bg2);
        bg.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.bg);

        bg.start();
        bg.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
        //Intent intent = getIntent();
        username = (EditText) findViewById(R.id.usrname);
        password = (EditText) findViewById(R.id.passwrd);
        email = (EditText) findViewById(R.id.email);
    }

    public void back(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void next(View view) {
        // http://10.0.2.2:8080/foodApp/signup.php?login=charbeldaoud,charbel.daoud@lau.edu.lb,charbel123
        Toast t;
        if (username.getText().length() == 0 || password.getText().length() == 0 || email.getText().length() == 0) {
            t = Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_LONG);
            t.show();
        } else {
            String url = "http://10.0.2.2:8080/foodApp/signup.php?login=" + username.getText() + "," + email.getText() + "," + password.getText();
            Register task = new Register();
            try {
                task.execute(url).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public class Register extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection;
            String result = "";
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {

                    char c = (char) data;
                    result += c;
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}