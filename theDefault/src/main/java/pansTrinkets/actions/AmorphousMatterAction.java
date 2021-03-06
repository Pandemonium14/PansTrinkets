package pansTrinkets.actions;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pansTrinkets.DefaultMod;
import pansTrinkets.cardmods.IllusoryModifier;
import pansTrinkets.cards.AbstractTrinket;
import pansTrinkets.cards.UnboundFury;
import pansTrinkets.rewards.TrinketReward;

import java.util.ArrayList;

public class AmorphousMatterAction extends AbstractGameAction {

    private final boolean upgraded;

    public AmorphousMatterAction(boolean upgraded) {
        this.upgraded = upgraded;
        actionType = ActionType.CARD_MANIPULATION;
        duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> trinkets = TrinketReward.generateCombatCardChoices();

            for (AbstractCard c : trinkets) {
                if (upgraded) c.upgrade();
                ((AbstractTrinket)c).weight = 0;
            }

            AbstractDungeon.cardRewardScreen.customCombatOpen(trinkets, DefaultMod.actionsStrings.TEXT[0], true);
            tickDuration();
        } else {
            if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                AbstractCard card = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                CardModifierManager.addModifier(card, new IllusoryModifier());
                addToBot( new MakeTempCardInHandAction(card));
            }
            isDone = true;
        }
    }
}
