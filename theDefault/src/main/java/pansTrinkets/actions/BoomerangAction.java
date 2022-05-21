package pansTrinkets.actions;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.DiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pansTrinkets.powers.BoomerangPower;

public class BoomerangAction extends AbstractGameAction {


    private final AbstractCard card;


    public BoomerangAction(AbstractCard c) {
        this.card = c;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.discardPile.group.contains(card)) {
            p.discardPile.moveToHand(card);
        } else if (p.exhaustPile.group.contains(card)) {
            p.exhaustPile.moveToHand(card);
        } else if (p.drawPile.contains(card)) {
            p.drawPile.moveToHand(card);
        } else if (StSLib.getMasterDeckEquivalent(card)!=null) {
            addToBot(new MakeTempCardInHandAction(card.makeStatEquivalentCopy()));
        }
        this.isDone = true;
    }
}
