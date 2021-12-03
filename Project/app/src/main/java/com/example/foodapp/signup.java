package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class signup extends AppCompatActivity {

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
    }

    public void back(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void next(View view) {
        Intent intent = new Intent(getApplicationContext(), cook.class);
        startActivity(intent);
        finish();
    }
}