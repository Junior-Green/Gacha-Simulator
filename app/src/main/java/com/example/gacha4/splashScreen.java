package com.example.gacha4;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class splashScreen extends AppCompatActivity {
    private ImageView container;
    private AnimationDrawable ani;
    private Toast shoutOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        shoutOut = Toast.makeText(this, "Follow Discord and Twitter for updates!\nTwitter: @JCGreen\nDiscord: ", Toast.LENGTH_LONG);
        shoutOut.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splashScreen.this, HomeScreen.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}