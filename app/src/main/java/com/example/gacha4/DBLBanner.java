package com.example.gacha4;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DBLBanner {
    int image, step;
    Boolean isStepUp, isGuranteed;
    Card zenkaiUnit;
    private static Random rng = new Random();
    private static DecimalFormat df = new DecimalFormat("#.###");
    public ArrayList<Card> featured, unfeatured, banner, legendsLimited;
    public static final Card EX = new Card(R.drawable.dbl_ex_icon, 0);
    public static final Card HE = new Card(R.drawable.dbl_he_icon, 1);
    public static final ArrayList<Card> ZENKAIS = new ArrayList<>(Arrays.asList(new Card(R.drawable.dbl06_11s_zenkai_zpower, 611), new Card(R.drawable.dbl06_11s_zenkai_zpower, 613)));
    public static final ArrayList<Card> SPARKINGS = new ArrayList<>(Arrays.asList(new Card(R.drawable.dbl01_04s, 104), new Card(R.drawable.dbl01_05s, 105), new Card(R.drawable.dbl01_07s, 107),
            new Card(R.drawable.dbl01_16s, 116), new Card(R.drawable.dbl01_17s, 117), new Card(R.drawable.dbl01_35s, 135), new Card(R.drawable.dbl01_36s, 136),
            new Card(R.drawable.dbl01_41s, 141), new Card(R.drawable.dbl01_44s, 144), new Card(R.drawable.dbl02_01s, 201), new Card(R.drawable.dbl02_02s, 202),
            new Card(R.drawable.dbl02_11s, 211), new Card(R.drawable.dbl02_13s, 213), new Card(R.drawable.dbl02_15s, 215), new Card(R.drawable.dbl03_01s, 301),
            new Card(R.drawable.dbl03_03s, 303), new Card(R.drawable.dbl03_05s, 305), new Card(R.drawable.dbl03_06s, 306), new Card(R.drawable.dbl03_10s, 310),
            new Card(R.drawable.dbl03_13s, 313), new Card(R.drawable.dbl03_14s, 314), new Card(R.drawable.dbl03_15s, 315), new Card(R.drawable.dbl04_01s, 401),
            new Card(R.drawable.dbl04_04s, 404), new Card(R.drawable.dbl04_10s, 410), new Card(R.drawable.dbl04_11s, 411), new Card(R.drawable.dbl05_01s, 501),
            new Card(R.drawable.dbl05_03s, 503), new Card(R.drawable.dbl05_05s, 505), new Card(R.drawable.dbl05_06s, 506), new Card(R.drawable.dbl05_07s, 507),
            new Card(R.drawable.dbl05_08s, 508), new Card(R.drawable.dbl05_10s, 510), new Card(R.drawable.dbl05_11s, 511), new Card(R.drawable.dbl06_01s, 601),
            new Card(R.drawable.dbl06_06s, 606), new Card(R.drawable.dbl06_07s, 607), new Card(R.drawable.dbl06_10s, 610), new Card(R.drawable.dbl06_11s, 611),
            new Card(R.drawable.dbl06_12s, 612), new Card(R.drawable.dbl06_13s, 613), new Card(R.drawable.dbl07_01s, 701), new Card(R.drawable.dbl07_04s, 704),
            new Card(R.drawable.dbl07_05s, 705), new Card(R.drawable.dbl07_07s, 707), new Card(R.drawable.dbl07_08s, 708), new Card(R.drawable.dbl07_09s, 709),
            new Card(R.drawable.dbl07_10s, 710), new Card(R.drawable.dbl07_11s, 711), new Card(R.drawable.dbl08_01s, 801), new Card(R.drawable.dbl08_02s, 802),
            new Card(R.drawable.dbl08_03s, 803), new Card(R.drawable.dbl08_10s, 810), new Card(R.drawable.dbl08_13s, 813), new Card(R.drawable.dbl08_14s, 814),
            new Card(R.drawable.dbl09_01s, 901), new Card(R.drawable.dbl09_03s, 903), new Card(R.drawable.dbl09_04s, 904), new Card(R.drawable.dbl09_05s, 905),
            new Card(R.drawable.dbl09_06s, 906), new Card(R.drawable.dbl09_07s, 907), new Card(R.drawable.dbl09_09s, 909), new Card(R.drawable.dbl10_01s, 1001),
            new Card(R.drawable.dbl10_02s, 1002), new Card(R.drawable.dbl10_03s, 1003), new Card(R.drawable.dbl10_09s, 1009), new Card(R.drawable.dbl10_10s, 1010),
            new Card(R.drawable.dbl10_11s, 1011), new Card(R.drawable.dbl11_01s, 1101), new Card(R.drawable.dbl11_02s, 1102), new Card(R.drawable.dbl11_04s, 1104),
            new Card(R.drawable.dbl11_06s, 1106), new Card(R.drawable.dbl11_07s, 1107), new Card(R.drawable.dbl11_08s, 1108), new Card(R.drawable.dbl11_09s, 1109),
            new Card(R.drawable.dbl12_01s, 1201), new Card(R.drawable.dbl12_02s, 1202), new Card(R.drawable.dbl12_04s, 1204), new Card(R.drawable.dbl12_05s, 1205),
            new Card(R.drawable.dbl12_06s, 1206), new Card(R.drawable.dbl13_01s, 1301), new Card(R.drawable.dbl13_02s, 1302), new Card(R.drawable.dbl13_06s, 1306),
            new Card(R.drawable.dbl13_09s, 1309), new Card(R.drawable.dbl14_01s, 1401), new Card(R.drawable.dbl14_02s, 1402), new Card(R.drawable.dbl14_03s, 1403),
            new Card(R.drawable.dbl14_04s, 1404), new Card(R.drawable.dbl14_05s, 1405), new Card(R.drawable.dbl15_01s, 1501), new Card(R.drawable.dbl15_02s, 1502),
            new Card(R.drawable.dbl15_06s, 1506), new Card(R.drawable.dbl15_07s, 1507), new Card(R.drawable.dbl15_08s, 1508), new Card(R.drawable.dbl16_01s, 1601),
            new Card(R.drawable.dbl16_02s, 1602), new Card(R.drawable.dbl16_06s, 1606), new Card(R.drawable.dbl16_07s, 1607), new Card(R.drawable.dbl16_08s, 1608),
            new Card(R.drawable.dbl16_09s, 1609), new Card(R.drawable.dbl16_10s, 1610), new Card(R.drawable.dbl16_13s, 1613), new Card(R.drawable.dbl17_01s, 1701),
            new Card(R.drawable.dbl17_03s, 1703), new Card(R.drawable.dbl17_06s, 1706), new Card(R.drawable.dbl18_01s, 1801), new Card(R.drawable.dbl18_03s, 1803),
            new Card(R.drawable.dbl18_05s, 1805), new Card(R.drawable.dbl18_06s, 1806), new Card(R.drawable.dbl19_01s, 1901), new Card(R.drawable.dbl19_03s, 1903),
            new Card(R.drawable.dbl19_05s, 1905), new Card(R.drawable.dbl19_07s, 1907), new Card(R.drawable.dbl19_08s, 1908), new Card(R.drawable.dbl20_01s, 2001),
            new Card(R.drawable.dbl20_02s, 2002), new Card(R.drawable.dbl20_05s, 2005), new Card(R.drawable.dbl20_06s, 2006), new Card(R.drawable.dbl21_01s, 2101),
            new Card(R.drawable.dbl21_02s, 2102), new Card(R.drawable.dbl21_05s, 2105), new Card(R.drawable.dbl22_01s, 2201), new Card(R.drawable.dbl22_02s, 2202),
            new Card(R.drawable.dbl22_04s, 2204), new Card(R.drawable.dbl22_05s, 2205), new Card(R.drawable.dbl22_08s, 2208), new Card(R.drawable.dbl22_09s, 2209),
            new Card(R.drawable.dbl23_01s, 2301), new Card(R.drawable.dbl23_02s, 2302), new Card(R.drawable.dbl23_04s, 2304), new Card(R.drawable.dbl23_05s, 2305),
            new Card(R.drawable.dbl24_01s, 2401), new Card(R.drawable.dbl24_08s, 2408), new Card(R.drawable.dbl24_13s, 2413), new Card(R.drawable.dbl24_15s, 2415),
            new Card(R.drawable.dbl26_02s, 2602), new Card(R.drawable.dbl26_03s, 2603), new Card(R.drawable.dbl26_06s, 2606), new Card(R.drawable.dbl26_08s, 2608),
            new Card(R.drawable.dbl27_05s, 2705), new Card(R.drawable.dbl27_06s, 2706), new Card(R.drawable.dbl28_05s, 2805), new Card(R.drawable.dbl28_06s, 2806),
            new Card(R.drawable.dbl15_05s, 1505)));

    public DBLBanner(int bannerImage, ArrayList<Card> bannerPool, ArrayList<Card> unfeaturedPool, ArrayList<Card> featuredPool, ArrayList<Card> legendsLimitedPool, Boolean isStepUp, Boolean isGuranteed) {
        image = bannerImage;
        featured = featuredPool;
        unfeatured = unfeaturedPool;
        banner = bannerPool;
        legendsLimited = legendsLimitedPool;
        this.isGuranteed = isGuranteed;
        this.isStepUp = isStepUp;
        if (this.isStepUp)
            step = 1;
    }

    public DBLBanner(int bannerImage, Card zenkaiUnit) {
        image = bannerImage;
        this.zenkaiUnit = zenkaiUnit;
    }

    public static ArrayList<Card> findCardsByID(ArrayList<Integer> ids, ArrayList<Card> pool) {
        ArrayList<Card> results = new ArrayList<>();
        for (int id : ids)
            for (Card card : pool)
                if (card.getCardID() == id)
                    results.add(card);

        return results;
    }

    public static Card findCardByID(int id, ArrayList<Card> pool) {
        for (Card card : pool)
            if (card.getCardID() == id)
                return card;

        return null;
    }

    public ArrayList<Card> stepUpSummon() {
        ArrayList<Card> results = new ArrayList<>();
        if (isStepUp) {
            switch (step) {
                case 1:
                    for (int i = 0; i < 3; i++) {
                        float num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                        if (num >= 0 && num <= 0.75)
                            results.add(legendsLimited.get(rng.nextInt(legendsLimited.size())));
                        else if (num > 0.75 && num <= 2.75)
                            results.add(featured.get(rng.nextInt(featured.size())));
                        else if (num > 2.75 && num <= 8.75)
                            results.add(banner.get(rng.nextInt(banner.size())));
                        else if (num > 8.75 && num <= 10)
                            results.add(unfeatured.get(rng.nextInt(unfeatured.size())));
                        else if (num > 10 && num <= 35)
                            results.add(DBLBanner.EX);
                        else
                            results.add(DBLBanner.HE);
                    }
                    break;
                case 2:
                    for (int i = 0; i < 5; i++) {
                        float num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                        if (num >= 0 && num <= 0.75)
                            results.add(legendsLimited.get(rng.nextInt(legendsLimited.size())));
                        else if (num > 0.75 && num <= 2.75)
                            results.add(featured.get(rng.nextInt(featured.size())));
                        else if (num > 2.75 && num <= 8.75)
                            results.add(banner.get(rng.nextInt(banner.size())));
                        else if (num > 8.75 && num <= 10)
                            results.add(unfeatured.get(rng.nextInt(unfeatured.size())));
                        else
                            results.add(DBLBanner.EX);
                    }
                    break;
                case 3:
                case 6:
                case 7:
                    for (int i = 0; i < 10; i++) {
                        float num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                        if (num >= 0 && num <= 0.75)
                            results.add(legendsLimited.get(rng.nextInt(legendsLimited.size())));
                        else if (num > 0.75 && num <= 2.75)
                            results.add(featured.get(rng.nextInt(featured.size())));
                        else if (num > 2.75 && num <= 8.75)
                            results.add(banner.get(rng.nextInt(banner.size())));
                        else if (num > 8.75 && num <= 10)
                            results.add(unfeatured.get(rng.nextInt(unfeatured.size())));
                        else if (num > 10 && num <= 35)
                            results.add(DBLBanner.EX);
                        else
                            results.add(DBLBanner.HE);
                    }
                    break;
                case 4:
                    for (int i = 0; i < 10; i++) {
                        float num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                        if (num >= 0 && num <= 0.75)
                            results.add(legendsLimited.get(rng.nextInt(legendsLimited.size())));
                        else if (num > 0.75 && num <= 2.75)
                            results.add(featured.get(rng.nextInt(featured.size())));
                        else if (num > 2.75 && num <= 8.75)
                            results.add(banner.get(rng.nextInt(banner.size())));
                        else if (num > 8.75 && num <= 10)
                            results.add(unfeatured.get(rng.nextInt(unfeatured.size())));
                        else
                            results.add(DBLBanner.EX);
                    }
                    break;
                case 5:
                    for (int i = 0; i < 10; i++) {
                        float num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                        if (num >= 0 && num <= 0.75)
                            results.add(legendsLimited.get(rng.nextInt(legendsLimited.size())));
                        else if (num > 0.75 && num <= 4.75)
                            results.add(featured.get(rng.nextInt(featured.size())));
                        else if (num > 4.75 && num <= 10.75)
                            results.add(banner.get(rng.nextInt(banner.size())));
                        else if (num > 10.75 && num <= 12.00)
                            results.add(unfeatured.get(rng.nextInt(unfeatured.size())));
                        else if (num > 12 && num <= 37.418)
                            results.add(DBLBanner.EX);
                        else
                            results.add(DBLBanner.HE);
                    }
                    break;
                default:
                    results = null;
                    break;
            }
            step++;
            if (step > 7)
                step = 3;
        }
        return results;
    }

    public Card[] guranteedSummmon() {
        Card[] results = new Card[10];
        float num;
        if (legendsLimited.isEmpty()) {
            for (int i = 0; i < 9; i++) {
                num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                if (num >= 0 && num <= 10)
                    results[i] = (banner.get(rng.nextInt(banner.size())));
                else if (num > 10 && num <= 35)
                    results[i] = (DBLBanner.EX);
                else results[i] = (DBLBanner.HE);
            }
            results[9] = banner.get(rng.nextInt(banner.size()));
        } else {
            for (int i = 0; i < 9; i++) {
                num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                if (num >= 0 && num <= 0.25 * legendsLimited.size())
                    results[i] = legendsLimited.get(rng.nextInt(legendsLimited.size()));
                else if (num > legendsLimited.size() * 0.25 && num <= 10)
                    results[i] = banner.get(rng.nextInt(banner.size()));
                else if (num > 10 && num <= 35)
                    results[i] = DBLBanner.EX;
                else
                    results[i] = DBLBanner.HE;
            }
            num = Float.parseFloat(df.format(rng.nextFloat() * 100));
            if (num >= 0 && num <= legendsLimited.size() * 2.5)
                results[9] = legendsLimited.get(rng.nextInt(legendsLimited.size()));
            else
                results[9] = banner.get(rng.nextInt(banner.size()));
        }
        return results;
    }

    public Card[] normalSummon() {
        Card[] results = new Card[10];
        float num;
        if (legendsLimited.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                if (num >= 0 && num <= 10)
                    results[i] = (banner.get(rng.nextInt(banner.size())));
                else if (num > 10 && num <= 35)
                    results[i] = (DBLBanner.EX);
                else results[i] = (DBLBanner.HE);
            }
        } else {
            for (int i = 0; i < 10; i++) {
                num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                if (num >= 0 && num <= 0.25 * legendsLimited.size())
                    results[i] = legendsLimited.get(rng.nextInt(legendsLimited.size()));
                else if (num > 0.25 * legendsLimited.size() && num <= 10)
                    results[i] = banner.get(rng.nextInt(banner.size()));
                else if (num > 10 && num <= 35)
                    results[i] = DBLBanner.EX;
                else
                    results[i] = DBLBanner.HE;
            }
        }
        return results;
    }

    public Card singleSummon() {
        Card result = null;
        float num;
        if (legendsLimited.isEmpty()) {
            num = Float.parseFloat(df.format(rng.nextFloat() * 100));
            if (num >= 0 && num <= 10)
                result = (banner.get(rng.nextInt(banner.size())));
            else if (num > 10 && num <= 35)
                result = (DBLBanner.EX);
            else result = (DBLBanner.HE);

        } else {
            num = Float.parseFloat(df.format(rng.nextFloat() * 100));
            if (num >= 0 && num <= 0.25 * legendsLimited.size())
                result = legendsLimited.get(rng.nextInt(legendsLimited.size()));
            else if (num > 0.25 * legendsLimited.size() && num <= 10)
                result = banner.get(rng.nextInt(banner.size()));
            else if (num > 10 && num <= 35)
                result = DBLBanner.EX;
            else
                result = DBLBanner.HE;
        }
        return result;
    }

    public int[] zenkaiMulti() {
        int[] results = new int[10];
        for (int i = 0; i < 10; i++) {
            float num = Float.parseFloat(df.format(rng.nextFloat() * 100));
            if (num >= 0 && num <= 27.65)
                results[i] = 70;
            else if (num > 27.65 && num <= 27.75)
                results[i] = 1500;
            else if (num > 27.75 && num <= 28)
                results[i] = 500;
            else
                results[i] = 100;
        }
        return results;
    }

    public int zenkaiSingle() {
        int result = 0;
        float num = Float.parseFloat(df.format(rng.nextFloat() * 100));
        if (num >= 0 && num <= 27.65)
            result = 70;
        else if (num > 27.65 && num <= 27.75)
            result = 1500;
        else if (num > 27.75 && num <= 28)
            result = 500;
        else
            result = 100;

        return result;
    }

    public int getStep() {
        return step;
    }

    public int getImage() {
        return image;
    }

    public int getMultiCost() {
        if (!isStepUp)
            return 1000;
        else {
            switch (step) {
                case 1:
                    return 100;
                case 2:
                    return 300;
                case 3:
                    return 700;
                case 7:
                    return 0;
                default:
                    return 1000;
            }
        }
    }

    public ArrayList<Card> getBannerCards() {
        return banner;
    }

    public ArrayList<Card> getFeaturedCards() {
        return featured;
    }

    public ArrayList<Card> getLegendsLimitedCards() {
        return legendsLimited;
    }
}
