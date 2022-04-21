package pansTrinkets.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class IllusoryModifier extends AbstractCardModifier {
    public static String ID = "panstrinkets:DazzlingModifier";

    public IllusoryModifier() {
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + " NL panstrinkets:Illusory.";// 14
    }

    public void onInitialApplication(AbstractCard card) {
        card.exhaust = true;
        card.isEthereal = true;// 24
    }

    public void onRemove(AbstractCard card) {
        AbstractCard temp = card.makeCopy();
        if (card.upgraded) {
            temp.upgrade();
        }
        card.exhaust = temp.exhaust;
        card.isEthereal = temp.isEthereal;
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
    //    AbstractDungeon.actionManager.addToBottom( new MakeTempCardInDrawPileAction( new Dazed(), 1, true, true));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new IllusoryModifier();
    }
}
