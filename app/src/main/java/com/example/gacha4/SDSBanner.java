package com.example.gacha4;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SDSBanner {
    int image;
    private static final Random rng = new Random();
    public ArrayList<Card> banner, rateUp;
    public static final Card RARE = new Card(R.drawable.sds_rare,0);
    public static final Card SR = new Card(R.drawable.sds_sr,0);
    public static final ArrayList<Card> SSRS = new ArrayList<>(Arrays.asList(new Card(R.drawable.sds_a_new_adventure_elizabeth,1), new Card(R.drawable.sds_ale_collector_ban,2),
            new Card(R.drawable.sds_bringer_of_disaster_lillia,3), new Card(R.drawable.sds_creation_diane,4), new Card(R.drawable.sds_deathbringer_mono,5),
            new Card(R.drawable.sds_destined_heir_arthur,6), new Card(R.drawable.sds_dungeon_raider_shin,7), new Card(R.drawable.sds_earthshaker_valenti,8),
            new Card(R.drawable.sds_elite_demon_derieri,9), new Card(R.drawable.sds_elite_demon_estarossa,10), new Card(R.drawable.sds_elite_demon_galland,11),
            new Card(R.drawable.sds_elite_demon_gloxinia,12), new Card(R.drawable.sds_elite_demon_melascula,13), new Card(R.drawable.sds_elite_demon_monspeet,14),
            new Card(R.drawable.sds_elite_demon_zeldris,15), new Card(R.drawable.sds_eternal_promise_diane,16), new Card(R.drawable.sds_executor_of_darkness_camila,17),
            new Card(R.drawable.sds_explosion_guila,18), new Card(R.drawable.sds_fairy_kings_forest_elaine,19), new Card(R.drawable.sds_fang_of_the_land_matrona,20),
            new Card(R.drawable.sds_forest_guardian_helbram,21), new Card(R.drawable.sds_forest_guardian_king,22), new Card(R.drawable.sds_godspeed_knight_jericho,23),
            new Card(R.drawable.sds_greatest_soldier_levi,24), new Card(R.drawable.sds_greatest_soldier_mikasa_ackerman,25), new Card(R.drawable.sds_halloween__meliodas,26),
            new Card(R.drawable.sds_halloween_elaine,27), new Card(R.drawable.sds_halloween_gowther,28), new Card(R.drawable.sds_halloween_guila,29), new Card(R.drawable.sds_harlequin_king,30),
            new Card(R.drawable.sds_holy_knight_escanor,31), new Card(R.drawable.sds_holy_warrior_elizabeth,32), new Card(R.drawable.sds_infinity_merlin,33), new Card(R.drawable.sds_iron_wall_knight_griamore,34),
            new Card(R.drawable.sds_kijin_benimaru,35), new Card(R.drawable.sds_knight_of_wrath_meliodas,36), new Card(R.drawable.sds_knighthood_of_scraps_disposal_hawk,37),
            new Card(R.drawable.sds_kof_98_athena,38), new Card(R.drawable.sds_kof_98_kyo,39), new Card(R.drawable.sds_kof_98_mai,40), new Card(R.drawable.sds_kof_98_rugal,41),
            new Card(R.drawable.sds_kungfu_master_diane,42), new Card(R.drawable.sds_liones_elizabeth,43), new Card(R.drawable.sds_lioness_hero_gowther,44), new Card(R.drawable.sds_lostvayne_meliodas,45),
            new Card(R.drawable.sds_mad_destroyer_roxy,46), new Card(R.drawable.sds_mastermind_lillia,47), new Card(R.drawable.sds_matrona_diane,48), new Card(R.drawable.sds_melt_hendrickson,49),
            new Card(R.drawable.sds_new_legend_elizabeth,50), new Card(R.drawable.sds_new_legend_jericho,51), new Card(R.drawable.sds_new_legend_meliodas,52), new Card(R.drawable.sds_nunchaku_ban,53),
            new Card(R.drawable.sds_outlaw_ban,54), new Card(R.drawable.sds_overpower_slater,55), new Card(R.drawable.sds_protector_of_dolls_king,56), new Card(R.drawable.sds_reincarnation_of_conviction_zaratras,57),
            new Card(R.drawable.sds_reincarnation_of_revenge_helbram,58), new Card(R.drawable.sds_reverse_elizabeth_and_hawk,59), new Card(R.drawable.sds_ruler_of_monsters_rimuru,60), new Card(R.drawable.sds_ruler_of_stormy_seas_eastin,61),
            new Card(R.drawable.sds_snatch_ban,62), new Card(R.drawable.sds_sweet_temptation_elaine,63), new Card(R.drawable.sds_sweet_temptation_jenna,64), new Card(R.drawable.sds_sweet_temptation_zaneri,65),
            new Card(R.drawable.sds_tempest_howzer,66), new Card(R.drawable.sds_ten_commandments_derieri,67), new Card(R.drawable.sds_ten_commandments_estarossa,68), new Card(R.drawable.sds_ten_commandments_fraudrin,69),
            new Card(R.drawable.sds_ten_commandments_galland,70), new Card(R.drawable.sds_ten_commandments_gloxinia,71), new Card(R.drawable.sds_ten_commandments_melascula,72), new Card(R.drawable.sds_ten_commandments_monspeet,73),
            new Card(R.drawable.sds_the_boar_sin_of_gluttony_merlin,74), new Card(R.drawable.sds_the_dragon_sin_of_wrath_meliodas,75), new Card(R.drawable.sds_the_fox_sin_of_greed_ban,76), new Card(R.drawable.sds_the_goat_sin_of_lust_gowther,77),
            new Card(R.drawable.sds_the_grizzly_sin_of_sloth_king,78), new Card(R.drawable.sds_the_lion_sin_of_pride_escanor,79), new Card(R.drawable.sds_the_pleiades_of_the_blue_sky_deathpierce,80),
            new Card(R.drawable.sds_the_pleiades_of_the_blue_sky_deldry,81), new Card(R.drawable.sds_the_serpent_sin_of_envy_diane,82), new Card(R.drawable.sds_the_seven_deadly_sins_diane,83),
            new Card(R.drawable.sds_the_seven_deadly_sins_king,84), new Card(R.drawable.sds_the_seven_deadly_sins_meliodas,85), new Card(R.drawable.sds_the_ten_commandments_zeldris,86), new Card(R.drawable.sds_thunderbolt_gilthunder,87),
            new Card(R.drawable.sds_titan_form_eren_jaeger,88), new Card(R.drawable.sds_tyrant_of_destruction_milim,89)));

    public SDSBanner(int bannerImage, ArrayList<Card> bannerPool,ArrayList<Card> rateUpPool){
        image = bannerImage;
        rateUp = rateUpPool;
        banner = bannerPool;
    }

    public static ArrayList<Card> findCardsByID(ArrayList<Integer> ids) {
        ArrayList<Card> results = new ArrayList<>();
        for (int id : ids)
            for (Card card : SSRS)
                if (card.getCardID() == id)
                    results.add(card);

        return results;
    }

    public Card[] multiSummon() {
        Card[] results = new Card[11];

        if (rateUp == null)
        {
            for (int i = 0; i < 11;i++)
            {
                int num = rng.nextInt(101);
                if (num >=0 && num <= 3)
                    results[i] = banner.get(rng.nextInt(banner.size()));
                else if (num > 3 && num <= 40)
                    results[i] = SR;
                else
                    results[i] = RARE;
            }
        }
        else
        {
            for (int i = 0; i < 11;i++)
            {
                int num = rng.nextInt(101);
                if (num >= 0 && num <= 0.5 * rateUp.size())
                    results[i] = rateUp.get(rng.nextInt(rateUp.size()));
                else if (num > 0.5 * rateUp.size() && num <= 3)
                    results[i] = banner.get(rng.nextInt(banner.size()));
                else if (num > 3 && num <= 40)
                    results[i] = SR;
                else
                    results[i] = RARE;
            }
        }
        return results;
    }

    public Card singleSummon(){
        int num = rng.nextInt(101);
        if (rateUp == null)
        {
            if (num >=0 && num <= 3)
                return banner.get(rng.nextInt(banner.size()));
            else if (num > 3 && num <= 40)
                return SR;
            else
                return RARE;
        }
        else
        {
            if (num >= 0 && num <= 0.5 * rateUp.size())
                return rateUp.get(rng.nextInt(rateUp.size()));
            else if (num > 0.5 * rateUp.size() && num <= 3)
                return banner.get(rng.nextInt(banner.size()));
            else if (num > 3 && num <= 40)
                return SR;
            else
                return RARE;
        }
    }
    public int getImage() {
        return image;
    }


}
