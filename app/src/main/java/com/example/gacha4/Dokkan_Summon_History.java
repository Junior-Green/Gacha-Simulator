package com.example.gacha4;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Dokkan_Summon_History extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    MediaPlayer background_audio3;
    TextView back;
    ImageButton mute_button, home_button;
    Boolean state = true;
    ImageView back_arrow;
    private static ArrayList<Card> cards, cardsHash;
    private ArrayList<String> frequencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokkan__summon__history);

        back_arrow = findViewById(R.id.back_arrow_dokkan_history);
        back_arrow.setOnClickListener(this);
        back = findViewById(R.id.back_dokkan_history);
        back.setOnClickListener(this);
        background_audio3 = MediaPlayer.create(Dokkan_Summon_History.this, R.raw.dokkan_summon_theme_audio);
        background_audio3.setLooping(true);
        background_audio3.start();


        mute_button = findViewById(R.id.volume_control2);
        mute_button.setOnClickListener(this);

        home_button = findViewById(R.id.home_button2);

        if (!Dokkan_Summon.volume_state) {
            mute_button.setImageResource(R.drawable.ic_mute_icon);
            background_audio3.setVolume(0f, 0f);
        }

        if (!cardsHash.isEmpty()) {
            frequencies = new ArrayList<>();
            for (Card c : cardsHash)
                frequencies.add(Integer.toString(Collections.frequency(cards, c)));
        } else
            frequencies = new ArrayList<>();

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        layoutManager.setAlignItems(AlignItems.STRETCH);

        recyclerView = findViewById(R.id.recycler_dokkan);
        recyclerView.setLayoutManager(layoutManager);
        DokkanRecyclerViewAdapter adapter = new DokkanRecyclerViewAdapter(cardsHash, frequencies);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view == mute_button) {
            if (mute_button.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.no_mute).getConstantState())) {
                mute_button.setImageResource(R.drawable.ic_mute_icon);
                background_audio3.setVolume(0, 0);
                Dokkan_Summon.volume_state = false;
            } else {
                mute_button.setImageResource(R.drawable.no_mute);
                background_audio3.setVolume(1.0f, 1.0f);
                Dokkan_Summon.volume_state = true;
            }
        } else if (view == back || view == back_arrow) {
            background_audio3.release();
            Intent i = new Intent(Dokkan_Summon_History.this, Dokkan_Summon.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            state = false;
            finish();
        }
    }

    public static void setLists(ArrayList<Card> list, HashSet<Card> list2) {
        cards = new ArrayList<>();
        cards.addAll(list);
        cardsHash = new ArrayList<>();
        cardsHash.addAll(list2);
        cardsHash.removeAll(Arrays.asList(DokkanBanner.RARE, DokkanBanner.SR));
    }

    @Override
    public void onBackPressed() {
        background_audio3.release();
        Intent i = new Intent(Dokkan_Summon_History.this, Dokkan_Summon.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        state = false;
        finish();
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
}