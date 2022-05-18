package pansTrinkets.patches;


import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.neow.NeowReward;
import pansTrinkets.DefaultMod;
import pansTrinkets.helpers.TrinketHelper;
import pansTrinkets.rewards.TrinketReward;

import java.util.ArrayList;

public class NeowPatches {

    private static final String drawbackText = "Reduce your max weight by 2";
    private static final String chooseTrinketText = "Choose a random trinket to obtain.";
    private static final String smallMaxWeightText = "Increase your max weight by 2.";
    private static final String rareTrinketText = "Choose a rare trinket to obtain.";
    private static final String bigMaxWeightText = "Increase your max weight by 4.";


    @SpireEnum
    public static NeowReward.NeowRewardType CHOOSE_TRINKET, MAX_WEIGHT_SMALL;

    @SpireEnum
    public static NeowReward.NeowRewardType CHOOSE_RARE_TRINKET, MAX_WEIGHT_BIG;

    @SpireEnum
    public static NeowReward.NeowRewardDrawback LOSE_MAX_WEIGHT;


    @SpirePatch(clz = NeowReward.class, method = "getRewardDrawbackOptions")
    public static class NewDrawback {
        @SpirePostfixPatch
        public static ArrayList<NeowReward.NeowRewardDrawbackDef> patch(ArrayList<NeowReward.NeowRewardDrawbackDef> __result, NeowReward __instance) {
            if (DefaultMod.enableNeowOptions) __result.add(new NeowReward.NeowRewardDrawbackDef(LOSE_MAX_WEIGHT, "[ " + FontHelper.colorString(drawbackText, "r") + " "));
            return __result;
        }
    }

    @SpirePatch(
            clz = NeowReward.class,
            method = "getRewardOptions"
    )
    public static class AddRewards {
        @SpirePostfixPatch
        public static ArrayList<NeowReward.NeowRewardDef> patch(ArrayList<NeowReward.NeowRewardDef> __result, NeowReward __instance, final int category) {
            if (DefaultMod.enableNeowOptions){
                if (category == 0) {
                    __result.add(new NeowReward.NeowRewardDef(CHOOSE_TRINKET, "[ " + FontHelper.colorString(chooseTrinketText,"g") + " ]"));
                }
                if (category == 1) {
                    __result.add(new NeowReward.NeowRewardDef(MAX_WEIGHT_SMALL, "[ " + FontHelper.colorString(smallMaxWeightText,"g") + " ]"));
                }

                if (category == 2) {
                    __result.add(new NeowReward.NeowRewardDef(CHOOSE_RARE_TRINKET, FontHelper.colorString(rareTrinketText,"g") + " ]"));

                    if (__instance.drawback != LOSE_MAX_WEIGHT) {
                        __result.add(new NeowReward.NeowRewardDef(MAX_WEIGHT_BIG, FontHelper.colorString(bigMaxWeightText ,"g") + " ]"));
                    }
                }
            }
            return __result;
        }
    }

    @SpirePatch(
            clz = NeowReward.class,
            method = "activate"
    )
    public static class ActivatePatch {
        @SpirePrefixPatch
        public static void patch(NeowReward __instance) {
            if(__instance.drawback == LOSE_MAX_WEIGHT) {
                TrinketHelper.changeMaxWeight(-2);
            }

            if (__instance.type == CHOOSE_TRINKET) {
                AbstractDungeon.cardRewardScreen.open(TrinketReward.generateCardChoices(),null,"Choose a trinket");
            } else if (__instance.type == MAX_WEIGHT_SMALL) {
                TrinketHelper.changeMaxWeight(2);
            } else if (__instance.type == MAX_WEIGHT_BIG) {
                TrinketHelper.changeMaxWeight(4);
            } else if (__instance.type == CHOOSE_RARE_TRINKET) {
                AbstractDungeon.cardRewardScreen.open(TrinketReward.generateRareCardChoices(),null,"Choose a trinket");
            }
        }
    }
}



