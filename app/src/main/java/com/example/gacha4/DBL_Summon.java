package com.example.gacha4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class DBL_Summon extends AppCompatActivity implements View.OnClickListener, GestureDetector.OnGestureListener, View.OnTouchListener, BudgetDialog.BudgetDialogListener {
    MediaPlayer background_audio3;
    ElasticImageView single_summon, multi_summon, mute_button, home_button;
    ImageView bannerImage, zenkaiImage;
    TextView crystalCount, resetButton, summonHistoryButton, bannerSwitch, multiCost, stepNumber, zenkaiTotal;
    ConstraintLayout constraintLayout;
    Boolean isNormal = true;
    static HashMap<Card, Integer> zenkaiPoints = new HashMap<>();
    ConstraintSet constraintSet1 = new ConstraintSet();
    ConstraintSet constraintSet2 = new ConstraintSet();
    Transition transition = new ChangeBounds();
    Toast crystalWarning;
    Boolean state = true;
    private static Boolean budgetEnabled = false;
    static Boolean volume_state = true;
    static ArrayList<Card> cardsPulled = new ArrayList<>();
    static HashSet<Card> cardsPulledHash = new HashSet<>();
    static ImageView[] unitSlots;
    static TextView[] zPowerSlots;
    static int bannerChoice, crystalsUsed = 0;
    GestureDetector detector;

    //---------------------------------------------------------------------------------------------------------BANNERS---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    static DBLBanner legends_justice_against_evil = new DBLBanner(R.drawable.legends_justice_against_evil, DBLBanner.findCardsByID(new ArrayList<Integer>(Arrays.asList(2408, 1806, 1501, 1703, 1701, 202)), DBLBanner.SPARKINGS),
            DBLBanner.findCardsByID(new ArrayList<Integer>(Arrays.asList(2002, 2001, 2209, 1903, 1803, 1601, 1507, 1302, 1301, 909, 1106, 907, 905, 906, 813, 707, 1502, 814, 613, 802, 801, 135, 508, 306, 803)), DBLBanner.SPARKINGS),
            DBLBanner.findCardsByID(new ArrayList<Integer>(Arrays.asList(2706, 2705)), DBLBanner.SPARKINGS), DBLBanner.findCardsByID(new ArrayList<Integer>(Arrays.asList(2205, 1805, 1206)), DBLBanner.SPARKINGS), true, false);

    static DBLBanner legends_the_6th_universe = new DBLBanner(R.drawable.legends_the_6th_universe, DBLBanner.findCardsByID(new ArrayList<Integer>(Arrays.asList(2415, 2413, 2006, 2005, 613, 135)), DBLBanner.SPARKINGS),
            DBLBanner.findCardsByID(new ArrayList<Integer>(Arrays.asList(2305, 2002, 2001, 2209, 1903, 1803, 1806, 1703, 1601, 1701, 1508, 1507, 1302, 1301, 909, 903, 813, 709, 707, 704, 904, 814, 215, 901, 116)), DBLBanner.SPARKINGS),
            DBLBanner.findCardsByID(new ArrayList<Integer>(Arrays.asList(2805, 2806)), DBLBanner.SPARKINGS), DBLBanner.findCardsByID(new ArrayList<Integer>(Arrays.asList(2401, 1505, 711)), DBLBanner.SPARKINGS), true, false);

    static DBLBanner legends_all_star_vol2 = new DBLBanner(R.drawable.legends_all_star_vol2, DBLBanner.findCardsByID(new ArrayList<Integer>(Arrays.asList(2505, 2504, 2802, 2801, 2803, 2305, 15081502, 508, 117)), DBLBanner.SPARKINGS), null, null, null, false, false);

    static DBLBanner zenkai_awakening_goku_black = new DBLBanner(R.drawable.zenkai_awakening_goku_black, DBLBanner.findCardByID(613, DBLBanner.ZENKAIS));
    static DBLBanner zenkai_awakening_super_saiyan_3_goku = new DBLBanner(R.drawable.zenkai_awakening_super_saiyan_3_goku, DBLBanner.findCardByID(611, DBLBanner.ZENKAIS));

    static DBLBanner[] normalBanners = new DBLBanner[]{legends_justice_against_evil, legends_the_6th_universe, legends_all_star_vol2};
    static DBLBanner[] zenkaiBanners = new DBLBanner[]{zenkai_awakening_goku_black, zenkai_awakening_super_saiyan_3_goku};

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        constraintSet1.clone(this, R.layout.activity_dragon__ball__legends__summon);
        constraintSet2.clone(this, R.layout.activity_dragon_ball_legends_summon_multi_only);
        setContentView(R.layout.activity_dragon_ball_legends_summon_multi_only);

        background_audio3 = MediaPlayer.create(DBL_Summon.this, R.raw.dbl_summon_theme_audio);
        background_audio3.setLooping(true);
        background_audio3.start();

        bannerChoice = 0;

        crystalWarning = Toast.makeText(this, "Insufficient Chrono Crystals. Reset or set new budget", Toast.LENGTH_SHORT);

        resetButton = findViewById(R.id.reset);
        resetButton.setOnClickListener(this);

        multi_summon = findViewById(R.id.multi_button);
        multi_summon.setOnClickListener(this);

        single_summon = findViewById(R.id.single_button);
        single_summon.setOnClickListener(this);

        bannerImage = findViewById(R.id.banner_image);
        bannerImage.setImageResource(normalBanners[bannerChoice].getImage());
        bannerImage.setOnTouchListener(this);

        bannerSwitch = findViewById(R.id.bannerSwitch_dbl);
        bannerSwitch.setOnClickListener(this);

        stepNumber = findViewById(R.id.stepNumber);
        stepNumber.setText(String.valueOf(normalBanners[bannerChoice].getStep()));

        summonHistoryButton = findViewById(R.id.summon_history_dbl);
        summonHistoryButton.setOnClickListener(this);

        zenkaiImage = findViewById(R.id.zenkai_image);
        zenkaiTotal = findViewById(R.id.total_zenkai);

        detector = new GestureDetector(this, this);

        for (DBLBanner banners : zenkaiBanners) {
            if (!zenkaiPoints.containsKey(banners.getZenkaiUnit()))
                zenkaiPoints.put(banners.getZenkaiUnit(), 0);
        }
        home_button = findViewById(R.id.home_button);
        home_button.setOnClickListener(this);

        multiCost = findViewById(R.id.multi_prompt_dbl_);
        multiCost.setText(new DecimalFormat(",###").format(normalBanners[bannerChoice].getMultiCost()));

        transition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        transition.setDuration(1000);

        crystalCount = findViewById(R.id.crystals_used);
        crystalCount.setText(Integer.toString(crystalsUsed));
        crystalCount.setOnClickListener(this);

        multiCost.setText(new DecimalFormat(",###").format(normalBanners[bannerChoice].getMultiCost()));

        unitSlots = new ImageView[]{findViewById(R.id.slot1), findViewById(R.id.slot2), findViewById(R.id.slot3), findViewById(R.id.slot4), findViewById(R.id.slot5),
                findViewById(R.id.slot6), findViewById(R.id.slot7), findViewById(R.id.slot8), findViewById(R.id.slot9), findViewById(R.id.slot10),};

        zPowerSlots = new TextView[]{findViewById(R.id.slot1A), findViewById(R.id.slot2A), findViewById(R.id.slot3A), findViewById(R.id.slot4A), findViewById(R.id.slot5A),
                findViewById(R.id.slot6A), findViewById(R.id.slot7A), findViewById(R.id.slot8A), findViewById(R.id.slot9A), findViewById(R.id.slot10A)};

        for (int i = 0; i < 10; i++) {
            unitSlots[i].setImageResource(android.R.color.transparent);
            unitSlots[i].setForeground(getDrawable(R.drawable.blank));
            zPowerSlots[i].setText("");
        }
        mute_button = findViewById(R.id.volume_control);
        mute_button.setOnClickListener(this);

        constraintLayout = findViewById(R.id.dbl_summon_root);
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
            Intent i = new Intent(DBL_Summon.this, HomeScreen.class);
            startActivity(i);
            finish();
        } else if (view == multi_summon) {
            for (int i = 0; i < 10; i++) {
                unitSlots[i].setImageResource(android.R.color.transparent);
                unitSlots[i].setForeground(getDrawable(R.drawable.blank));
                zPowerSlots[i].setText("");
            }
            if (isNormal) {
                if (!budgetEnabled || crystalsUsed >= normalBanners[bannerChoice].getMultiCost()) {
                    if (budgetEnabled)
                        crystalsUsed -= normalBanners[bannerChoice].getMultiCost();
                    else
                        crystalsUsed += normalBanners[bannerChoice].getMultiCost();
                    ArrayList<Card> results = new ArrayList<>();
                    if (normalBanners[bannerChoice].isStepUp) {
                        results = normalBanners[bannerChoice].stepUpSummon();
                        multiCost.setText(new DecimalFormat(",###").format(normalBanners[bannerChoice].getMultiCost()));
                        stepNumber.setText(String.valueOf(normalBanners[bannerChoice].getStep()));
                        for (int i = 0; i < results.size(); i++) {
                            unitSlots[i].setImageResource(results.get(i).getCardImage());
                            if (normalBanners[bannerChoice].getBannerCards().contains(results.get(i)) || normalBanners[bannerChoice].getFeaturedCards().contains(results.get(i))) {
                                unitSlots[i].setForeground(getDrawable(R.drawable.red_border));
                                zPowerSlots[i].setText("x600");
                            } else if (normalBanners[bannerChoice].getLegendsLimitedCards().contains(results.get(i))) {
                                unitSlots[i].setForeground(getDrawable(R.drawable.yellow_border));
                                zPowerSlots[i].setText("x600");
                            } else if (results.get(i) != DBLBanner.HE && results.get(i) != DBLBanner.EX) {
                                zPowerSlots[i].setText("x600");
                            }
                        }
                    } else if (normalBanners[bannerChoice].isGuranteed) {
                        Collections.addAll(results, normalBanners[bannerChoice].guaranteedSummon());
                        for (int i = 0; i < results.size(); i++) {
                            unitSlots[i].setImageResource(results.get(i).getCardImage());
                            if (normalBanners[bannerChoice].getBannerCards().contains(results.get(i)) || normalBanners[bannerChoice].getFeaturedCards().contains(results.get(i))) {
                                unitSlots[i].setForeground(getDrawable(R.drawable.red_border));
                                zPowerSlots[i].setText("x600");
                            } else if (normalBanners[bannerChoice].getLegendsLimitedCards().contains(results.get(i))) {
                                unitSlots[i].setForeground(getDrawable(R.drawable.yellow_border));
                                zPowerSlots[i].setText("x600");
                            } else if (results.get(i) != DBLBanner.HE && results.get(i) != DBLBanner.EX) {
                                zPowerSlots[i].setText("x600");
                            }
                        }
                    } else {
                        Collections.addAll(results, normalBanners[bannerChoice].normalSummon());
                        for (int i = 0; i < results.size(); i++) {
                            unitSlots[i].setImageResource(results.get(i).getCardImage());
                            if (normalBanners[bannerChoice].getBannerCards().contains(results.get(i))) {
                                unitSlots[i].setForeground(getDrawable(R.drawable.red_border));
                                zPowerSlots[i].setText("x600");
                            } else if (normalBanners[bannerChoice].getLegendsLimitedCards() != null && normalBanners[bannerChoice].getLegendsLimitedCards().contains(results.get(i))) {
                                unitSlots[i].setForeground(getDrawable(R.drawable.yellow_border));
                                zPowerSlots[i].setText("x600");
                            } else if (results.get(i) != DBLBanner.HE && results.get(i) != DBLBanner.EX) {
                                zPowerSlots[i].setText("x600");
                            }
                        }
                    }
                    cardsPulled.addAll(results);
                    cardsPulledHash.addAll(results);
                } else
                    crystalWarning.show();
            } else {
                if (!budgetEnabled || crystalsUsed >= zenkaiBanners[bannerChoice].getMultiCost()) {
                    int[] zenkaiScores = zenkaiBanners[bannerChoice].zenkaiMulti();
                    for (int i = 0; i < zenkaiScores.length; i++) {
                        if (zenkaiPoints.containsKey(zenkaiBanners[bannerChoice].getZenkaiUnit())) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                zenkaiPoints.replace(zenkaiBanners[bannerChoice].getZenkaiUnit(), zenkaiPoints.get(zenkaiBanners[bannerChoice].getZenkaiUnit()) + zenkaiScores[i]);
                            }
                        } else
                            zenkaiPoints.put(zenkaiBanners[bannerChoice].getZenkaiUnit(), zenkaiScores[i]);
                        unitSlots[i].setImageResource(zenkaiBanners[bannerChoice].getZenkaiUnit().getCardImage());
                        zPowerSlots[i].setText(String.valueOf(zenkaiScores[i]));
                        if (i == 9) {
                            if (zenkaiPoints.containsKey(zenkaiBanners[bannerChoice].getZenkaiUnit())) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    zenkaiPoints.replace(zenkaiBanners[bannerChoice].getZenkaiUnit(), zenkaiPoints.get(zenkaiBanners[bannerChoice].getZenkaiUnit()) + 100);
                                }
                            }
                            zPowerSlots[i].setText(zenkaiScores[i] + "+(100)");
                        }
                        if (zenkaiScores[i] == 1500) {
                            unitSlots[i].setForeground(getDrawable(R.drawable.yellow_border));
                        }
                    }
                    updateZenkaiState();
                    if (budgetEnabled)
                        crystalsUsed -= zenkaiBanners[bannerChoice].getMultiCost();
                    else
                        crystalsUsed += zenkaiBanners[bannerChoice].getMultiCost();
                } else
                    crystalWarning.show();

            }
            crystalCount.setText(String.valueOf(crystalsUsed));
        } else if (view == single_summon) {
            if (!budgetEnabled || crystalsUsed >= 100) {
                for (int i = 0; i < 10; i++) {
                    unitSlots[i].setImageResource(android.R.color.transparent);
                    unitSlots[i].setForeground(getDrawable(R.drawable.blank));
                    zPowerSlots[i].setText("");
                }
                if (isNormal) {
                    Card result = normalBanners[bannerChoice].singleSummon();
                    if (normalBanners[bannerChoice].getBannerCards().contains(result)) {
                        unitSlots[0].setForeground(getDrawable(R.drawable.red_border));
                        zPowerSlots[0].setText("x600");
                    } else if (normalBanners[bannerChoice].getLegendsLimitedCards() != null && normalBanners[bannerChoice].getLegendsLimitedCards().contains(result)) {
                        unitSlots[0].setForeground(getDrawable(R.drawable.yellow_border));
                        zPowerSlots[0].setText("x600");
                    } else if (result != DBLBanner.HE && result != DBLBanner.EX) {
                        zPowerSlots[0].setText("x600");
                    }
                    unitSlots[0].setImageResource(result.getCardImage());
                    cardsPulledHash.add(result);
                    cardsPulled.add(result);
                } else {
                    unitSlots[0].setImageResource(zenkaiBanners[bannerChoice].getZenkaiUnit().getCardImage());
                    int num = zenkaiBanners[bannerChoice].zenkaiSingle();
                    zPowerSlots[0].setText(String.valueOf(num));
                    if (zenkaiPoints.containsKey(zenkaiBanners[bannerChoice].getZenkaiUnit())) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            zenkaiPoints.replace(zenkaiBanners[bannerChoice].getZenkaiUnit(), zenkaiPoints.get(zenkaiBanners[bannerChoice].getZenkaiUnit()) + num);
                        }
                    }
                    updateZenkaiState();
                }
                if (budgetEnabled)
                    crystalsUsed -= 100;
                else
                    crystalsUsed += 100;
            } else
                crystalWarning.show();
            crystalCount.setText(String.valueOf(crystalsUsed));
        } else if (view == resetButton) {
            for (DBLBanner banner : zenkaiBanners) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    zenkaiPoints.replace(banner.getZenkaiUnit(), 0);
                }
            }
            if (!isNormal)
                updateZenkaiState();
            for (DBLBanner banner : normalBanners)
                if (banner.isStepUp)
                    banner.setStep(1);
            if (isNormal) {
                if (normalBanners[bannerChoice].isStepUp) {
                    stepNumber.setText(String.valueOf(normalBanners[bannerChoice].getStep()));
                    TransitionManager.beginDelayedTransition(constraintLayout, transition);
                    constraintSet2.applyTo(constraintLayout);
                } else {
                    TransitionManager.beginDelayedTransition(constraintLayout, transition);
                    constraintSet1.applyTo(constraintLayout);
                }
                multiCost.setText(new DecimalFormat(",###").format(normalBanners[bannerChoice].getMultiCost()));
                bannerImage.setImageResource(normalBanners[bannerChoice].getImage());
            } else {
                bannerImage.setImageResource(zenkaiBanners[bannerChoice].getImage());
                TransitionManager.beginDelayedTransition(constraintLayout, transition);
                constraintSet1.applyTo(constraintLayout);
                multiCost.setText(new DecimalFormat(",###").format(zenkaiBanners[bannerChoice].getMultiCost()));
                zenkaiTotal.setVisibility(View.VISIBLE);
                zenkaiImage.setVisibility(View.VISIBLE);
            }
            budgetEnabled = false;
            crystalsUsed = 0;
            cardsPulledHash.clear();
            cardsPulled.clear();
            for (int i = 0; i < 10; i++) {
                unitSlots[i].setImageResource(android.R.color.transparent);
                unitSlots[i].setForeground(getDrawable(R.drawable.blank));
                zPowerSlots[i].setText("");
            }
            crystalCount.setText(Integer.toString(crystalsUsed));
        } else if (view == summonHistoryButton) {
            background_audio3.release();
            Intent i = new Intent(DBL_Summon.this, DBL_Summon_History.class);
            startActivity(i);
            state = false;
            DBL_Summon_History.setLists(cardsPulled, cardsPulledHash);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else if (view == crystalCount) {
            openDialog();
        } else if (view == bannerSwitch) {
            single_summon.setClickable(false);
            multi_summon.setClickable(false);
            bannerSwitch.setEnabled(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    bannerSwitch.setEnabled(true);
                    single_summon.setClickable(true);
                    multi_summon.setClickable(true);
                }
            }, 1000);
            for (int i = 0; i < 10; i++) {
                unitSlots[i].setImageResource(android.R.color.transparent);
                unitSlots[i].setForeground(getDrawable(R.drawable.blank));
                zPowerSlots[i].setText("");
            }
            bannerChoice = 0;
            isNormal = !isNormal;
            if (isNormal) {
                zenkaiTotal.setVisibility(View.INVISIBLE);
                zenkaiImage.setVisibility(View.INVISIBLE);
                if (normalBanners[bannerChoice].isStepUp) {
                    stepNumber.setText(String.valueOf(normalBanners[bannerChoice].getStep()));
                    TransitionManager.beginDelayedTransition(constraintLayout, transition);
                    constraintSet2.applyTo(constraintLayout);
                } else {

                    TransitionManager.beginDelayedTransition(constraintLayout, transition);
                    constraintSet1.applyTo(constraintLayout);
                }
                multiCost.setText(new DecimalFormat(",###").format(normalBanners[bannerChoice].getMultiCost()));
                bannerImage.setImageResource(normalBanners[bannerChoice].getImage());
                bannerSwitch.setText("ZENKAI");
            } else {
                updateZenkaiState();
                bannerImage.setImageResource(zenkaiBanners[bannerChoice].getImage());
                TransitionManager.beginDelayedTransition(constraintLayout, transition);
                constraintSet1.applyTo(constraintLayout);
                multiCost.setText(new DecimalFormat(",###").format(zenkaiBanners[bannerChoice].getMultiCost()));
                zenkaiTotal.setVisibility(View.VISIBLE);
                zenkaiImage.setVisibility(View.VISIBLE);
                bannerSwitch.setText("NORMAL");
            }
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
        Intent i = new Intent(DBL_Summon.this, HomeScreen.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onFling(MotionEvent motionDown, MotionEvent motionEnd, float vX, float v1) {
        multi_summon.setClickable(false);
        single_summon.setClickable(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                multi_summon.setClickable(true);
                single_summon.setClickable(true);
            }
        }, 500);
        if (Math.abs(motionDown.getX() - motionEnd.getX()) >= 100 && Math.abs(vX) >= 750) {
            if (motionDown.getX() - motionEnd.getX() < 0) {
                bannerChoice++;
                if (isNormal) {
                    if (bannerChoice > normalBanners.length - 1)
                        bannerChoice = 0;
                    bannerImage.setImageResource(normalBanners[bannerChoice].getImage());
                    if (normalBanners[bannerChoice].isStepUp) {
                        stepNumber.setText(String.valueOf(normalBanners[bannerChoice].getStep()));
                        TransitionManager.beginDelayedTransition(constraintLayout, transition);
                        constraintSet2.applyTo(constraintLayout);
                    } else {
                        TransitionManager.beginDelayedTransition(constraintLayout, transition);
                        constraintSet1.applyTo(constraintLayout);
                        zenkaiImage.setVisibility(View.INVISIBLE);
                        zenkaiTotal.setVisibility(View.INVISIBLE);
                    }
                    multiCost.setText(new DecimalFormat(",###").format(normalBanners[bannerChoice].getMultiCost()));
                } else {

                    if (bannerChoice > zenkaiBanners.length - 1)
                        bannerChoice = 0;
                    updateZenkaiState();
                    bannerImage.setImageResource(zenkaiBanners[bannerChoice].getImage());
                    multiCost.setText(new DecimalFormat(",###").format(zenkaiBanners[bannerChoice].getMultiCost()));
                }
            } else if (motionDown.getX() - motionEnd.getX() > 0) {
                bannerChoice--;
                if (isNormal) {
                    if (bannerChoice < 0)
                        bannerChoice = normalBanners.length - 1;
                    bannerImage.setImageResource(normalBanners[bannerChoice].getImage());
                    if (normalBanners[bannerChoice].isStepUp) {
                        stepNumber.setText(String.valueOf(normalBanners[bannerChoice].getStep()));
                        TransitionManager.beginDelayedTransition(constraintLayout, transition);
                        constraintSet2.applyTo(constraintLayout);
                    } else {

                        TransitionManager.beginDelayedTransition(constraintLayout, transition);
                        constraintSet1.applyTo(constraintLayout);
                        zenkaiImage.setVisibility(View.INVISIBLE);
                        zenkaiTotal.setVisibility(View.INVISIBLE);
                    }
                    multiCost.setText(new DecimalFormat(",###").format(normalBanners[bannerChoice].getMultiCost()));
                } else {

                    if (bannerChoice < 0)
                        bannerChoice = zenkaiBanners.length - 1;
                    updateZenkaiState();
                    bannerImage.setImageResource(zenkaiBanners[bannerChoice].getImage());
                    multiCost.setText(new DecimalFormat(",###").format(zenkaiBanners[bannerChoice].getMultiCost()));
                }
            }
        }
        return true;
    }

    public void updateZenkaiState() {
        if (zenkaiPoints.get(zenkaiBanners[bannerChoice].getZenkaiUnit()) >= 7000)
            zenkaiTotal.setTextColor(Color.YELLOW);
        else
            zenkaiTotal.setTextColor(Color.WHITE);
        zenkaiTotal.setText(String.valueOf(zenkaiPoints.get(zenkaiBanners[bannerChoice].getZenkaiUnit())));
        zenkaiImage.setImageResource(zenkaiBanners[bannerChoice].getZenkaiUnit().getCardImage());
    }

    @Override
    public void getBudget(int budget) {
        resetButton.performClick();
        budgetEnabled = true;
        if (budget > 999999) {
            crystalsUsed = 999999;
            crystalCount.setText(String.valueOf(999999));
        } else {
            crystalsUsed = budget;
            crystalCount.setText(String.valueOf(budget));
        }

    }
}
