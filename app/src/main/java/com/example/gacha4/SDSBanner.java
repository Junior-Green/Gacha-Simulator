package com.example.gacha4;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SDSBanner {
    int image;
    private static final Random rng = new Random();
    public ArrayList<Card> banner, featured;
    public static final Card RARE = new Card(R.drawable.sds_rare,"0");
    public static final Card SR = new Card(R.drawable.sds_sr,"0");
    //public static final ArrayList<Card> SSRS = new ArrayList<>(Arrays.asList(new Card(R.drawable.dian)));
}
