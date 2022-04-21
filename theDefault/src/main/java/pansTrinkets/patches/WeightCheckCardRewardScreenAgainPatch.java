package pansTrinkets.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import pansTrinkets.cards.AbstractTrinket;
import pansTrinkets.helpers.TrinketRewardHelper;

@SpirePatch(clz = CardRewardScreen.class, method = "cardSelectUpdate")
public class WeightCheckCardRewardScreenAgainPatch {
    @SpireInsertPatch(rloc = 27, localvars = {"hoveredCard"})
    public static void patch(CardRewardScreen __Instance, AbstractCard hoveredCard) {
        if (hoveredCard != null && hoveredCard.color.equals(EnumColorPatch.TRINKET_WHITE)) {
            AbstractTrinket trinket = (AbstractTrinket) hoveredCard;
            if (trinket.weight + TrinketRewardHelper.carriedWeight(AbstractDungeon.player) > TrinketRewardHelper.maxWeight) {
                hoveredCard.hb.clicked = false;
            }
        }
    }
}
