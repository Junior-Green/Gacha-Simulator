package com.example.gacha4;

import android.content.Intent;
import android.content.res.Configuration;
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
    private String toastMsg;
    private AnimationDrawable ani;
    private Toast shoutOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        switch(screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                toastMsg = "Follow Discord and Twitter for updates!\nTwitter: @JCGreen\nDiscord: Project Gacha 4";
                break;
            default:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        System.exit(0);
                    }
                }, 250);
                toastMsg = "This device is not supported";
                break;
        }

        shoutOut = Toast.makeText(this, toastMsg, Toast.LENGTH_LONG);
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