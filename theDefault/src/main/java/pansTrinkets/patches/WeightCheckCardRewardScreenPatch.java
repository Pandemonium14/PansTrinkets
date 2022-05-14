package pansTrinkets.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import pansTrinkets.cards.AbstractTrinket;
import pansTrinkets.helpers.TrinketHelper;

@SpirePatch(clz = CardRewardScreen.class, method = "update")
public class WeightCheckCardRewardScreenPatch {
    @SpirePrefixPatch
    public static void patch(CardRewardScreen __Instance) {
        AbstractCard card = ReflectionHacks.getPrivate(__Instance,CardRewardScreen.class, "touchCard");
        if (card != null && card.color.equals(EnumColorPatch.TRINKET_WHITE)) {
            AbstractTrinket trinket = (AbstractTrinket) card;
            if (trinket.weight + TrinketHelper.carriedWeight(AbstractDungeon.player) > TrinketHelper.maxWeight && AbstractDungeon.previousScreen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
                ReflectionHacks.setPrivate(__Instance,CardRewardScreen.class, "touchCard", null);
            }
        }
    }
}
