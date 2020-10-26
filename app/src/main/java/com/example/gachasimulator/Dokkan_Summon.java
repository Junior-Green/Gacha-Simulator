package com.example.gachasimulator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class Dokkan_Summon extends AppCompatActivity implements View.OnClickListener, GestureDetector.OnGestureListener, View.OnTouchListener, BudgetDialog.BudgetDialogListener {
    MediaPlayer background_audio2;
    ImageButton mute_button, home_button, multi_summon, single_summon, cancel_button;
    ImageView bannerImage;
    TextView stoneCount, resetButton, summonHistoryButton, stats;
    TextView[] statsSlots;
    ConstraintLayout constraintLayout;
    ConstraintSet constraintSet1 = new ConstraintSet();
    ConstraintSet constraintSet2 = new ConstraintSet();
    Transition transition = new ChangeBounds();
    BlurView blurView;
    Toast stoneWarning;
    Boolean state = true;
    private static Boolean budgetEnabled = false;
    View backDrop;
    static Boolean volume_state = true;
    static ArrayList<Card> cardsPulled = new ArrayList<>();
    static HashSet<Card> cardsPulledHash = new HashSet<>();
    static ImageView[] unitsSlots;
    static int bannerChoice = 0, stonesUsed = 0, ssrsPulled = 0, unitsPulled = 0, featuredPulled = 0, multiSSRs = 0, singleSSRs = 0, multiCount = 0, singleCount = 0;
    DokkanBanner[] banners;
    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        constraintSet1.clone(this, R.layout.activity_dokkan_summon);
        constraintSet2.clone(this, R.layout.activity_dokkan_summon_animation);
        setContentView(R.layout.activity_dokkan_summon);

        DokkanBanner df_8442 = new DokkanBanner(R.drawable.dokkan_festival_8442, DokkanBanner.findCardsById(new ArrayList<>(Arrays.asList(1020440, 1020270, 1019130, 1018750, 1017880, 1015740, 1015150, 1012880, 1012580, 1008410))),
                DokkanBanner.customizePool(new ArrayList<>(Arrays.asList(1020520, 1020270)), null, DokkanBanner.NORMALPOOL), "DOKKAN FESTIVAL (A) 8442");

        DokkanBanner ls_8456 = new DokkanBanner(R.drawable.legendary_summon_8460, DokkanBanner.findCardsById(new ArrayList<>(Arrays.asList(1020200, 1018670, 1018570, 1015090, 1013180, 1013170, 1010150, 1008140, 1002460, 1001970, 1001940, 1001930))),
                DokkanBanner.customizePool(new ArrayList<>(Arrays.asList(1018670, 1018570, 1015090, 1013180, 1013170, 1008140, 1002460, 1001970, 1001940, 1001930)), null, DokkanBanner.NORMALPOOL, new ArrayList<>(Arrays.asList(1020200, 1010150)), null, DokkanBanner.SUMMONABLELRPOOL), "Top Legendary Summon");

        banners = new DokkanBanner[]{df_8442, ls_8456};

        statsSlots = new TextView[]{findViewById(R.id.stat_1A_d), findViewById(R.id.stat_2A_d), findViewById(R.id.stat_3A_d), findViewById(R.id.stat_4A_d),
                findViewById(R.id.stat_5A_d), findViewById(R.id.stat_6A_d), findViewById(R.id.stat_7A_d), findViewById(R.id.stat_8A_d)};

        background_audio2 = MediaPlayer.create(Dokkan_Summon.this, R.raw.dokkan_summon_theme_audio);
        background_audio2.setLooping(true);
        background_audio2.start();

        stoneWarning = new Toast(this);
        stoneWarning.setText("Insufficient Dragonstones. Reset or set new budget");
        stoneWarning.setDuration(Toast.LENGTH_SHORT);

        resetButton = findViewById(R.id.reset);
        resetButton.setOnClickListener(this);

        multi_summon = findViewById(R.id.multi_button);
        multi_summon.setOnClickListener(this);

        single_summon = findViewById(R.id.single_button);
        single_summon.setOnClickListener(this);

        cancel_button = findViewById(R.id.cancel_button_d);
        cancel_button.setOnClickListener(this);

        stats = findViewById(R.id.stats_dokkan);
        stats.setOnClickListener(this);

        backDrop = findViewById(R.id.slider_backdrop_dokkan);

        blurView = findViewById(R.id.blurView1);
        View decorView = getWindow().getDecorView();
        ViewGroup rootView = decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();
        blurView.setupWith(rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(this))
                .setBlurRadius(22f)
                .setBlurEnabled(false)
                .setHasFixedTransformationMatrix(true);

        bannerImage = findViewById(R.id.banner_image);
        bannerImage.setImageResource(banners[bannerChoice].getImage());
        bannerImage.setOnTouchListener(this);

        summonHistoryButton = findViewById(R.id.summon_history_dokkan);
        summonHistoryButton.setOnClickListener(this);

        detector = new GestureDetector(this, this);

        home_button = findViewById(R.id.home_button);
        home_button.setOnClickListener(this);

        stoneCount = findViewById(R.id.stones_used);
        stoneCount.setText(Integer.toString(stonesUsed));
        stoneCount.setOnClickListener(this);

        unitsSlots = new ImageView[]{findViewById(R.id.slot1), findViewById(R.id.slot2), findViewById(R.id.slot3), findViewById(R.id.slot4), findViewById(R.id.slot5),
                findViewById(R.id.slot6), findViewById(R.id.slot7), findViewById(R.id.slot8), findViewById(R.id.slot9), findViewById(R.id.slot10),};
        mute_button = findViewById(R.id.volume_control);
        mute_button.setOnClickListener(this);

        constraintLayout = findViewById(R.id.dokkan_summon_root);

        transition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        transition.setDuration(1000);

        if (!volume_state) {
            background_audio2.setVolume(0f, 0f);
            mute_button.setImageResource(R.drawable.ic_mute_icon);
        }
    }

    public void onPause() {
        if (state)
            background_audio2.pause();
        super.onPause();
    }

    public void onResume() {
        state = true;
        super.onResume();
        if (!background_audio2.isPlaying())
            background_audio2.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View view) {
        if (view == mute_button) {
            if (mute_button.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.no_mute).getConstantState())) {
                mute_button.setImageResource(R.drawable.ic_mute_icon);
                background_audio2.setVolume(0, 0);
                volume_state = false;
            } else {
                mute_button.setImageResource(R.drawable.no_mute);
                background_audio2.setVolume(1.0f, 1.0f);
                volume_state = true;
            }
        } else if (view == home_button) {
            background_audio2.release();
            state = false;
            Intent i = new Intent(Dokkan_Summon.this, HomeScreen.class);
            startActivity(i);
            finish();
        } else if (view == multi_summon) {
            if (!budgetEnabled || stonesUsed >= 50) {
                Card[] results = banners[bannerChoice].multiSummon();
                for (int i = 0; i < 10; i++) {
                    if (results[i] != DokkanBanner.SR && results[i] != DokkanBanner.RARE) {
                        multiSSRs++;
                        ssrsPulled++;
                        if (banners[bannerChoice].featured.contains(results[i]))
                            featuredPulled++;
                    }
                    unitsSlots[i].setImageResource(results[i].getCardImage());
                    if (banners[bannerChoice].featured.contains(results[i]))
                        unitsSlots[i].setForeground(getDrawable(R.drawable.red_border));
                    else if (DokkanBanner.SUMMONABLELRPOOL.contains(results[i]))
                        unitsSlots[i].setForeground(getDrawable(R.drawable.yellow_border));
                    else
                        unitsSlots[i].setForeground(getDrawable(R.drawable.blank));
                }
                cardsPulled.addAll(Arrays.asList(results));
                cardsPulledHash.addAll(Arrays.asList(results));
                multiCount++;
                unitsPulled += 10;
                if (!budgetEnabled)
                    stonesUsed += 50;
                else
                    stonesUsed -= 50;
                stoneCount.setText(Integer.toString(stonesUsed));
            } else {
                stoneWarning.show();
            }
        } else if (view == single_summon) {
            if (!budgetEnabled || stonesUsed >= 5) {
                for (ImageView views : unitsSlots) {
                    views.setImageResource(android.R.color.transparent);
                    views.setForeground(getDrawable(R.drawable.blank));
                }
                Card result = banners[bannerChoice].singleSummon();
                if (result != DokkanBanner.SR && result != DokkanBanner.RARE) {
                    singleSSRs++;
                    ssrsPulled++;
                    if (banners[bannerChoice].featured.contains(result))
                        featuredPulled++;
                }
                if (banners[bannerChoice].featured.contains(result))
                    unitsSlots[0].setForeground(getDrawable(R.drawable.red_border));
                else if (DokkanBanner.SUMMONABLELRPOOL.contains(result))
                    unitsSlots[0].setForeground(getDrawable(R.drawable.yellow_border));
                else
                    unitsSlots[0].setForeground(getDrawable(R.drawable.blank));

                unitsSlots[0].setImageResource(result.getCardImage());
                cardsPulled.add(result);
                cardsPulledHash.add(result);
                singleCount++;
                unitsPulled++;
                if (!budgetEnabled)
                    stonesUsed += 5;
                else
                    stonesUsed -= 5;
                stoneCount.setText(Integer.toString(stonesUsed));
            } else {
                stoneWarning.show();
            }
        } else if (view == resetButton) {
            if (budgetEnabled)
                budgetEnabled = false;
            stonesUsed = 0;
            ssrsPulled = 0;
            unitsPulled = 0;
            featuredPulled = 0;
            multiSSRs = 0;
            singleSSRs = 0;
            multiCount = 0;
            singleCount = 0;
            cardsPulledHash.clear();
            cardsPulled.clear();
            for (ImageView views : unitsSlots) {
                views.setForeground(getDrawable(R.drawable.blank));
                views.setImageResource(android.R.color.transparent);
            }

            stoneCount.setText(Integer.toString(stonesUsed));
        } else if (view == summonHistoryButton) {
            background_audio2.release();
            Intent i = new Intent(Dokkan_Summon.this, Dokkan_Summon_History.class);
            startActivity(i);
            state = false;
            Dokkan_Summon_History.setLists(cardsPulled, cardsPulledHash);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else if (view == stats) {
            statsSlots[0].setText(String.valueOf(ssrsPulled));
            if (ssrsPulled != 0)
                statsSlots[1].setText(String.valueOf(Math.round((float) stonesUsed / ssrsPulled * 100) / 100f));
            else
                statsSlots[1].setText("N/A");

            if (unitsPulled != 0) {
                statsSlots[2].setText(("%" + String.format(Locale.getDefault(), "%.1f", (float) ssrsPulled / unitsPulled * 100)));
                statsSlots[3].setText(("%" + String.format(Locale.getDefault(), "%.1f", (float) featuredPulled / unitsPulled * 100)));
            } else {
                statsSlots[2].setText("%0");
                statsSlots[3].setText("%0");
            }
            statsSlots[4].setText(String.valueOf(unitsPulled));
            statsSlots[5].setText(String.valueOf(featuredPulled));
            if (multiCount != 0)
                statsSlots[6].setText((String.format(Locale.getDefault(), "%.1f", (float) multiSSRs / multiCount)));
            else
                statsSlots[6].setText("0");
            if (singleCount != 0)
                statsSlots[7].setText((String.format(Locale.getDefault(), "%.1f", (float) singleSSRs / singleCount)));
            else
                statsSlots[7].setText("0");

            multi_summon.setEnabled(false);
            single_summon.setEnabled(false);
            resetButton.setEnabled(false);
            home_button.setEnabled(false);
            mute_button.setEnabled(false);
            cancel_button.setEnabled(true);
            view.setEnabled(false);
            blurView.setBlurEnabled(true);
            backDrop.setVisibility(View.VISIBLE);
            backDrop.setAlpha(0f);
            backDrop.animate().alpha(0.3f).setDuration(1000);
            TransitionManager.beginDelayedTransition(constraintLayout, transition);
            constraintSet2.applyTo(constraintLayout);
        } else if (view == cancel_button) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    multi_summon.setEnabled(true);
                    single_summon.setEnabled(true);
                    resetButton.setEnabled(true);
                    home_button.setEnabled(true);
                    mute_button.setEnabled(true);
                    stats.setEnabled(true);
                }
            }, 1000);
            view.setEnabled(false);
            backDrop.setAlpha(1f);
            Animation fadeOut = new AlphaAnimation(0.3f, 0);
            fadeOut.setDuration(2000);
            backDrop.startAnimation(fadeOut);
            TransitionManager.beginDelayedTransition(constraintLayout, transition);
            constraintSet1.applyTo(constraintLayout);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    blurView.setBlurEnabled(false);
                }
            }, 500);
        } else if (view == stoneCount) {
            openDialog();
        }
    }

    private void openDialog() {
        BudgetDialog budgetDialog = new BudgetDialog();
        budgetDialog.show(getSupportFragmentManager(), "Set Budget Dialog");
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
    public void onBackPressed() {
        background_audio2.release();
        state = false;
        Intent i = new Intent(Dokkan_Summon.this, HomeScreen.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onFling(MotionEvent motionDown, MotionEvent motionEnd, float vX, float v1) {
        if (Math.abs(motionDown.getX() - motionEnd.getX()) >= 100 && Math.abs(vX) >= 750) {
            if (motionDown.getX() - motionEnd.getX() < 0) {
                bannerChoice++;
                if (bannerChoice > banners.length - 1)
                    bannerChoice = 0;

                bannerImage.setImageResource(banners[bannerChoice].getImage());
            } else if (motionDown.getX() - motionEnd.getX() > 0) {
                bannerChoice--;
                if (bannerChoice < 0)
                    bannerChoice = banners.length - 1;

                bannerImage.setImageResource(banners[bannerChoice].getImage());
            }
        }
        return true;
    }

    @Override
    public void getBudget(int budget) {
        resetButton.performClick();
        budgetEnabled = true;
        if (budget > 9999) {
            stonesUsed = 9999;
            stoneCount.setText(String.valueOf(9999));
        } else {
            stonesUsed = budget;
            stoneCount.setText(String.valueOf(budget));
        }

    }
}
