package com.example.gacha4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.GestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.skydoves.elasticviews.ElasticImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class SDS_Summon extends AppCompatActivity implements View.OnClickListener,BudgetDialog.BudgetDialogListener{
    MediaPlayer background_audio;
    ElasticImageView single_summon, multi_summon, mute_button, home_button,right_arrow,left_arrow;
    ImageView bannerImage;
    TextView homeScreenButton, resetButton, summonHistoryButton, diamondCount;
    ConstraintLayout constraintLayout;
    ConstraintSet constraintSet1 = new ConstraintSet();
    ConstraintSet constraintSet2 = new ConstraintSet();
    Transition transition = new ChangeBounds();
    BlurView blurView;
    Toast diamondWarning;
    Boolean state = true;
    private static Boolean budgetEnabled = false;
    View backDrop;
    static Boolean volume_state = true;
    static ArrayList<Card> cardsPulled = new ArrayList<>();
    static HashSet<Card> cardsPulledHash = new HashSet<>();
    static ImageView[] unitsSlots;
    static int bannerChoice = 0, diamondsUsed = 0;
    SDSBanner[] banners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        constraintSet1.clone(this, R.layout.activity_sds_summon);
        constraintSet2.clone(this, R.layout.activity_sds_summon_animation);
        setContentView(R.layout.activity_sds_summon);

        //---------------------------------------------------------------------------------------------------------BANNERS---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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

        summonHistoryButton = findViewById(R.id.summon_history_dokkan);
        summonHistoryButton.setOnClickListener(this);

        home_button = findViewById(R.id.home_button);
        home_button.setOnClickListener(this);

        diamondCount = findViewById(R.id.diamonds_used);
        diamondCount.setText(Integer.toString(diamondsUsed));
        diamondCount.setOnClickListener(this);

        right_arrow = findViewById(R.id.sds_right_arrow);
        right_arrow.setOnClickListener(this);

        left_arrow = findViewById(R.id.sds_left_arrow);
        left_arrow.setOnClickListener(this);

        unitsSlots = new ImageView[]{findViewById(R.id.sds_slot1), findViewById(R.id.sds_slot2), findViewById(R.id.sds_slot3), findViewById(R.id.sds_slot4), findViewById(R.id.sds_slot5),
                findViewById(R.id.sds_slot6), findViewById(R.id.sds_slot7), findViewById(R.id.sds_slot8), findViewById(R.id.sds_slot9), findViewById(R.id.sds_slot10),};

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
        if (view == right_arrow)
        {
            bannerChoice++;
            if (bannerChoice >= banners.length)
                bannerChoice = 0;
            bannerImage.setImageResource(banners[bannerChoice].image);
        }
        else if(view == left_arrow)
        {
            bannerChoice--;
            if (bannerChoice < 0)
                bannerChoice = banners.length-1;
            bannerImage.setImageResource(banners[bannerChoice].image);
        }
        else if(view == mute_button)
        {
            if (mute_button.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.no_mute).getConstantState())) {
                mute_button.setImageResource(R.drawable.ic_mute_icon);
                background_audio.setVolume(0, 0);
                volume_state = false;
            } else {
                mute_button.setImageResource(R.drawable.no_mute);
                background_audio.setVolume(1.0f, 1.0f);
                volume_state = true;
            }
        }
        else if(view == multi_summon)
        {
            Card[] results = banners[bannerChoice].multiSummon();
            for (int i = 0; i < 11; i++) {
                unitsSlots[i].setImageResource(results[i].getCardImage());
                if (banners[bannerChoice].rateUp.contains(results[i]))
                    unitsSlots[i].setForeground(getDrawable(R.drawable.red_border));
            }
            cardsPulled.addAll(Arrays.asList(results));
            cardsPulledHash.addAll(Arrays.asList(results));
            diamondsUsed += 30;
            diamondCount.setText(String.valueOf(diamondsUsed));

        }
        else if(view == single_summon)
        {
            Card result = banners[bannerChoice].singleSummon();
            for (ImageView views : unitsSlots) {
                views.setImageResource(android.R.color.transparent);
                views.setForeground(getDrawable(R.drawable.blank));
            }
            unitsSlots[0].setImageResource(result.image);
            if (banners[bannerChoice].rateUp.contains(result))
                unitsSlots[0].setForeground(getDrawable((R.drawable.red_border)));
            diamondsUsed += 3;
            diamondCount.setText(String.valueOf(diamondsUsed));
        }
        else if(view == home_button)
        {

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