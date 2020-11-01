package com.example.gacha4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.skydoves.elasticviews.ElasticImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Dragon_Ball_Legends_Summon extends AppCompatActivity implements View.OnClickListener, GestureDetector.OnGestureListener, View.OnTouchListener, BudgetDialog.BudgetDialogListener {
    MediaPlayer background_audio3;
    ElasticImageView single_summon, multi_summon, mute_button, home_button;
    ImageView bannerImage;
    TextView stoneCount, resetButton, summonHistoryButton, stats;
    ConstraintLayout constraintLayout;
    ConstraintSet constraintSet1 = new ConstraintSet();
    ConstraintSet constraintSet2 = new ConstraintSet();
    Transition transition = new ChangeBounds();
    Toast stoneWarning;
    Boolean state = true;
    private static Boolean budgetEnabled = false;
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
        /*constraintSet1.clone(this, R.layout.activity_dokkan_summon);
        constraintSet2.clone(this, R.layout.activity_dokkan_summon_animation);*/
        setContentView(R.layout.activity_dragon__ball__legends__summon);

        DokkanBanner df_8442 = new DokkanBanner(R.drawable.dokkan_festival_8442, DokkanBanner.findCardsById(new ArrayList<>(Arrays.asList(1020440, 1020270, 1019130, 1018750, 1017880, 1015740, 1015150, 1012880, 1012580, 1008410))),
                DokkanBanner.customizePool(new ArrayList<>(Arrays.asList(1020520, 1020270)), null, DokkanBanner.NORMALPOOL), "DOKKAN FESTIVAL (A) 8442");

        DokkanBanner ls_8456 = new DokkanBanner(R.drawable.legendary_summon_8460, DokkanBanner.findCardsById(new ArrayList<>(Arrays.asList(1020200, 1018670, 1018570, 1015090, 1013180, 1013170, 1010150, 1008140, 1002460, 1001970, 1001940, 1001930))),
                DokkanBanner.customizePool(new ArrayList<>(Arrays.asList(1018670, 1018570, 1015090, 1013180, 1013170, 1008140, 1002460, 1001970, 1001940, 1001930)), null, DokkanBanner.NORMALPOOL, new ArrayList<>(Arrays.asList(1020200, 1010150)), null, DokkanBanner.SUMMONABLELRPOOL), "Top Legendary Summon");

        banners = new DokkanBanner[]{df_8442, ls_8456};

        background_audio3 = MediaPlayer.create(Dragon_Ball_Legends_Summon.this, R.raw.dbl_summon_theme_audio);
        background_audio3.setLooping(true);
        background_audio3.start();

        stoneWarning = Toast.makeText(this, "Insufficient Dragonstones. Reset or set new budget", Toast.LENGTH_SHORT);

        resetButton = findViewById(R.id.reset);
        resetButton.setOnClickListener(this);

        multi_summon = findViewById(R.id.multi_button);
        multi_summon.setOnClickListener(this);

        single_summon = findViewById(R.id.single_button);
        single_summon.setOnClickListener(this);

        bannerImage = findViewById(R.id.banner_image);
        bannerImage.setImageResource(banners[bannerChoice].getImage());
        bannerImage.setOnTouchListener(this);

        summonHistoryButton = findViewById(R.id.summon_history_dbl);
        summonHistoryButton.setOnClickListener(this);

        detector = new GestureDetector(this, this);

        home_button = findViewById(R.id.home_button);
        home_button.setOnClickListener(this);

        stoneCount = findViewById(R.id.crystals_used);
        stoneCount.setText(Integer.toString(stonesUsed));
        stoneCount.setOnClickListener(this);

        unitsSlots = new ImageView[]{findViewById(R.id.slot1), findViewById(R.id.slot2), findViewById(R.id.slot3), findViewById(R.id.slot4), findViewById(R.id.slot5),
                findViewById(R.id.slot6), findViewById(R.id.slot7), findViewById(R.id.slot8), findViewById(R.id.slot9), findViewById(R.id.slot10),};
        mute_button = findViewById(R.id.volume_control);
        mute_button.setOnClickListener(this);

        constraintLayout = findViewById(R.id.dbl_summon_root);

        transition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        transition.setDuration(1000);

        if (!volume_state) {
            background_audio3.setVolume(0f, 0f);
            mute_button.setImageResource(R.drawable.ic_mute_icon);
        }
    }

    public void onPause() {
        if (state)
            background_audio3.pause();
        super.onPause();
    }

    public void onResume() {
        state = true;
        super.onResume();
        if (!background_audio3.isPlaying())
            background_audio3.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View view) {
        if (view == mute_button) {
            if (mute_button.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.no_mute).getConstantState())) {
                mute_button.setImageResource(R.drawable.ic_mute_icon);
                background_audio3.setVolume(0, 0);
                volume_state = false;
            } else {
                mute_button.setImageResource(R.drawable.no_mute);
                background_audio3.setVolume(1.0f, 1.0f);
                volume_state = true;
            }
        } else if (view == home_button) {
            background_audio3.release();
            state = false;
            Intent i = new Intent(Dragon_Ball_Legends_Summon.this, HomeScreen.class);
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
            background_audio3.release();
            Intent i = new Intent(Dragon_Ball_Legends_Summon.this, Dokkan_Summon_History.class);
            startActivity(i);
            state = false;
            Dokkan_Summon_History.setLists(cardsPulled, cardsPulledHash);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
        background_audio3.release();
        state = false;
        Intent i = new Intent(Dragon_Ball_Legends_Summon.this, HomeScreen.class);
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
