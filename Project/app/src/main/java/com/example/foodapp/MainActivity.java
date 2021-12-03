package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private TextView signup;

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

        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        TextView signup = (TextView) findViewById(R.id.signup);
        signup.setPaintFlags(signup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public void signup(View view) {
        Intent intent = new Intent(getApplicationContext(), signup.class);
        startActivity(intent);
        finish();
    }

    public void cook(View view) {
        Intent intent = new Intent(getApplicationContext(), cook.class);
        startActivity(intent);
        finish();
    }
}