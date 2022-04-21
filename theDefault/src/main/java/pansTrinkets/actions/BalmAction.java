package pansTrinkets.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import java.util.Iterator;

public class BalmAction extends AbstractGameAction {
    private final AbstractPlayer p;

    public BalmAction(AbstractPlayer player) {
        this.p = player;
    }

    @Override
    public void update() {
        Iterator<AbstractCard> pile = p.drawPile.group.iterator();
        while (pile.hasNext()) {
            AbstractCard c = pile.next();
            if (c.type.equals(AbstractCard.CardType.STATUS)) {
                addToBot(new ExhaustSpecificCardAction(c, p.drawPile));
            }
        }
        Iterator<AbstractCard> hand = p.hand.group.iterator();
        while (hand.hasNext()) {
            AbstractCard c = hand.next();
            if (c.type.equals(AbstractCard.CardType.STATUS)) {
                addToBot(new ExhaustSpecificCardAction(c, p.hand));
            }
        }
        Iterator<AbstractCard> discard = p.discardPile.group.iterator();
        while (discard.hasNext()) {
            AbstractCard c = discard.next();
            if (c.type.equals(AbstractCard.CardType.STATUS)) {
                addToBot(new ExhaustSpecificCardAction(c, p.discardPile));
            }
        }
        this.isDone = true;
    }

}
