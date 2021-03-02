package com.example.gacha4;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.skydoves.elasticviews.ElasticImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import eightbitlab.com.blurview.BlurView;

public class GI_Summon extends AppCompatActivity implements View.OnClickListener,BudgetDialog.BudgetDialogListener{
    MediaPlayer background_audio;
    ElasticImageView single_summon, multi_summon, mute_button, home_button, right_arrow, left_arrow;
    ImageView bannerImage;
    TextView homeScreenButton, resetButton, summonHistoryButton, afCount;
    ConstraintLayout constraintLayout;
    ConstraintSet constraintSet1 = new ConstraintSet();
    ConstraintSet constraintSet2 = new ConstraintSet();
    Transition transition = new ChangeBounds();
    BlurView blurView;
    Toast afWarning;
    RotateAnimation rotateDown, rotateUp;
    Boolean state = true, homeMenu = false, isAnimationRunning = false;
    private static Boolean budgetEnabled = false;
    View backDrop;
    static Boolean volume_state = true;
    static ArrayList<Card> cardsPulled = new ArrayList<>();
    static HashSet<Card> cardsPulledHash = new HashSet<>();
    static ImageView[] unitsSlots, raritySlots;
    static int bannerChoice = 0, afUsed = 0;
    GIBanner[] banners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        constraintSet1.clone(this, R.layout.activity_gi_summon);
        constraintSet2.clone(this, R.layout.activity_gi_summon_animation);
        setContentView(R.layout.activity_gi_summon);

        //---------------------------------------------------------------------------------------------------------BANNERS---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        GIBanner adrift_in_the_harbour = new GIBanner(R.drawable.gi_adrift_in_the_harbor, GIBanner.findCardsById(new ArrayList<>(Collections.singletonList(11))),
                GIBanner.findCardsById(new ArrayList<>(Arrays.asList(19, 27, 25))), GIBanner.findCardsById(new ArrayList<>(Arrays.asList(14, 17, 20, 8, 12))),
                GIBanner.findCardsById(new ArrayList<>(Arrays.asList(28, 22, 9, 7, 6, 10, 18, 5, 21, 4, 105, 106, 110, 66, 80, 99, 117, 44, 79, 89, 92, 125, 49, 51, 39, 122, 56, 32))),
                GIBanner.findCardsById(new ArrayList<>(Arrays.asList(109, 107, 102, 78, 93, 83, 61, 43, 42, 45, 33, 120, 115, 33))), false);

        banners = new GIBanner[]{adrift_in_the_harbour};
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        background_audio = MediaPlayer.create(GI_Summon.this, R.raw.gi_summon_theme_audio);
        background_audio.setLooping(true);
        background_audio.start();

        bannerChoice = 0;

        rotateDown = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateDown.setDuration(500);
        rotateDown.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                home_button.setRotation(180);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        rotateUp = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateUp.setDuration(500);
        rotateUp.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                home_button.setRotation(0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        afWarning = Toast.makeText(this, "Insufficient Diamonds. Reset or set new budget", Toast.LENGTH_SHORT);

        resetButton = findViewById(R.id.gi_reset);
        resetButton.setOnClickListener(this);

        multi_summon = findViewById(R.id.gi_multi_summon);
        multi_summon.setOnClickListener(this);

        single_summon = findViewById(R.id.gi_single_summon);
        single_summon.setOnClickListener(this);

        backDrop = findViewById(R.id.slider_backdrop_gi);

//        blurView = findViewById(R.id.blurViewGI);
//        View decorView = getWindow().getDecorView();
//        ViewGroup rootView = decorView.findViewById(android.R.id.content);
//        Drawable windowBackground = decorView.getBackground();
//        blurView.setupWith(rootView)
//                .setFrameClearDrawable(windowBackground)
//                .setBlurAlgorithm(new RenderScriptBlur(this))
//                .setBlurRadius(22f)
//                .setBlurEnabled(false)
//                .setHasFixedTransformationMatrix(true);

        bannerImage = findViewById(R.id.gi_banner_image);
        bannerImage.setImageResource(banners[bannerChoice].getImage());

//        summonHistoryButton = findViewById(R.id.gi_summon_history);
//        summonHistoryButton.setOnClickListener(this);

        home_button = findViewById(R.id.gi_openclose);
        home_button.setOnClickListener(this);

        afCount = findViewById(R.id.af_used);
        afCount.setText(Integer.toString(afUsed));
        afCount.setOnClickListener(this);

        right_arrow = findViewById(R.id.gi_right_arrow);
        right_arrow.setOnClickListener(this);

        left_arrow = findViewById(R.id.gi_left_arrow);
        left_arrow.setOnClickListener(this);

        homeScreenButton = findViewById(R.id.gi_home_screen);
        homeScreenButton.setOnClickListener(this);

        resetButton = findViewById(R.id.gi_reset);
        resetButton.setOnClickListener(this);

        summonHistoryButton = findViewById(R.id.gi_summon_history);
        summonHistoryButton.setOnClickListener(this);

        unitsSlots = new ImageView[]{findViewById(R.id.gi_slot1), findViewById(R.id.gi_slot2), findViewById(R.id.gi_slot3), findViewById(R.id.gi_slot4), findViewById(R.id.gi_slot5),
                findViewById(R.id.gi_slot6), findViewById(R.id.gi_slot7), findViewById(R.id.gi_slot8), findViewById(R.id.gi_slot9), findViewById(R.id.gi_slot10)};

        raritySlots = new ImageView[]{findViewById(R.id.gi_rarity1), findViewById(R.id.gi_rarity2), findViewById(R.id.gi_rarity3), findViewById(R.id.gi_rarity4),
                findViewById(R.id.gi_rarity5), findViewById(R.id.gi_rarity6), findViewById(R.id.gi_rarity7), findViewById(R.id.gi_rarity8), findViewById(R.id.gi_rarity9),
                findViewById(R.id.gi_rarity10),};

        mute_button = findViewById(R.id.volume_button);
        mute_button.setOnClickListener(this);

        constraintLayout = findViewById(R.id.gi_summon_root);

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
            bannerImage.setImageResource(banners[bannerChoice].bannerImage);
        } else if (view == left_arrow && !homeMenu) {
            bannerChoice--;
            if (bannerChoice < 0)
                bannerChoice = banners.length - 1;
            bannerImage.setImageResource(banners[bannerChoice].bannerImage);
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
            for (int i = 0; i < 10; i++) {
                cardsPulled.add(results[i]);
                cardsPulledHash.add(results[i]);
                unitsSlots[i].setImageResource(results[i].getCardImage());
                if (banners[bannerChoice].fiveStarBanner.contains(results[i]) || banners[bannerChoice].fiveStarPool.contains(results[i])) {
                    unitsSlots[i].setForeground(getDrawable(R.drawable.red_border));
                    raritySlots[i].setImageResource(GIBanner.FIVE_STAR);
                } else if (banners[bannerChoice].fourStarBanner.contains(results[i]) || banners[bannerChoice].fourStarPool.contains(results[i])) {
                    unitsSlots[i].setForeground(getDrawable(R.drawable.blank));
                    raritySlots[i].setImageResource(GIBanner.FOUR_STAR);
                } else {
                    unitsSlots[i].setForeground(getDrawable(R.drawable.blank));
                    raritySlots[i].setImageResource(GIBanner.THREE_STAR);
                }
            }
        } else if (view == single_summon && !homeMenu) {


        } else if (view == home_button) {
            if (!homeMenu) {
                if (!isAnimationRunning) {
                    isAnimationRunning = true;
                    blurView.setBlurEnabled(true);
                    backDrop.setVisibility(View.VISIBLE);
                    backDrop.setAlpha(0f);
                    backDrop.animate().alpha(0.3f).setDuration(1000);
                    TransitionManager.beginDelayedTransition(constraintLayout, transition);
                    constraintSet2.applyTo(constraintLayout);
                    home_button.startAnimation(rotateDown);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            homeMenu = true;
                            isAnimationRunning = false;
                        }
                    }, 1100);
                }
            } else if (homeMenu) {
                if (!isAnimationRunning) {
                    isAnimationRunning = true;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            homeMenu = false;
                            isAnimationRunning = false;
                        }
                    }, 1100);

                    home_button.startAnimation(rotateUp);
                    blurView.setBlurEnabled(false);
                    backDrop.setAlpha(1f);
                    Animation fadeOut = new AlphaAnimation(0.3f, 0);
                    fadeOut.setDuration(2000);
                    backDrop.startAnimation(fadeOut);
                    TransitionManager.beginDelayedTransition(constraintLayout, transition);
                    constraintSet1.applyTo(constraintLayout);
                }
            }
        } else if (view == homeScreenButton && homeMenu) {
            background_audio.release();
            state = false;
            Intent i = new Intent(GI_Summon.this, HomeScreen.class);
            startActivity(i);
            finish();
        } else if (view == resetButton && homeMenu) {
            budgetEnabled = false;
            cardsPulledHash.clear();
            cardsPulled.clear();
            for (ImageView views : unitsSlots) {
                views.setForeground(getDrawable(R.drawable.blank));
                views.setImageResource(android.R.color.transparent);
            }
            afUsed = 0;
            afCount.setText(Integer.toString(afUsed));
        } else if (view == summonHistoryButton && homeMenu) {
            background_audio.release();
            Intent i = new Intent(GI_Summon.this, GI_Summon_History.class);
            startActivity(i);
            state = false;
            SDS_Summon_History.setLists(cardsPulled, cardsPulledHash);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else if (view == afCount && !homeMenu) {
            openDialog();
        }
    }

    private void openDialog() {
        BudgetDialog budgetDialog = new BudgetDialog();
        budgetDialog.show(getSupportFragmentManager(), "Set Budget Dialog");
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
    public void getBudget(int budget) {
        cardsPulledHash.clear();
        cardsPulled.clear();
        for (ImageView views : unitsSlots) {
            views.setForeground(getDrawable(R.drawable.blank));
            views.setImageResource(android.R.color.transparent);
        }
        afUsed = 0;
        afCount.setText(Integer.toString(afUsed));
        budgetEnabled = true;
        if (budget > 999999) {
            afUsed = 999999;
            afCount.setText(String.valueOf(999999));
        } else {
            afUsed = budget;
            afCount.setText(String.valueOf(budget));
        }
    }
}