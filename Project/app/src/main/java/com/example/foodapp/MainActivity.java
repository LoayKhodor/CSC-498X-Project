package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private TextView signup;
    EditText username;
    EditText password;
    String validator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView bg = (VideoView) findViewById(R.id.bg);
        bg.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.bg);

        bg.start();
        bg.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        TextView signup = (TextView) findViewById(R.id.signup);
        signup.setPaintFlags(signup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }

    public void signup(View view) {
        Intent intent = new Intent(getApplicationContext(), signup.class);
        startActivity(intent);
        finish();
    }

    public void cook(View view) { //login to cook page
        // http://10.0.2.2:8080/foodApp/signin.php?login=charbeldaoud,charbel123

        DownloadJSON task = new DownloadJSON();
        try {
            String url = "http://10.0.2.2:8080/foodApp/signin.php?login=" + username.getText() + "," + password.getText();
            task.execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public class DownloadJSON extends AsyncTask<String, Void, String> {

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

        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray arr = new JSONArray(s);
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject username = arr.getJSONObject(i);
                    Log.i("USERNAME: ", username.getString("username"));
                    validator = username.getString("username");
                }
                if (!validator.equals("")) {
                    Intent intent = new Intent(getApplicationContext(), cook.class);
                    startActivity(intent);
                    finish();
                } else
                {
                    Toast t = Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_LONG);
                    t.show();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

}