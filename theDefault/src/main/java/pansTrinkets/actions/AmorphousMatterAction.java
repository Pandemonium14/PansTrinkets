package pansTrinkets.actions;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pansTrinkets.cardmods.IllusoryModifier;
import pansTrinkets.cards.AbstractTrinket;
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
            ArrayList<AbstractCard> trinkets = TrinketReward.generateCardChoices();

            for (AbstractCard c : trinkets) {

                ((AbstractTrinket)c).weight = 0;

                if (upgraded) c.upgrade();
            }

            AbstractDungeon.cardRewardScreen.customCombatOpen(trinkets, "Choose a card to add to your hand.", true);
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
