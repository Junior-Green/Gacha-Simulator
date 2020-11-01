package com.example.gacha4;

import java.util.ArrayList;
import java.util.Arrays;

public class DBLBanner {
    int image, step;
    String name;
    Boolean isStepUp;
    Card zenkaiUnit;
    public ArrayList<Card> featured, unfeatured, banner, legendsLimited;
    public static final Card EX = new Card(R.drawable.dbl_ex_icon, "EX");
    public static final Card HE = new Card(R.drawable.dbl_he_icon, "HE");
    public static final ArrayList<Card> ZENKAIS = new ArrayList<>(Arrays.asList(new Card(R.drawable.dbl06_11s_zenkai_zpower, "dbl06_11s"), new Card(R.drawable.dbl06_11s_zenkai_zpower, "dbl06_13s")));
    public static final ArrayList<Card> SPARKINGS = new ArrayList<>(Arrays.asList(new Card(R.drawable.dbl01_04s, "dbl01_04s")));

    public DBLBanner(int bannerImage, String name, ArrayList<Card> bannerPool, ArrayList<Card> unfeaturedPool, ArrayList<Card> featuredPool, ArrayList<Card> legendsLimitedPool, Boolean stepUp) {
        image = bannerImage;
        featured = featuredPool;
        unfeatured = unfeaturedPool;
        banner = bannerPool;
        legendsLimited = legendsLimitedPool;
        this.name = name;
        isStepUp = stepUp;
        if (isStepUp)
            step = 1;
    }

    public DBLBanner(int bannerImage, String name, Card zenkaiUnit) {
        image = bannerImage;
        this.name = name;
        this.zenkaiUnit = zenkaiUnit;
    }

    public Card[] zenkaiMulti() {
        Card[] results = new Card[10];


        return results;
    }
}
