package com.example.gacha4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DokkanBanner {
    int image;
    String name;
    public ArrayList<Card> featured, unfeatured;
    public static final Card RARE = new Card(R.drawable.dokkan_rare_icon, 0);
    public static final Card SR = new Card(R.drawable.dokkan_sr_icon, 1);
    public static final ArrayList<Card> NORMALPOOL = new ArrayList<>(Arrays.asList(new Card(R.drawable.thum_1000010_1, 1000010), new Card(R.drawable.thum_1000020_1, 1000020), new Card(R.drawable.thum_1000840_1, 1000840),
            new Card(R.drawable.thum_1001390_1, 1001390), new Card(R.drawable.thum_1001400_1, 1001400), new Card(R.drawable.thum_1001480_1, 1001480),
            new Card(R.drawable.thum_1001490_1, 1001490), new Card(R.drawable.thum_1001590_1, 1001590), new Card(R.drawable.thum_1001630_1, 1001630),
            new Card(R.drawable.thum_1001690_1, 1001690), new Card(R.drawable.thum_1001700_1, 1001700), new Card(R.drawable.thum_1001790_1, 1001790),
            new Card(R.drawable.thum_1001800_1, 1001800), new Card(R.drawable.thum_1001920_1, 1001920), new Card(R.drawable.thum_1001930_1, 1001930),
            new Card(R.drawable.thum_1001940_1, 1001940), new Card(R.drawable.thum_1001950_1, 1001950), new Card(R.drawable.thum_1001960_1, 1001960),
            new Card(R.drawable.thum_1001970_1, 1001970), new Card(R.drawable.thum_1002020_1, 1002020), new Card(R.drawable.thum_1002080_1, 1002080),
            new Card(R.drawable.thum_1002090_1, 1002090), new Card(R.drawable.thum_1002100_1, 1002100), new Card(R.drawable.thum_1002390_1, 1002390),
            new Card(R.drawable.thum_1002400_1, 1002400), new Card(R.drawable.thum_1002450_1, 1002450), new Card(R.drawable.thum_1002460_1, 1002460),
            new Card(R.drawable.card_1002620, 1002620), new Card(R.drawable.card_1002670, 1002670), new Card(R.drawable.thum_1002700_1, 1002700), new Card(R.drawable.thum_1002810, 1002810),
            new Card(R.drawable.card_1002820, 1002820), new Card(R.drawable.card_1002830, 1002830), new Card(R.drawable.thum_1002990_1, 1002990), new Card(R.drawable.thum_1003020_1, 1003020), new Card(R.drawable.thum_1003030_1, 1003030),
            new Card(R.drawable.thum_1003040, 1003040), new Card(R.drawable.thum_1003180_1, 1003180), new Card(R.drawable.thum_1003190_1, 1003190), new Card(R.drawable.thum_1003200_1, 1003200),
            new Card(R.drawable.thum_1003230_1, 1003230), new Card(R.drawable.thum_1003240_1, 1003240), new Card(R.drawable.thum_1003260_1, 1003260), new Card(R.drawable.thum_1003270_1, 1003270),
            new Card(R.drawable.thum_1003290_1, 1003290), new Card(R.drawable.thum_1003360_1, 1003360), new Card(R.drawable.thum_1003370_1, 1003370), new Card(R.drawable.thum_1003600_1, 1003600),
            new Card(R.drawable.thum_1003610_1, 1003610), new Card(R.drawable.thum_1003660_1, 1003660), new Card(R.drawable.thum_1003670_1, 1003670), new Card(R.drawable.thum_1003690_1, 1003690), new Card(R.drawable.thum_1003720_1, 1003720), new Card(R.drawable.thum_1003780_1, 1003780),
            new Card(R.drawable.thum_1003790_1, 1003790), new Card(R.drawable.thum_1004000_1, 1004000), new Card(R.drawable.thum_1004070_1, 1004070), new Card(R.drawable.thum_1004080_1, 1004080), new Card(R.drawable.thum_1004090_1, 1004090),
            new Card(R.drawable.thum_1004210_1, 1004210), new Card(R.drawable.thum_1004340_1, 1004340), new Card(R.drawable.thum_1004350_1, 1004350), new Card(R.drawable.thum_1004360_1, 1004360),
            new Card(R.drawable.thum_1004370_1, 1004370), new Card(R.drawable.thum_1004380_1, 1004380), new Card(R.drawable.card_1004480, 1004480), new Card(R.drawable.thum_1004600_1, 1004600),
            new Card(R.drawable.thum_1004710_1, 1004710), new Card(R.drawable.thum_1004720_1, 1004720), new Card(R.drawable.thum_1004910_1, 1004910), new Card(R.drawable.thum_1004940_1, 1004940), new Card(R.drawable.thum_1004980_1, 1004980),
            new Card(R.drawable.thum_1004990_1, 1004990), new Card(R.drawable.card_1005140, 1005140), new Card(R.drawable.thum_1005150, 1005150), new Card(R.drawable.card_1005190, 1005190), new Card(R.drawable.card_1005210, 1005210),
            new Card(R.drawable.thum_1005220_1, 1005220), new Card(R.drawable.thum_1005260_1, 1005260), new Card(R.drawable.card_1005340, 1005340), new Card(R.drawable.thum_1005350, 1005350), new Card(R.drawable.thum_1005410_1, 1005410),
            new Card(R.drawable.thum_1005640_1, 1005640), new Card(R.drawable.thum_1005780_1, 1005780), new Card(R.drawable.thum_1005940_1, 1005940), new Card(R.drawable.thum_1006030, 1006030), new Card(R.drawable.thum_1006360, 1006360),
            new Card(R.drawable.thumb_1006480, 1006480), new Card(R.drawable.thumb_1006490, 1006490), new Card(R.drawable.thumb_1006500, 1006500), new Card(R.drawable.card_1006510, 1006510), new Card(R.drawable.thum_1006710, 1006710),
            new Card(R.drawable.thum_1006750, 1006750), new Card(R.drawable.card_1006760_thumb, 1006750), new Card(R.drawable.card_1006780, 1006780), new Card(R.drawable.card_1007010, 1007010), new Card(R.drawable.card_1007090, 1007090),
            new Card(R.drawable.card_1007370, 1007370), new Card(R.drawable.card_1007380, 1007380), new Card(R.drawable.card_1007780, 1007780), new Card(R.drawable.card_1007860_thumb, 1007860), new Card(R.drawable.card_1007880, 1007880),
            new Card(R.drawable.card_1007890, 1007890), new Card(R.drawable.thum_1008140, 1008140), new Card(R.drawable.card_1008340, 1008340), new Card(R.drawable.thum_1008650, 1008650),
            new Card(R.drawable.card_1008660, 1008660), new Card(R.drawable.thum_1008670, 1008670), new Card(R.drawable.card_1008680, 1008680), new Card(R.drawable.thum_1008690, 1008690), new Card(R.drawable.card_1008700, 1008700),
            new Card(R.drawable.thum_1008720, 1008720), new Card(R.drawable.thum_1008760, 1008760), new Card(R.drawable.thum_1008860, 1008860), new Card(R.drawable.thum_1008880, 1008880), new Card(R.drawable.thum_1009160, 1009160),
            new Card(R.drawable.thum_1009530, 1009530), new Card(R.drawable.card_1009540, 1009540), new Card(R.drawable.card_1009760, 1009760), new Card(R.drawable.thum_1009990, 1009990),
            new Card(R.drawable.thum_1010010, 1010010), new Card(R.drawable.thum_1010090, 1010090), new Card(R.drawable.thum_1010100, 1010100), new Card(R.drawable.thum_1010110, 1010110),
            new Card(R.drawable.thum_1010120, 1010120), new Card(R.drawable.thum_1010130, 1010130), new Card(R.drawable.thum_1010140, 1010140), new Card(R.drawable.thum_1010280, 1010280), new Card(R.drawable.card_1010540, 1010540),
            new Card(R.drawable.card_1010550, 1010550), new Card(R.drawable.card_1010630, 1010630), new Card(R.drawable.card_1010670, 1010670), new Card(R.drawable.card_1010680, 1010680), new Card(R.drawable.card_1010690, 1010690),
            new Card(R.drawable.card_1011580, 1011580), new Card(R.drawable.card_1011590, 1011590), new Card(R.drawable.card_1011750, 1011750), new Card(R.drawable.card_1011760, 1011760),
            new Card(R.drawable.card_1011820_thumb, 1011820), new Card(R.drawable.card_1011830, 1011830), new Card(R.drawable.card_1011940, 1011940), new Card(R.drawable.card_1011950, 1011950),
            new Card(R.drawable.card_1011990, 1011990), new Card(R.drawable.thum_1012040, 1012040), new Card(R.drawable.thumb_1012060, 1012060), new Card(R.drawable.card_1012070_thumb, 1012070), new Card(R.drawable.thumb_1012090, 1012090),
            new Card(R.drawable.card_1012300_thumb, 1012300), new Card(R.drawable.card_1012410_thumb, 1012410), new Card(R.drawable.thumb_1012610, 1012610), new Card(R.drawable.card_1012640, 1012640), new Card(R.drawable.card_1012670, 1012670),
            new Card(R.drawable.card_1012690, 1012690), new Card(R.drawable.card_1012860_thumb, 1012860), new Card(R.drawable.card_1012950, 1012950), new Card(R.drawable.card_1012980, 1012980), new Card(R.drawable.thumb_1013170, 1013170), new Card(R.drawable.thumb_1013180, 1013180),
            new Card(R.drawable.thumb_1013190, 1012190), new Card(R.drawable.thumb_1013200, 1013200), new Card(R.drawable.thumb_1013700, 1013700), new Card(R.drawable.thumb_1013920, 1013920), new Card(R.drawable.thumb_1013930, 1013930),
            new Card(R.drawable.card_1014200_thumb, 1014200), new Card(R.drawable.card_1014320, 1014320), new Card(R.drawable.card_1014340, 1014340), new Card(R.drawable.thumb_1014360, 1014360), new Card(R.drawable.thumb_1014380, 1014380),
            new Card(R.drawable.card_1014480, 1014480), new Card(R.drawable.thumb_1014650, 1014650), new Card(R.drawable.thumb_1014730, 1014730), new Card(R.drawable.card_1015090_thumb, 1015090), new Card(R.drawable.card_1015190_thumb, 1015190),
            new Card(R.drawable.card_1015280_thumb, 1015280), new Card(R.drawable.card_1015300_thumb, 1015300), new Card(R.drawable.card_1015780_thumb, 1015780), new Card(R.drawable.card_1015800_thumb, 1015800), new Card(R.drawable.card_1016020_thumb, 1016020),
            new Card(R.drawable.card_1016040_thumb, 1016040), new Card(R.drawable.card_1016060_thumb, 1016060), new Card(R.drawable.card_1016070int_thumb, 1016070), new Card(R.drawable.card_1016240_thumb, 1016240), new Card(R.drawable.card_1016260_thumb, 1016260),
            new Card(R.drawable.card_1016270_thumb, 1016270), new Card(R.drawable.card_1016620_thumb, 1016620), new Card(R.drawable.card_1016640_thumb, 1016640), new Card(R.drawable.card_1016890_thumb, 1016890), new Card(R.drawable.card_1017010_thumb, 1017010),
            new Card(R.drawable.card_1017210_thumb, 1017210), new Card(R.drawable.card_1017460_thumb, 1017460), new Card(R.drawable.card_1017560_thumb, 1017560), new Card(R.drawable.card_1017580_thumb, 1017580), new Card(R.drawable.card_1017690_thumb, 1017690),
            new Card(R.drawable.card_1018050_thumb, 1018050), new Card(R.drawable.card_1018090_thumb, 1018090), new Card(R.drawable.card_1018140_thumb, 1018140), new Card(R.drawable.card_1018570_thumb, 1018570), new Card(R.drawable.card_1018670_thumb, 1018670),
            new Card(R.drawable.card_1019030_thumb, 1019030), new Card(R.drawable.card_1019270_thumb, 1019270), new Card(R.drawable.card_1019290_thumb, 1019290), new Card(R.drawable.card_1019350_thumb, 1019350), new Card(R.drawable.card_1019830_thumb, 1019830),
            new Card(R.drawable.thum_2000170_1, 2000170), new Card(R.drawable.thum_2000230_1, 2000230), new Card(R.drawable.thum_2000240_1, 2000240), new Card(R.drawable.thum_2000250_1, 2000250), new Card(R.drawable.card_1020270_thumb, 1020270),
            new Card(R.drawable.card_1020520_thumb, 1020520), new Card(R.drawable.card_1020120_thumb, 1020120), new Card(R.drawable.card_1020020_thumb, 1020020), new Card(R.drawable.card_1020000_thumb, 1020000)));

    public static final ArrayList<Card> DOKKANFESTPOOL = new ArrayList<>(Arrays.asList(new Card(R.drawable.thum_1002800_1, 1002800), new Card(R.drawable.card_1003070, 1003070), new Card(R.drawable.thum_1003240_1, 1003240), new Card(R.drawable.thum_1003300_1, 1003300), new Card(R.drawable.thum_1003320_1, 1003320),
            new Card(R.drawable.thum_1003770_1, 1003770), new Card(R.drawable.thum_1004220_1, 1004220), new Card(R.drawable.thum_1005270_1, 1005270), new Card(R.drawable.thumb_1005630, 1005630), new Card(R.drawable.card_1005990, 1005990), new Card(R.drawable.thum_1006410, 1006410),
            new Card(R.drawable.thum_1006680_1, 1006680), new Card(R.drawable.thumb_1007450, 1007450), new Card(R.drawable.thum_1007920, 1007920), new Card(R.drawable.card_1007930, 1007930), new Card(R.drawable.card_1008100, 1008100), new Card(R.drawable.thumb_1008410, 1008410),
            new Card(R.drawable.card_1008730, 1008730), new Card(R.drawable.card_1008810, 1008810), new Card(R.drawable.thum_1008840glb, 1008840), new Card(R.drawable.thum_1009080, 10090800), new Card(R.drawable.thum_1009320, 1009320), new Card(R.drawable.thum_1009340, 1009340),
            new Card(R.drawable.thum_1009420, 1009420), new Card(R.drawable.card_1009570, 1009570), new Card(R.drawable.card_1009600, 1009600), new Card(R.drawable.card_1009650, 1009650), new Card(R.drawable.thum_1009750, 1009750), new Card(R.drawable.thum_1009820, 1009820),
            new Card(R.drawable.thum_1010240, 1010240), new Card(R.drawable.card_1010840, 1010840), new Card(R.drawable.card_1010860, 1010860), new Card(R.drawable.card_1011050, 1011050), new Card(R.drawable.card_1011160, 1011160), new Card(R.drawable.card_1011290, 1011290),
            new Card(R.drawable.card_1011310, 1011310), new Card(R.drawable.thumb_1011770, 1011770), new Card(R.drawable.card_1011810, 1011810), new Card(R.drawable.card_1012020, 1012020), new Card(R.drawable.card_1012370, 1012370), new Card(R.drawable.thumb_1012580, 1012580), new Card(R.drawable.card_1012730, 1012730), new Card(R.drawable.card_1012750, 1012750),
            new Card(R.drawable.thumb_1013480, 1013480), new Card(R.drawable.card_1013560_thumb, 1013560), new Card(R.drawable.thumb_1013820, 1013280), new Card(R.drawable.thumb_1013850, 1013850), new Card(R.drawable.card_1014120, 1014120), new Card(R.drawable.thumb_1014810, 1014810),
            new Card(R.drawable.thumb_1014900, 1014900), new Card(R.drawable.card_1015040_thumb, 1015040), new Card(R.drawable.thumb_1015150, 1015150), new Card(R.drawable.card_1015360_thumb, 1015360), new Card(R.drawable.card_1015460_thumb, 1015460), new Card(R.drawable.card_1015660_thumb, 1015660),
            new Card(R.drawable.card_1015960_thumb, 1015960), new Card(R.drawable.card_1015990_thumb, 1015990), new Card(R.drawable.card_1016500_thumb, 1016500), new Card(R.drawable.card_1016560_thumb, 1016560), new Card(R.drawable.card_1017170_thumb, 1017170), new Card(R.drawable.card_1017480_thumb, 1017480),
            new Card(R.drawable.thum_2000640_1, 2000640), new Card(R.drawable.card_1018770_thumb, 1018770), new Card(R.drawable.card_1018110_thumb, 1018110), new Card(R.drawable.card_1018180_thumb, 1018180), new Card(R.drawable.thumb_1012880, 1012880), new Card(R.drawable.card_1018600_thumb, 1018600),
            new Card(R.drawable.card_1018630_thumb, 1018630), new Card(R.drawable.card_1018750_thumb, 1018750), new Card(R.drawable.card_1015760_thumb, 1015760), new Card(R.drawable.card_1017110_thumb, 1017110), new Card(R.drawable.card_1015740_thumb, 1015740), new Card(R.drawable.card_1017070_thumb, 1017070),
            new Card(R.drawable.card_1017880_thumb, 1017880), new Card(R.drawable.thumb_1012910, 1012910), new Card(R.drawable.card_1020350_thumb, 1020350), new Card(R.drawable.card_1018530_thumb, 1018530), new Card(R.drawable.card_1019330_thumb, 1019330), new Card(R.drawable.card_1018260_thumb, 1018260),
            new Card(R.drawable.card_1017610_thumb, 1017610), new Card(R.drawable.card_1019130_thumb, 1019130), new Card(R.drawable.card_1019970_thumb, 1019970), new Card(R.drawable.card_1016790_thumb, 1016790), new Card(R.drawable.card_1019620_thumb, 1019620), new Card(R.drawable.card_1019580_thumb, 1019580),
            new Card(R.drawable.card_1020080_thumb, 1020080), new Card(R.drawable.card_1019730_thumb, 1019730), new Card(R.drawable.card_1020440_thumb, 1020440), new Card(R.drawable.card_1020490_thumb, 1020490)));

    public static final ArrayList<Card> SUMMONABLELRPOOL = new ArrayList<>(Arrays.asList(new Card(R.drawable.thumb_1014920, 1014920), new Card(R.drawable.thumb_1014000, 1014000), new Card(R.drawable.thumb_1012140, 1012140), new Card(R.drawable.thum_1011620, 1011620), new Card(R.drawable.card_1020200_thumb, 1020200),
            new Card(R.drawable.card_1019800_thumb, 1019800), new Card(R.drawable.card_1019380_thumb, 1019380), new Card(R.drawable.card_1018980_thumb, 1018980), new Card(R.drawable.card_1018860_thumb, 1018860), new Card(R.drawable.card_1018470_thumb, 1018470), new Card(R.drawable.card_1018230_thumb, 1018230),
            new Card(R.drawable.card_1018010_thumb, 1018010), new Card(R.drawable.card_1016850_thumb, 1016850), new Card(R.drawable.card_1016820_thumb, 1016820), new Card(R.drawable.card_1016380, 1016380), new Card(R.drawable.card_1016350_thumb, 1016350), new Card(R.drawable.card_1015600_thumb, 1015600),
            new Card(R.drawable.card_1014950_thumb, 1014950), new Card(R.drawable.card_1013750, 1013750), new Card(R.drawable.card_1011120, 1011120), new Card(R.drawable.card_1010420, 1010420), new Card(R.drawable.card_1010150, 1010150), new Card(R.drawable.card_1010050, 1010050),
            new Card(R.drawable.card_1007830, 1007830), new Card(R.drawable.card_1005010_thumb, 1005010), new Card(R.drawable.thum_1011700_1, 1011700)));
    public DokkanBanner(int bannerImage, ArrayList<Card> featuredPool, ArrayList<Card> unfeaturedPool, String name) {
        image = bannerImage;
        featured = featuredPool;
        unfeatured = unfeaturedPool;
        this.name = name;
    }

    public static ArrayList<Card> customizePool(ArrayList<Integer> removeID, ArrayList<Integer> addID, ArrayList<Card> pool) {
        ArrayList<Card> result = new ArrayList<>();
        result.addAll(pool);
        if (removeID != null) {
            for (int item : removeID)
                for (Card card : result) {
                    if (item == card.getCardID()) {
                        result.remove(card);
                        break;
                    }
                }
        }
        if (addID != null) {
            for (int value : addID) {
                if (!result.contains(findCardById(value)))
                    result.add(findCardById((value)));
            }
        }
        return result;
    }

    public static ArrayList<Card> customizePool(ArrayList<Integer> removeID, ArrayList<Integer> addID, ArrayList<Card> pool, ArrayList<Integer> removeID2, ArrayList<Integer> addID2, ArrayList<Card> pool2) {
        ArrayList<Card> result1 = new ArrayList<>();
        result1.addAll(pool);
        ArrayList<Card> result2 = new ArrayList<>();
        result2.addAll(pool2);

        if (removeID != null) {
            for (int item : removeID)
                for (Card card : result1) {
                    if (item == card.getCardID()) {
                        result1.remove(card);
                        break;
                    }
                }
        }
        if (addID != null) {
            for (int value : addID) {
                if (!result1.contains(findCardById(value))) {
                    result1.add(findCardById((value)));
                }
            }
        }
        if (removeID2 != null) {
            for (int item : removeID2)
                for (Card card : result2) {
                    if (item == card.getCardID()) {
                        result2.remove(card);
                        break;
                    }
                }
        }
        if (addID2 != null) {
            for (int value : addID2) {
                if (!result2.contains(findCardById(value)))
                    result2.add(findCardById((value)));
            }
        }
        result1.addAll(result2);
        return result1;
    }

    public static Card findCardById(int id) {
        ArrayList<Card> pool = new ArrayList<>();
        pool.addAll(SUMMONABLELRPOOL);
        pool.addAll(DOKKANFESTPOOL);
        pool.addAll(NORMALPOOL);
        for (Card c : pool) {
            if (c.getCardID() == id)
                return c;
        }
        return null;
    }

    public static ArrayList<Card> findCardsById(ArrayList<Integer> ids) {
        ArrayList<Card> result = new ArrayList<>();
        ArrayList<Card> pool = new ArrayList<>();
        pool.addAll(SUMMONABLELRPOOL);
        pool.addAll(DOKKANFESTPOOL);
        pool.addAll(NORMALPOOL);
        for (Card c : pool) {
            if (ids.contains(c.getCardID()))
                result.add(c);
        }
        return result;
    }

    public Card[] multiSummon() {
        int roll;
        Random rng = new Random();
        boolean flip;
        Card[] results = new Card[10];
        for (int i = 0; i < 9; i++) {
            roll = rng.nextInt(100) + 1;
            if (roll >= 1 && roll <= 10) {
                flip = rng.nextBoolean();
                if (!flip)
                    results[i] = featured.get(rng.nextInt(featured.size()));
                else
                    results[i] = unfeatured.get(rng.nextInt(unfeatured.size()));
            } else if (roll > 10 && roll <= 70)
                results[i] = SR;
            else
                results[i] = RARE;
        }
        roll = rng.nextInt(100) + 1;
        if (roll >= 1 && roll <= 5)
            results[9] = featured.get(rng.nextInt(featured.size()));
        else
            results[9] = unfeatured.get(rng.nextInt(unfeatured.size()));
        return results;
    }

    public Card singleSummon() {
        int roll;
        Random rng = new Random();
        boolean flip;
        roll = rng.nextInt(100) + 1;
        if (roll >= 1 && roll <= 10) {
            flip = rng.nextBoolean();
            if (!flip)
                return featured.get(rng.nextInt(featured.size()));
            else
                return unfeatured.get(rng.nextInt(unfeatured.size()));
        } else if (roll > 10 && roll <= 70)
            return SR;
        else
            return RARE;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
