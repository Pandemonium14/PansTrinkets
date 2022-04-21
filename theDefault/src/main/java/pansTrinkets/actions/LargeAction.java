package pansTrinkets.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class LargeAction extends AbstractGameAction {

    private AbstractCard cardToKeep;
    private AbstractPlayer p;

    public LargeAction(AbstractCard card) {
        cardToKeep = card;
        p = AbstractDungeon.player;
        duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        CardGroup hand = p.hand;
        if (hand.size() > 1) {
            boolean discardedACard = false;
            while (!discardedACard) {
                AbstractCard c = hand.getRandomCard(AbstractDungeon.cardRng);
                if (!c.equals(cardToKeep)) {
                    hand.moveToDeck(c, false);
                    discardedACard = true;
                }
            }
        }
        isDone = true;
    }
}
