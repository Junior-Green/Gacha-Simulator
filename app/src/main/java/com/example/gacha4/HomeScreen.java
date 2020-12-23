package com.example.gacha4;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class HomeScreen extends AppCompatActivity implements View.OnClickListener, GestureDetector.OnGestureListener, View.OnTouchListener {
    boolean state;
    GestureDetector detector;
    MediaPlayer background_audio;
    ImageView right_arrow, left_arrow, app_logo;
    ImageButton play_button;
    TextView play;
    ConstraintLayout main;
    int[] audio_id;
    int[] app_icon_id;
    int[] background_icon_id;
    int selection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detector = new GestureDetector(this, this);

        audio_id = new int[]{R.raw.dokkan_theme_audio, R.raw.dbl_theme_audio, R.raw.genshin_impact_audio, R.raw.sds_theme_audio};
        background_icon_id = new int[]{R.drawable.dokkan_home, R.drawable.legends_home, R.drawable.genshin_impact_home, R.drawable.sds_home_};
        app_icon_id = new int[]{R.drawable.dokkan_app_icon, R.drawable.legends_app_icon, R.drawable.genshin_impact_app, R.drawable.seven_deadly_sins_app_icon};

        play = findViewById(R.id.play);
        play_button = findViewById(R.id.play_button);
        right_arrow = findViewById(R.id.rightArrow);
        left_arrow = findViewById(R.id.leftArrow);
        main = findViewById(R.id.background);
        app_logo = findViewById((R.id.app));
        app_logo.setOnClickListener(this);

        play.setOnClickListener(this);
        play_button.setOnClickListener(this);
        main.setOnTouchListener(this);


        background_audio = MediaPlayer.create(HomeScreen.this, audio_id[selection]);
        background_audio.setLooping(true);
        background_audio.start();
    }

    public void selectRight(View v) {
            if(selection == 3)
                selection = 0;
            else
                selection++;
            setMedia();
            app_logo.setImageResource(app_icon_id[selection]);
            main.setBackgroundResource(background_icon_id[selection]);
    }
    public void selectLeft(View v) {
        if(selection == 0)
            selection = 3;
        else
            selection--;
        setMedia();
        app_logo.setImageResource(app_icon_id[selection]);
        main.setBackgroundResource(background_icon_id[selection]);
    }
    public void setMedia() {
        background_audio.pause();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                background_audio.reset();
                background_audio = MediaPlayer.create(HomeScreen.this, audio_id[selection]);
                background_audio.setLooping(true);
                if (state)
                    background_audio.start();
                }
        },500);

    }
    public void onPause() {
        super.onPause();
        if (state)
            background_audio.pause();
    }

    public void onResume() {
        state = true;
        super.onResume();
        if(!background_audio.isPlaying())
            background_audio.start();
    }

    @Override
    public void onClick(View view) {
        if (view == play || view == play_button) {
            Intent i;
            switch (selection) {
                case 0:
                    i = new Intent(HomeScreen.this, Dokkan_Summon.class);
                    background_audio.release();
                    state = false;
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    break;
                case 1:
                    i = new Intent(HomeScreen.this, Dragon_Ball_Legends_Summon.class);
                    background_audio.release();
                    state = false;
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    break;
                case 3:
                    i = new Intent(HomeScreen.this, SDS_Summon.class);
                    background_audio.release();
                    state = false;
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    break;
                default:
            }
        } else if (view == app_logo) {
            String appPackageName;
            switch (selection) {
                case 0:
                    appPackageName = "com.bandainamcogames.dbzdokkanww";
                    break;
                case 1:
                    appPackageName = "com.bandainamcoent.dblegends_ww";
                    break;
                case 2:
                    appPackageName = "com.miHoYo.GenshinImpact";
                    break;
                case 3:
                    appPackageName = "com.netmarble.nanagb";
                    break;
                default:
                    appPackageName = "null";
                    break;
            }
            Intent open = getPackageManager().getLaunchIntentForPackage(appPackageName);
            if (open != null)
                startActivity(open);
            else
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        detector.onTouchEvent(motionEvent);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionDown, MotionEvent motionEnd, float vX, float vY) {
        if (Math.abs(motionDown.getX() - motionEnd.getX()) >= 150 && Math.abs(vX) >= 750) {
            if (motionDown.getX() - motionEnd.getX() < 0) {
                if (selection == 0)
                    selection = 3;
                else
                    selection--;
            } else if (motionDown.getX() - motionEnd.getX() > 0) {

                if (selection == 3)
                    selection = 0;
                else
                    selection++;
            }
            setMedia();
            app_logo.setImageResource(app_icon_id[selection]);
            main.setBackgroundResource(background_icon_id[selection]);
            return true;
        }
        return false;
    }


}