package pansTrinkets.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.rooms.TreasureRoom;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;
import com.megacrit.cardcrawl.ui.buttons.ProceedButton;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import pansTrinkets.helpers.TrinketRewardHelper;
import pansTrinkets.relics.BottomlessBag;
import pansTrinkets.rewards.LinkedCardReward;
import pansTrinkets.rewards.TrinketReward;

import java.util.ArrayList;
import java.util.Iterator;

@SpirePatch(
        clz= CombatRewardScreen.class,
        method="setupItemReward"
)
public class CombatRewardPatch {
    @SpireInsertPatch(locator = Locator.class)
    public static void patch(CombatRewardScreen __Instance) {
        if ((AbstractDungeon.getCurrRoom().event == null || AbstractDungeon.getCurrRoom().event != null && !AbstractDungeon.getCurrRoom().event.noCardsInRewards) && !(AbstractDungeon.getCurrRoom() instanceof TreasureRoom) && !(AbstractDungeon.getCurrRoom() instanceof RestRoom)) {
            RewardItem reward = __Instance.rewards.get(__Instance.rewards.size() - 1);
            ArrayList<AbstractCard> cards = reward.cards;
            boolean makeTrinket = false;
            Iterator iter = cards.iterator();
            while (iter.hasNext() && !makeTrinket) {
                AbstractCard c = (AbstractCard) iter.next();
                if (c.rarity == AbstractCard.CardRarity.RARE) {
                    makeTrinket = true;
                }
            }
            if (makeTrinket) {
                if (!AbstractDungeon.player.hasRelic(BottomlessBag.ID)) {
                    TrinketRewardHelper.maxWeight = TrinketRewardHelper.baseMaxWeight;
                }
                __Instance.rewards.remove(reward);
                TrinketReward trinkets = new TrinketReward(false);
                LinkedCardReward cardReward = new LinkedCardReward(cards,trinkets);
                __Instance.rewards.add(cardReward);
                __Instance.rewards.add(trinkets);
            }
        }
    }


    public static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher matcher = new Matcher.MethodCallMatcher(ProceedButton.class, "show");
            return LineFinder.findInOrder(ctMethodToPatch, matcher);
        }
    }

}
