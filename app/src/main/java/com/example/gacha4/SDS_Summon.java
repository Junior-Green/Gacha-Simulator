package com.example.gacha4;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.skydoves.elasticviews.ElasticImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class SDS_Summon extends AppCompatActivity implements View.OnClickListener,BudgetDialog.BudgetDialogListener{
    MediaPlayer background_audio;
    ElasticImageView single_summon, multi_summon, mute_button, home_button, right_arrow, left_arrow;
    ImageView bannerImage;
    TextView homeScreenButton, resetButton, summonHistoryButton, diamondCount;
    ConstraintLayout constraintLayout;
    ConstraintSet constraintSet1 = new ConstraintSet();
    ConstraintSet constraintSet2 = new ConstraintSet();
    Transition transition = new ChangeBounds();
    BlurView blurView;
    Toast diamondWarning;
    Boolean state = true, homeMenu = false;
    private static final Boolean budgetEnabled = false;
    View backDrop;
    static Boolean volume_state = true;
    static ArrayList<Card> cardsPulled = new ArrayList<>();
    static HashSet<Card> cardsPulledHash = new HashSet<>();
    static ImageView[] unitsSlots;
    static int bannerChoice = 0, diamondsUsed = 0;
    SDSBanner[] banners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        constraintSet1.clone(this, R.layout.activity_sds_summon);
        constraintSet2.clone(this, R.layout.activity_sds_summon_animation);
        setContentView(R.layout.activity_sds_summon);

        //---------------------------------------------------------------------------------------------------------BANNERS---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        SDSBanner pickup_0039 = new SDSBanner(R.drawable.sds_banner_bg_pickup_0039, SDSBanner.findCardsByID(new ArrayList<Integer>(Arrays.asList(12, 71, 6, 68, 81, 80, 73, 70, 55, 58, 23, 18, 33, 30, 53, 4, 1, 59))), SDSBanner.findCardsByID(new ArrayList<Integer>(Arrays.asList(12, 71))));

        banners = new SDSBanner[]{pickup_0039};
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        background_audio = MediaPlayer.create(SDS_Summon.this, R.raw.sds_summon_theme_audio);
        background_audio.setLooping(true);
        background_audio.start();

        bannerChoice = 0;

        diamondWarning = Toast.makeText(this, "Insufficient Diamonds. Reset or set new budget", Toast.LENGTH_SHORT);

        resetButton = findViewById(R.id.sds_reset);
        resetButton.setOnClickListener(this);

        multi_summon = findViewById(R.id.sds_multi_summon);
        multi_summon.setOnClickListener(this);

        single_summon = findViewById(R.id.sds_single_summon);
        single_summon.setOnClickListener(this);

        backDrop = findViewById(R.id.slider_backdrop_sds);

        blurView = findViewById(R.id.blurViewSDS);
        View decorView = getWindow().getDecorView();
        ViewGroup rootView = decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();
        blurView.setupWith(rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(this))
                .setBlurRadius(22f)
                .setBlurEnabled(false)
                .setHasFixedTransformationMatrix(true);

        bannerImage = findViewById(R.id.sds_banner_image);
        bannerImage.setImageResource(banners[bannerChoice].getImage());

        summonHistoryButton = findViewById(R.id.sds_summon_history);
        summonHistoryButton.setOnClickListener(this);

        home_button = findViewById(R.id.sds_openclose);
        home_button.setOnClickListener(this);

        diamondCount = findViewById(R.id.diamonds_used);
        diamondCount.setText(Integer.toString(diamondsUsed));
        diamondCount.setOnClickListener(this);

        right_arrow = findViewById(R.id.sds_right_arrow);
        right_arrow.setOnClickListener(this);

        left_arrow = findViewById(R.id.sds_left_arrow);
        left_arrow.setOnClickListener(this);

        homeScreenButton = findViewById(R.id.sds_home_screen);
        homeScreenButton.setOnClickListener(this);

        resetButton = findViewById(R.id.sds_reset);
        resetButton.setOnClickListener(this);

        summonHistoryButton = findViewById(R.id.sds_summon_history);
        summonHistoryButton.setOnClickListener(this);

        unitsSlots = new ImageView[]{findViewById(R.id.sds_slot1), findViewById(R.id.sds_slot2), findViewById(R.id.sds_slot3), findViewById(R.id.sds_slot4), findViewById(R.id.sds_slot5),
                findViewById(R.id.sds_slot6), findViewById(R.id.sds_slot7), findViewById(R.id.sds_slot8), findViewById(R.id.sds_slot9), findViewById(R.id.sds_slot10), findViewById(R.id.sds_slot11)};

        mute_button = findViewById(R.id.volume_button);
        mute_button.setOnClickListener(this);

        constraintLayout = findViewById(R.id.sds_summon_root);

        transition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        transition.setDuration(1000);

        if (!volume_state) {
            background_audio.setVolume(0f, 0f);
            mute_button.setImageResource(R.drawable.ic_mute_icon);
        }

    }
    @Override
    public void onClick(View view) {
        if (view == right_arrow && !homeMenu) {
            bannerChoice++;
            if (bannerChoice >= banners.length)
                bannerChoice = 0;
            bannerImage.setImageResource(banners[bannerChoice].image);
        } else if (view == left_arrow && !homeMenu) {
            bannerChoice--;
            if (bannerChoice < 0)
                bannerChoice = banners.length - 1;
            bannerImage.setImageResource(banners[bannerChoice].image);
        } else if (view == mute_button && !homeMenu) {
            if (mute_button.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.no_mute).getConstantState())) {
                mute_button.setImageResource(R.drawable.ic_mute_icon);
                background_audio.setVolume(0, 0);
                volume_state = false;
            } else {
                mute_button.setImageResource(R.drawable.no_mute);
                background_audio.setVolume(1.0f, 1.0f);
                volume_state = true;
            }
        } else if (view == multi_summon && !homeMenu) {
            Card[] results = banners[bannerChoice].multiSummon();
            for (int i = 0; i < 11; i++) {
                cardsPulled.add(results[i]);
                cardsPulledHash.add(results[i]);
                unitsSlots[i].setImageResource(results[i].getCardImage());
                if (banners[bannerChoice].rateUp.contains(results[i]))
                    unitsSlots[i].setForeground(getDrawable(R.drawable.red_border));
                else
                    unitsSlots[i].setForeground(getDrawable(R.drawable.blank));
            }
            cardsPulled.addAll(Arrays.asList(results));
            cardsPulledHash.addAll(Arrays.asList(results));
            diamondsUsed += 30;
            diamondCount.setText(String.valueOf(diamondsUsed));

        } else if (view == single_summon && !homeMenu) {
            Card result = banners[bannerChoice].singleSummon();
            cardsPulled.add(result);
            cardsPulledHash.add(result);
            for (ImageView views : unitsSlots) {
                views.setImageResource(android.R.color.transparent);
                views.setForeground(getDrawable(R.drawable.blank));
            }
            unitsSlots[0].setImageResource(result.image);
            if (banners[bannerChoice].rateUp.contains(result))
                unitsSlots[0].setForeground(getDrawable((R.drawable.red_border)));
            diamondsUsed += 3;
            diamondCount.setText(String.valueOf(diamondsUsed));
        } else if (view == home_button) {
            if (!homeMenu) {
                blurView.setBlurEnabled(true);
                backDrop.setVisibility(View.VISIBLE);
                backDrop.setAlpha(0f);
                backDrop.animate().alpha(0.3f).setDuration(1000);
                TransitionManager.beginDelayedTransition(constraintLayout, transition);
                constraintSet2.applyTo(constraintLayout);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        homeMenu = true;
                    }
                }, 1000);
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        homeMenu = false;
                    }
                }, 1000);
                blurView.setBlurEnabled(false);
                backDrop.setAlpha(1f);
                Animation fadeOut = new AlphaAnimation(0.3f, 0);
                fadeOut.setDuration(2000);
                backDrop.startAnimation(fadeOut);
                TransitionManager.beginDelayedTransition(constraintLayout, transition);
                constraintSet1.applyTo(constraintLayout);
            }
        } else if (view == homeScreenButton) {
            background_audio.release();
            state = false;
            Intent i = new Intent(SDS_Summon.this, HomeScreen.class);
            startActivity(i);
            finish();
        } else if (view == resetButton) {
            cardsPulledHash.clear();
            cardsPulled.clear();
            for (ImageView views : unitsSlots) {
                views.setForeground(getDrawable(R.drawable.blank));
                views.setImageResource(android.R.color.transparent);
            }
            diamondsUsed = 0;
            diamondCount.setText(Integer.toString(diamondsUsed));
        } else if (view == summonHistoryButton) {
            background_audio.release();
            Intent i = new Intent(SDS_Summon.this, SDS_Summon_History.class);
            startActivity(i);
            state = false;
            SDS_Summon_History.setLists(cardsPulled, cardsPulledHash);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    public void onPause() {
        if (state)
            background_audio.pause();
        super.onPause();
    }

    public void onResume() {
        state = true;
        super.onResume();
        if (!background_audio.isPlaying())
            background_audio.start();
    }
    @Override
    public void getBudget(int budget) {

    }
}