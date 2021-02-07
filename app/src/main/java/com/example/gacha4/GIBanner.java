package com.example.gacha4;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GIBanner {
    final int threeStars = R.drawable.gi_3stars, fourStars = R.drawable.gi_4stars, fiveStars = R.drawable.gi_5stars, image;
    private final boolean isWeaponBanner;
    private int drawCount = 0;
    private static final Random rng = new Random();
    private boolean firstFiveStar = false;
    private final ArrayList<Card> fiveStarBanner, fourStarBanner, fiveStarPool, fourStarPool, threeStarPool;

    public GIBanner(int image, ArrayList<Card> fiveStarBanner, ArrayList<Card> fourStarBanner, ArrayList<Card> fiveStarPool, ArrayList<Card> fourStarPool, ArrayList<Card> threeStarPool, boolean isWeaponBanner){
        this.image = image;
        this.fiveStarBanner = fiveStarBanner;
        this.fourStarBanner = fourStarBanner;
        this.fiveStarPool = fiveStarPool;
        this.fourStarPool = fourStarPool;
        this.threeStarPool = threeStarPool;
        this.isWeaponBanner = isWeaponBanner;
    }
    public static final ArrayList<Card> CHARACTERS = new ArrayList<>(Arrays.asList(new Card(R.drawable.gi_character_albedo_thumb, 1, 5),
            new Card(R.drawable.gi_character_amber_thumb, 2, 4), new Card(R.drawable.gi_character_ayaka_thumb, 3, 5),
            new Card(R.drawable.gi_character_barbara_thumb, 4, 4), new Card(R.drawable.gi_character_beidou_thumb, 5, 4),
            new Card(R.drawable.gi_character_bennett_thumb, 6, 4), new Card(R.drawable.gi_character_chongyun_thumb, 7, 4),
            new Card(R.drawable.gi_character_diluc_thumb, 8, 5), new Card(R.drawable.gi_character_diona_thumb, 9, 4),
            new Card(R.drawable.gi_character_fischl_thumb, 10, 4), new Card(R.drawable.gi_character_ganyu_thumb, 11, 5),
            new Card(R.drawable.gi_character_jean_thumb, 12, 5), new Card(R.drawable.gi_character_kaeya_thumb, 13, 4),
            new Card(R.drawable.gi_character_keqing_thumb, 14, 5), new Card(R.drawable.gi_character_klee_thumb, 15, 5),
            new Card(R.drawable.gi_character_lisa_thumb, 16, 4), new Card(R.drawable.gi_character_mona_thumb, 17, 5),
            new Card(R.drawable.gi_character_ningguang_thumb, 18, 4), new Card(R.drawable.gi_character_noelle_thumb, 19, 4),
            new Card(R.drawable.gi_character_qiqi_thumb, 20, 5), new Card(R.drawable.gi_character_razor_thumb, 21, 4),
            new Card(R.drawable.gi_character_sucrose_thumb, 22, 4), new Card(R.drawable.gi_character_tartaglia_thumb, 23, 5),
            new Card(R.drawable.gi_character_venti_thumb, 24, 5), new Card(R.drawable.gi_character_xiangling_thumb, 25, 4),
            new Card(R.drawable.gi_character_xiao_thumb, 26, 5), new Card(R.drawable.gi_character_xingqiu_thumb, 27, 4),
            new Card(R.drawable.gi_character_xinyan_thumb, 28, 4), new Card(R.drawable.gi_character_zhongli_thumb, 29, 5)));

    public static final ArrayList<Card> SWORDS = new ArrayList<>(Arrays.asList(new Card(R.drawable.gi_weapon_aquila_favonia,113,5),
            new Card(R.drawable.gi_weapon_blackcliff_longsword,114,4), new Card(R.drawable.gi_weapon_cool_steel,115,3),
            new Card(R.drawable.gi_weapon_dark_iron_sword,116,3), new Card(R.drawable.gi_weapon_favonius_sword,117,4),
            new Card(R.drawable.gi_weapon_festering_desire,118,4), new Card(R.drawable.gi_weapon_fillet_blade,119,3),
            new Card(R.drawable.gi_weapon_harbinger_of_dawn,120,3), new Card(R.drawable.gi_weapon_iron_sting,121,4),
            new Card(R.drawable.gi_weapon_lions_roar,122,4), new Card(R.drawable.gi_weapon_primordial_jade_winged_spear,123,5),
            new Card(R.drawable.gi_weapon_prototype_rancour,30,4), new Card(R.drawable.gi_weapon_royal_longsword,31,4),
            new Card(R.drawable.gi_weapon_sacrificial_sword,32,4), new Card(R.drawable.gi_weapon_skyrider_sword,33,3),
            new Card(R.drawable.gi_weapon_skyward_blade,34,5), new Card(R.drawable.gi_weapon_summit_shaper,35,5),
            new Card(R.drawable.gi_weapon_sword_of_descension,36,4), new Card(R.drawable.gi_weapon_the_alley_flash,37,4),
            new Card(R.drawable.gi_weapon_the_black_sword,38,4), new Card(R.drawable.gi_weapon_the_flute,39,4),
            new Card(R.drawable.gi_weapon_travelers_handy_sword,40,3)));

    public static final ArrayList<Card> CLAYMORES = new ArrayList<>(Arrays.asList(new Card(R.drawable.gi_weapon_blackcliff_slasher,41,4),
            new Card(R.drawable.gi_weapon_bloodtainted_greatsword,42,3), new Card(R.drawable.gi_weapon_debate_club,43,3),
            new Card(R.drawable.gi_weapon_favonius_greatsword,44,4), new Card(R.drawable.gi_weapon_ferrous_shadow,45,3),
            new Card(R.drawable.gi_weapon_lithic_blade,46,4), new Card(R.drawable.gi_weapon_prototype_archaic,47,4),
            new Card(R.drawable.gi_weapon_quartz,48,3), new Card(R.drawable.gi_weapon_rainslasher,49,4),
            new Card(R.drawable.gi_weapon_royal_greatsword,50,4), new Card(R.drawable.gi_weapon_sacrificial_greatsword,51,4),
            new Card(R.drawable.gi_weapon_serpent_spine,52,4), new Card(R.drawable.gi_weapon_skyrider_greatsword,53,3),
            new Card(R.drawable.gi_weapon_skyward_pride,54,5), new Card(R.drawable.gi_weapon_snow_tombed_starsilver,55,4),
            new Card(R.drawable.gi_weapon_the_bell,56,4), new Card(R.drawable.gi_weapon_the_unforged,57,5),
            new Card(R.drawable.gi_weapon_white_iron_greatsword,58,3), new Card(R.drawable.gi_weapon_whiteblind,59,4),
            new Card(R.drawable.gi_weapon_wolfs_gravestone,60,5)));

    public static final ArrayList<Card> POLEARMS = new ArrayList<>(Arrays.asList(new Card(R.drawable.gi_weapon_black_tassel,61,3),
            new Card(R.drawable.gi_weapon_blackcliff_pole,62,4), new Card(R.drawable.gi_weapon_crescent_pike,63,4),
            new Card(R.drawable.gi_weapon_deathmatch,64,4), new Card(R.drawable.gi_weapon_dragonspine_spear,65,4),
            new Card(R.drawable.gi_weapon_favonius_lance,66,4), new Card(R.drawable.gi_weapon_halberd,67,3),
            new Card(R.drawable.gi_weapon_lithic_spear,68,4), new Card(R.drawable.gi_weapon_primordial_jade_winged_spear,69,5),
            new Card(R.drawable.gi_weapon_prototype_starglitter,70,4), new Card(R.drawable.gi_weapon_royal_spear,71,4),
            new Card(R.drawable.gi_weapon_skyward_spine,72,5),new Card(R.drawable.gi_weapon_staff_of_homa,73,5) ,
            new Card(R.drawable.gi_weapon_vortex_vanquisher,74,5), new Card(R.drawable.gi_weapon_white_tassel,75,3)));

    public static final ArrayList<Card> CATALYSTS = new ArrayList<>(Arrays.asList(new Card(R.drawable.gi_weapon_amber_catalyst,76,3),
            new Card(R.drawable.gi_weapon_blackcliff_agate,77,4), new Card(R.drawable.gi_weapon_emerald_orb,78,3),
            new Card(R.drawable.gi_weapon_eye_of_perception,79,4), new Card(R.drawable.gi_weapon_favonius_codex,80,4),
            new Card(R.drawable.gi_weapon_frostbearer,81,4), new Card(R.drawable.gi_weapon_lost_prayer_to_the_sacred_winds,82,5),
            new Card(R.drawable.gi_weapon_magic_guide,83,3), new Card(R.drawable.gi_weapon_mappa_mare,84,4),
            new Card(R.drawable.gi_weapon_memory_of_dust,85,5), new Card(R.drawable.gi_weapon_otherworldly_story,86,3),
            new Card(R.drawable.gi_weapon_prototype_amber,87,4), new Card(R.drawable.gi_weapon_royal_grimoire,88,4),
            new Card(R.drawable.gi_weapon_sacrificial_fragments,89,4), new Card(R.drawable.gi_weapon_skyward_atlas,90,5),
            new Card(R.drawable.gi_weapon_solar_pearl,91,4), new Card(R.drawable.gi_weapon_the_widsith,92,4),
            new Card(R.drawable.gi_weapon_thrilling_tales_of_dragon_slayers,93,3), new Card(R.drawable.gi_weapon_twin_nephrite,93,3),
            new Card(R.drawable.gi_weapon_wine_and_song,94,4)));

    public static final ArrayList<Card> BOWS = new ArrayList<>(Arrays.asList(new Card(R.drawable.gi_weapon_amos_bow,95,5),
            new Card(R.drawable.gi_weapon_blackcliff_warbow,96,4),
            new Card(R.drawable.gi_weapon_compound_bow,97,4), new Card(R.drawable.gi_weapon_ebony_bow,98,3),
            new Card(R.drawable.gi_weapon_favonius_warbow,99,4), new Card(R.drawable.gi_weapon_messenger,100,3),
            new Card(R.drawable.gi_weapon_prototype_crescent,101,4), new Card(R.drawable.gi_weapon_raven_bow,102,3),
            new Card(R.drawable.gi_weapon_recurve_bow,103,3), new Card(R.drawable.gi_weapon_royal_bow,104,4),
            new Card(R.drawable.gi_weapon_rust,105,4), new Card(R.drawable.gi_weapon_sacrificial_bow,106,4),
            new Card(R.drawable.gi_weapon_sharpshooters_oath,107,3), new Card(R.drawable.gi_weapon_skyward_harp,108,5),
            new Card(R.drawable.gi_weapon_slingshot,109,3), new Card(R.drawable.gi_weapon_the_stringless,110,4),
            new Card(R.drawable.gi_weapon_the_viridescent_hunt,111,4), new Card(R.drawable.gi_weapon_alley_hunter,112,4)));

    public static ArrayList<Card> findCardsById(ArrayList<Integer> ids){
        ArrayList<Card> pool = null;
        pool.addAll(CHARACTERS);
        pool.addAll(BOWS);
        pool.addAll(SWORDS);
        pool.addAll(CLAYMORES);
        pool.addAll(POLEARMS);
        pool.addAll(CATALYSTS);

        ArrayList<Card> result = null;
        for(int id: ids) {
            for (Card c : pool) {
                if (c.getCardID() == id) {
                    result.add(c);
                    break;
                }
            }
        }
        return result;
    }


    public int getImage(){return image;}
}
