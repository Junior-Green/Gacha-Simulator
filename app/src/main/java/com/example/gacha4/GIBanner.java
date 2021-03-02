package com.example.gacha4;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GIBanner {
    final static int THREE_STAR = R.drawable.gi_3stars, FOUR_STAR = R.drawable.gi_4stars, FIVE_STAR = R.drawable.gi_5stars;
    final int bannerImage;
    private final boolean isWeaponBanner;
    private static final DecimalFormat df = new DecimalFormat("#.###");
    private int fourCount = 0, fiveCount = 0, firstFive = 0, firstFour = 0; //0 = Boosted chance, 1 = guaranteed if not 0, 2 = base
    private final Random rng = new Random();
    public final ArrayList<Card> fiveStarBanner, fourStarBanner, fiveStarPool, fourStarPool, threeStarPool;
    private final ArrayList<Card> weaponPool = new ArrayList<>();
    private final ArrayList<Card> characterPool = new ArrayList<>();

    public GIBanner(int image, ArrayList<Card> fiveStarBanner, ArrayList<Card> fourStarBanner, ArrayList<Card> fiveStarPool, ArrayList<Card> fourStarPool, ArrayList<Card> threeStarPool, boolean isWeaponBanner) {
        this.bannerImage = image;
        this.fiveStarBanner = fiveStarBanner;
        this.fourStarBanner = fourStarBanner;
        this.fiveStarPool = fiveStarPool;
        this.fourStarPool = fourStarPool;
        this.threeStarPool = threeStarPool;
        this.isWeaponBanner = isWeaponBanner;
        for (Card c : fourStarPool) {
            if (WEAPONS.contains(c))
                weaponPool.add(c);
            else
                characterPool.add(c);
        }
        for (Card c : fourStarBanner) {
            if (WEAPONS.contains(c))
                weaponPool.add(c);
            else
                characterPool.add(c);
        }
    }

    public static final ArrayList<Card> CHARACTERS = new ArrayList<>(Arrays.asList(new Card(R.drawable.gi_character_albedo_thumb, 1, 5, "Albedo"),
            new Card(R.drawable.gi_character_amber_thumb, 2, 4, "Amber"), new Card(R.drawable.gi_character_ayaka_thumb, 3, 5, "Ayaka"),
            new Card(R.drawable.gi_character_barbara_thumb, 4, 4, "Barbara"), new Card(R.drawable.gi_character_beidou_thumb, 5, 4, "Beidou"),
            new Card(R.drawable.gi_character_bennett_thumb, 6, 4, "Bennett"), new Card(R.drawable.gi_character_chongyun_thumb, 7, 4, "Chongyun"),
            new Card(R.drawable.gi_character_diluc_thumb, 8, 5, "Diluc"), new Card(R.drawable.gi_character_diona_thumb, 9, 4, "Diona"),
            new Card(R.drawable.gi_character_fischl_thumb, 10, 4, "Fischl"), new Card(R.drawable.gi_character_ganyu_thumb, 11, 5, "Ganyu"),
            new Card(R.drawable.gi_character_jean_thumb, 12, 5, "Jean"), new Card(R.drawable.gi_character_kaeya_thumb, 13, 4, "Kaeya"),
            new Card(R.drawable.gi_character_keqing_thumb, 14, 5, "Kequing"), new Card(R.drawable.gi_character_klee_thumb, 15, 5, "Klee"),
            new Card(R.drawable.gi_character_lisa_thumb, 16, 4, "Lisa"), new Card(R.drawable.gi_character_mona_thumb, 17, 5, "Mona"),
            new Card(R.drawable.gi_character_ningguang_thumb, 18, 4, "Ningguang"), new Card(R.drawable.gi_character_noelle_thumb, 19, 4, "Noelle"),
            new Card(R.drawable.gi_character_qiqi_thumb, 20, 5, "Qiqi"), new Card(R.drawable.gi_character_razor_thumb, 21, 4, "Razor"),
            new Card(R.drawable.gi_character_sucrose_thumb, 22, 4, "Sucrose"), new Card(R.drawable.gi_character_tartaglia_thumb, 23, 5, "Tartaglia"),
            new Card(R.drawable.gi_character_venti_thumb, 24, 5, "Venti"), new Card(R.drawable.gi_character_xiangling_thumb, 25, 4, "Xiangling"),
            new Card(R.drawable.gi_character_xiao_thumb, 26, 5, "Xiao"), new Card(R.drawable.gi_character_xingqiu_thumb, 27, 4, "Xingqiu"),
            new Card(R.drawable.gi_character_xinyan_thumb, 28, 4, "Xinyan"), new Card(R.drawable.gi_character_zhongli_thumb, 29, 5, "Zhongli")));

    public static final ArrayList<Card> WEAPONS = new ArrayList<>(Arrays.asList(new Card(R.drawable.gi_weapon_aquila_favonia, 113, 5, "Aquila Favonia"),
            new Card(R.drawable.gi_weapon_blackcliff_longsword, 114, 4, "Blackcliff Longsword"), new Card(R.drawable.gi_weapon_cool_steel, 115, 3, "Cool Steel"),
            new Card(R.drawable.gi_weapon_dark_iron_sword, 116, 3, "Iron Sword"), new Card(R.drawable.gi_weapon_favonius_sword, 117, 4, "Favonius Sword"),
            new Card(R.drawable.gi_weapon_festering_desire, 118, 4, "Festering Desire"), new Card(R.drawable.gi_weapon_fillet_blade, 119, 3, "Filler Blade"),
            new Card(R.drawable.gi_weapon_harbinger_of_dawn, 120, 3, "Harbinger of Dawn"), new Card(R.drawable.gi_weapon_iron_sting, 121, 4, "Iron Sting"),
            new Card(R.drawable.gi_weapon_lions_roar, 122, 4, "Lion's Roar"), new Card(R.drawable.gi_weapon_primordial_jade_winged_spear, 123, 5, "Primordial Jade Winged Spear"),
            new Card(R.drawable.gi_weapon_prototype_rancour, 30, 4, "Prototype Rancour"), new Card(R.drawable.gi_weapon_royal_longsword, 31, 4, "Royal Longsword"),
            new Card(R.drawable.gi_weapon_sacrificial_sword, 32, 4, "Sacrificial Sword"), new Card(R.drawable.gi_weapon_skyrider_sword, 33, 3, "Skyrider Sword"),
            new Card(R.drawable.gi_weapon_skyward_blade, 34, 5, "Skyward Blade"), new Card(R.drawable.gi_weapon_summit_shaper, 35, 5, "Summit Shaper"),
            new Card(R.drawable.gi_weapon_sword_of_descension, 36, 4, "Sword of Descension"), new Card(R.drawable.gi_weapon_the_alley_flash, 37, 4, "The Ally Flash"),
            new Card(R.drawable.gi_weapon_the_black_sword, 38, 4, "The Black Sword"), new Card(R.drawable.gi_weapon_the_flute, 39, 4, "The Flute"),
            new Card(R.drawable.gi_weapon_travelers_handy_sword, 40, 3, "Traveler's Handy Sword"), new Card(R.drawable.gi_weapon_blackcliff_slasher, 41, 4, "Blackcliff Slasher"),
            new Card(R.drawable.gi_weapon_bloodtainted_greatsword, 42, 3, "Bloodtainted Greatsword"), new Card(R.drawable.gi_weapon_debate_club, 43, 3, "Debate Club"),
            new Card(R.drawable.gi_weapon_favonius_greatsword, 44, 4, "Favonius Greatsword"), new Card(R.drawable.gi_weapon_ferrous_shadow, 45, 3, "Ferrous Shadow"),
            new Card(R.drawable.gi_weapon_lithic_blade, 46, 4, "Lithic Blade"), new Card(R.drawable.gi_weapon_prototype_archaic, 47, 4, "Prototype Archaic"),
            new Card(R.drawable.gi_weapon_quartz, 48, 3, "Quartz"), new Card(R.drawable.gi_weapon_rainslasher, 49, 4, "Rain slasher"),
            new Card(R.drawable.gi_weapon_royal_greatsword, 50, 4, "Royal Greatsword"), new Card(R.drawable.gi_weapon_sacrificial_greatsword, 51, 4, "Sacrificial Greatsword"),
            new Card(R.drawable.gi_weapon_serpent_spine, 52, 4, "Serpent Spine"), new Card(R.drawable.gi_weapon_skyrider_greatsword, 53, 3, "Skyrider Greatsword"),
            new Card(R.drawable.gi_weapon_skyward_pride, 54, 5, "Skyward Pride"), new Card(R.drawable.gi_weapon_snow_tombed_starsilver, 55, 4, "Snow Tombed Star Silver"),
            new Card(R.drawable.gi_weapon_the_bell, 56, 4, "The Bell"), new Card(R.drawable.gi_weapon_the_unforged, 57, 5, "The Unforged"),
            new Card(R.drawable.gi_weapon_white_iron_greatsword, 58, 3, "Iron Greatsword"), new Card(R.drawable.gi_weapon_whiteblind, 59, 4, "White Blind"),
            new Card(R.drawable.gi_weapon_wolfs_gravestone, 60, 5, "Wolf's Gravestone"), new Card(R.drawable.gi_weapon_black_tassel, 61, 3, "Black Tassel"),
            new Card(R.drawable.gi_weapon_blackcliff_pole, 62, 4, "Blackcliff Pole"), new Card(R.drawable.gi_weapon_crescent_pike, 63, 4, "Crescent Pike"),
            new Card(R.drawable.gi_weapon_deathmatch, 64, 4, "Deathmatch"), new Card(R.drawable.gi_weapon_dragonspine_spear, 65, 4, "Dragonspine Spear"),
            new Card(R.drawable.gi_weapon_favonius_lance, 66, 4, "Favonius Lance"), new Card(R.drawable.gi_weapon_halberd, 67, 3, "Halberd"),
            new Card(R.drawable.gi_weapon_lithic_spear, 68, 4, "Lithic Spear"), new Card(R.drawable.gi_weapon_primordial_jade_winged_spear, 69, 5, "Primordial Jade Winged Spear"),
            new Card(R.drawable.gi_weapon_prototype_starglitter, 70, 4, "Prototype Starglitter"), new Card(R.drawable.gi_weapon_royal_spear, 71, 4, "Royal Spear"),
            new Card(R.drawable.gi_weapon_skyward_spine, 72, 5, "Skyward Spine"), new Card(R.drawable.gi_weapon_staff_of_homa, 73, 5, "Staff of Homa"),
            new Card(R.drawable.gi_weapon_vortex_vanquisher, 74, 5, "Vortex Vanquisher"), new Card(R.drawable.gi_weapon_white_tassel, 75, 3, "White Tassel"),
            new Card(R.drawable.gi_weapon_amber_catalyst, 76, 3, "Amber Catalyst"), new Card(R.drawable.gi_weapon_wine_and_song, 94, 4, "Wine and Song"),
            new Card(R.drawable.gi_weapon_blackcliff_agate, 77, 4, "Blackcliff Agate"), new Card(R.drawable.gi_weapon_emerald_orb, 78, 3, "Emerald Orb"),
            new Card(R.drawable.gi_weapon_eye_of_perception, 79, 4, "Eye of Perception"), new Card(R.drawable.gi_weapon_favonius_codex, 80, 4, "Favonius Codex"),
            new Card(R.drawable.gi_weapon_frostbearer, 81, 4, "Frostbearer"), new Card(R.drawable.gi_weapon_lost_prayer_to_the_sacred_winds, 82, 5, "Lost to the Sacred Winds"),
            new Card(R.drawable.gi_weapon_magic_guide, 83, 3, "Magic Guide"), new Card(R.drawable.gi_weapon_mappa_mare, 84, 4, "Mappa Mare"),
            new Card(R.drawable.gi_weapon_memory_of_dust, 85, 5, "Memory of Dust"), new Card(R.drawable.gi_weapon_otherworldly_story, 86, 3, "Otherwordly Story"),
            new Card(R.drawable.gi_weapon_prototype_amber, 87, 4, "Prototype Amber"), new Card(R.drawable.gi_weapon_royal_grimoire, 88, 4, "Royal Grimoire"),
            new Card(R.drawable.gi_weapon_sacrificial_fragments, 89, 4, "Sacrificial Fragments"), new Card(R.drawable.gi_weapon_skyward_atlas, 90, 5, "Skyward Atlas"),
            new Card(R.drawable.gi_weapon_solar_pearl, 91, 4, "Solar Pearl"), new Card(R.drawable.gi_weapon_the_widsith, 92, 4, "The Widsith"),
            new Card(R.drawable.gi_weapon_thrilling_tales_of_dragon_slayers, 93, 3, "Thrilling Tales of Dragon Slayers"), new Card(R.drawable.gi_weapon_twin_nephrite, 124, 3, "Twin Nephrite"),
            new Card(R.drawable.gi_weapon_wine_and_song, 94, 4, "Wine and Song"), new Card(R.drawable.gi_weapon_amos_bow, 95, 5, "Amos Bow"),
            new Card(R.drawable.gi_weapon_blackcliff_warbow, 96, 4, "Blackcliff Warbow"), new Card(R.drawable.gi_weapon_compound_bow, 97, 4, "Compound Bow"), new Card(R.drawable.gi_weapon_ebony_bow, 98, 3, "Ebony Bow"),
            new Card(R.drawable.gi_weapon_favonius_warbow, 99, 4, "Favonius Warbow"), new Card(R.drawable.gi_weapon_messenger, 100, 3, "Messenger"),
            new Card(R.drawable.gi_weapon_prototype_crescent, 101, 4, "Prototype Crescent"), new Card(R.drawable.gi_weapon_raven_bow, 102, 3, "Raven Bow"),
            new Card(R.drawable.gi_weapon_recurve_bow, 103, 3, "Recurve Bow"), new Card(R.drawable.gi_weapon_royal_bow, 104, 4, "Royal Bow"),
            new Card(R.drawable.gi_weapon_rust, 105, 4, "Rust"), new Card(R.drawable.gi_weapon_sacrificial_bow, 106, 4, "Sacrificial Bow"),
            new Card(R.drawable.gi_weapon_sharpshooters_oath, 107, 3, "Sharpshooters Oath"), new Card(R.drawable.gi_weapon_skyward_harp, 108, 5, "Skyward Harp"),
            new Card(R.drawable.gi_weapon_slingshot, 109, 3, "Slingshot"), new Card(R.drawable.gi_weapon_the_stringless, 110, 4, "The Stringless"),
            new Card(R.drawable.gi_weapon_the_viridescent_hunt, 111, 4, "The Viridescent Hunt"), new Card(R.drawable.gi_weapon_alley_hunter, 112, 4, "Alley Hunter"),
            new Card(R.drawable.gi_weapon_dragons_bane, 125, 4, "Dragon's Bane")));

    public static ArrayList<Card> findCardsById(ArrayList<Integer> ids) {
        ArrayList<Card> pool = new ArrayList<>();
        pool.addAll(CHARACTERS);
        pool.addAll(WEAPONS);

        ArrayList<Card> result = new ArrayList<>();
        for (int id : ids) {
            for (Card c : pool) {
                if (c.getCardID() == id) {
                    result.add(c);
                    break;
                }
            }
        }
        return result;
    }

    public Card[] multiSummon() {
        Card[] results = new Card[10];
        if (isWeaponBanner) {
            for (int i = 0; i < 10; i++) {
                fourCount++;
                fiveCount++;
                if (fourCount == 10) {
                    float num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                    if (num <= 99.3) {
                        switch (firstFour) {
                            case 0:
                                num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                                if (num <= 75) {
                                    results[i] = fourStarBanner.get(rng.nextInt(fourStarBanner.size()));
                                    firstFour = 2;
                                } else {
                                    results[i] = fourStarPool.get(rng.nextInt(fourStarPool.size()));
                                    firstFour = 1;
                                }
                                break;
                            case 1:
                                results[i] = fourStarBanner.get(rng.nextInt(fourStarBanner.size()));
                                firstFour = 2;
                                break;
                            case 2:
                                num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                                if (num <= 50)
                                    results[i] = weaponPool.get(rng.nextInt(weaponPool.size()));
                                else
                                    results[i] = characterPool.get(rng.nextInt(characterPool.size()));
                        }
                    } else {
                        switch (firstFive) {
                            case 0:
                                num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                                if (num <= 75) {
                                    results[i] = fiveStarBanner.get(rng.nextInt(fiveStarBanner.size()));
                                    firstFive = 2;
                                } else {
                                    results[i] = fiveStarPool.get(rng.nextInt(fiveStarPool.size()));
                                    firstFive = 1;
                                }
                                break;
                            case 1:
                                results[i] = fiveStarBanner.get(rng.nextInt(fiveStarBanner.size()));
                                firstFive = 2;
                                break;
                            case 2:
                                ArrayList<Card> v = new ArrayList<>();
                                v.addAll(fiveStarBanner);
                                v.addAll(fiveStarPool);
                                results[i] = v.get(rng.nextInt(v.size()));
                        }
                        fiveCount = 0;
                    }
                    fourCount = 0;
                } else if (fiveCount == 80) {
                    switch (firstFive) {
                        case 0:
                            float num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                            if (num <= 75) {
                                results[i] = fiveStarBanner.get(rng.nextInt(fiveStarBanner.size()));
                                firstFive = 2;
                            } else {
                                results[i] = fiveStarPool.get(rng.nextInt(fiveStarPool.size()));
                                firstFive = 1;
                            }
                            break;
                        case 1:
                            results[i] = fiveStarBanner.get(rng.nextInt(fiveStarBanner.size()));
                            firstFive = 2;
                            break;
                        case 2:
                            ArrayList<Card> v = new ArrayList<>();
                            v.addAll(fiveStarBanner);
                            v.addAll(fiveStarPool);
                            results[i] = v.get(rng.nextInt(v.size()));
                    }
                    fiveCount = 0;
                } else {
                    float num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                    if (num >= 0 && num <= 0.700) {
                        switch (firstFive) {
                            case 0:
                                num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                                if (num <= 75) {
                                    results[i] = fiveStarBanner.get(rng.nextInt(fiveStarBanner.size()));
                                    firstFive = 2;
                                } else {
                                    results[i] = fiveStarPool.get(rng.nextInt(fiveStarPool.size()));
                                    firstFive = 1;
                                }
                                break;
                            case 1:
                                results[i] = fiveStarBanner.get(rng.nextInt(fiveStarBanner.size()));
                                firstFive = 2;
                                break;
                            case 2:
                                ArrayList<Card> v = new ArrayList<>();
                                v.addAll(fiveStarBanner);
                                v.addAll(fiveStarPool);
                                results[i] = v.get(rng.nextInt(v.size()));
                        }
                        fiveCount = 0;
                    } else if (num > 0.700 && num <= 6.700) {
                        switch (firstFour) {
                            case 0:
                                num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                                if (num <= 75) {
                                    results[i] = fourStarBanner.get(rng.nextInt(fourStarBanner.size()));
                                    firstFour = 2;
                                } else {
                                    results[i] = fourStarPool.get(rng.nextInt(fourStarPool.size()));
                                    firstFour = 1;
                                }
                                break;
                            case 1:
                                results[i] = fourStarBanner.get(rng.nextInt(fourStarBanner.size()));
                                firstFour = 2;
                                break;
                            case 2:
                                num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                                if (num <= 50)
                                    results[i] = weaponPool.get(rng.nextInt(weaponPool.size()));
                                else
                                    results[i] = characterPool.get(rng.nextInt(characterPool.size()));
                        }
                        fourCount = 0;
                    } else {
                        results[i] = threeStarPool.get(rng.nextInt(threeStarPool.size()));
                    }
                }
            }
        } else {
            for (int i = 0; i < 10; i++) {
                fourCount++;
                fiveCount++;
                if (fourCount == 10) {
                    float num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                    if (num <= 99.4) {
                        switch (firstFour) {
                            case 0:
                                num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                                if (num <= 50) {
                                    results[i] = fourStarBanner.get(rng.nextInt(fourStarBanner.size()));
                                    firstFour = 2;
                                } else {
                                    results[i] = fourStarPool.get(rng.nextInt(fourStarPool.size()));
                                    firstFour = 1;
                                }
                                break;
                            case 1:
                                results[i] = fourStarBanner.get(rng.nextInt(fourStarBanner.size()));
                                firstFour = 2;
                                break;
                            case 2:
                                num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                                if (num <= 50)
                                    results[i] = weaponPool.get(rng.nextInt(weaponPool.size()));
                                else
                                    results[i] = characterPool.get(rng.nextInt(characterPool.size()));
                        }
                    } else {
                        switch (firstFive) {
                            case 0:
                                num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                                if (num <= 50) {
                                    results[i] = fiveStarBanner.get(rng.nextInt(fiveStarBanner.size()));
                                    firstFive = 2;
                                } else {
                                    results[i] = fiveStarPool.get(rng.nextInt(fiveStarPool.size()));
                                    firstFive = 1;
                                }
                                break;
                            case 1:
                                results[i] = fiveStarBanner.get(rng.nextInt(fiveStarBanner.size()));
                                firstFive = 2;
                                break;
                            case 2:
                                ArrayList<Card> v = new ArrayList<>();
                                v.addAll(fiveStarBanner);
                                v.addAll(fiveStarPool);
                                results[i] = v.get(rng.nextInt(v.size()));
                        }
                        fiveCount = 0;
                    }
                    fourCount = 0;
                } else if (fiveCount == 90) {
                    switch (firstFive) {
                        case 0:
                            float num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                            if (num <= 50) {
                                results[i] = fiveStarBanner.get(rng.nextInt(fiveStarBanner.size()));
                                firstFive = 2;
                            } else {
                                results[i] = fiveStarPool.get(rng.nextInt(fiveStarPool.size()));
                                firstFive = 1;
                            }
                            break;
                        case 1:
                            results[i] = fiveStarBanner.get(rng.nextInt(fiveStarBanner.size()));
                            firstFive = 2;
                            break;
                        case 2:
                            ArrayList<Card> v = new ArrayList<>();
                            v.addAll(fiveStarBanner);
                            v.addAll(fiveStarPool);
                            results[i] = v.get(rng.nextInt(v.size()));
                    }
                    fiveCount = 0;
                } else {
                    float num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                    if (num >= 0 && num <= 0.600) {
                        switch (firstFive) {
                            case 0:
                                num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                                if (num <= 50) {
                                    results[i] = fiveStarBanner.get(rng.nextInt(fiveStarBanner.size()));
                                    firstFive = 2;
                                } else {
                                    results[i] = fiveStarPool.get(rng.nextInt(fiveStarPool.size()));
                                    firstFive = 1;
                                }
                                break;
                            case 1:
                                results[i] = fiveStarBanner.get(rng.nextInt(fiveStarBanner.size()));
                                firstFive = 2;
                                break;
                            case 2:
                                ArrayList<Card> v = new ArrayList<>();
                                v.addAll(fiveStarBanner);
                                v.addAll(fiveStarPool);
                                results[i] = v.get(rng.nextInt(v.size()));
                        }
                        fiveCount = 0;
                    } else if (num > 0.600 && num <= 5.700) {
                        switch (firstFour) {
                            case 0:
                                num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                                if (num <= 50) {
                                    results[i] = fourStarBanner.get(rng.nextInt(fourStarBanner.size()));
                                    firstFour = 2;
                                } else {
                                    results[i] = fourStarPool.get(rng.nextInt(fourStarPool.size()));
                                    firstFour = 1;
                                }
                                break;
                            case 1:
                                results[i] = fourStarBanner.get(rng.nextInt(fourStarBanner.size()));
                                firstFour = 2;
                                break;
                            case 2:
                                num = Float.parseFloat(df.format(rng.nextFloat() * 100));
                                if (num <= 50)
                                    results[i] = weaponPool.get(rng.nextInt(weaponPool.size()));
                                else
                                    results[i] = characterPool.get(rng.nextInt(characterPool.size()));
                        }
                        fourCount = 0;
                    } else {
                        results[i] = threeStarPool.get(rng.nextInt(threeStarPool.size()));
                    }
                }
            }
        }
        return results;
    }

    public int getImage() {
        return bannerImage;
    }
}
