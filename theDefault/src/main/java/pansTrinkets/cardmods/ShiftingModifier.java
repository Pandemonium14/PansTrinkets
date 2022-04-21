package pansTrinkets.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pansTrinkets.actions.ShiftingAction;
import pansTrinkets.cards.AbstractTrinket;
import pansTrinkets.cards.FriendlyMimic;
import pansTrinkets.helpers.TrinketLibrary;

public class ShiftingModifier extends AbstractCardModifier {

    //This card modifier currently only works properly on trinkets.

    public static String ID = "panstrinkets:ShiftingModifier";
    public boolean upgraded = false;

    public ShiftingModifier(boolean upgraded) {

        this.upgraded = upgraded;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        if ( !(card instanceof FriendlyMimic)) {
            return rawDescription + " NL panstrinkets:Shifting.";
        } else {
            return rawDescription;
        }
    }

    @Override
    public void onInitialApplication(AbstractCard c) {
        ((AbstractTrinket)c).enableOnDrawActions = false;
    }

    @Override
    public void onDrawn(AbstractCard card) {
        AbstractDungeon.actionManager.addToBottom(new ShiftingAction(AbstractDungeon.player.hand, (AbstractTrinket) card, upgraded));
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        if (card.shuffleBackIntoDrawPile) {
            AbstractDungeon.actionManager.addToBottom(
                    new ShiftingAction(AbstractDungeon.player.drawPile, (AbstractTrinket) card, upgraded));
        } else if (card.returnToHand) {
            AbstractDungeon.actionManager.addToBottom(
                    new ShiftingAction(AbstractDungeon.player.hand, (AbstractTrinket) card, upgraded));
        } else if (card.exhaust){
            AbstractDungeon.actionManager.addToBottom(
                    new ShiftingAction(AbstractDungeon.player.discardPile, (AbstractTrinket) card, upgraded, false, AbstractDungeon.player.exhaustPile));
        } else if (card.type == AbstractCard.CardType.POWER) {
            AbstractDungeon.actionManager.addToBottom(
                    new ShiftingAction(AbstractDungeon.player.discardPile, (AbstractTrinket) card, upgraded, false, null));
        } else {
            AbstractDungeon.actionManager.addToBottom(
                    new ShiftingAction(AbstractDungeon.player.discardPile, (AbstractTrinket) card, upgraded));
        }
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        return card instanceof AbstractTrinket && !CardModifierManager.hasModifier(card, ID) ;
    }


    @Override
    public AbstractCardModifier makeCopy() {
        return new ShiftingModifier(this.upgraded);
    }
}
