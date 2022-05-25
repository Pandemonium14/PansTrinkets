package pansTrinkets.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pansTrinkets.cards.SpentLodestone;

public class LodestoneDrawPileAction extends AbstractGameAction {

    private AbstractPlayer p = AbstractDungeon.player;
    private AbstractCard cardToSwap;


    public LodestoneDrawPileAction(AbstractCard c) {
        actionType = ActionType.CARD_MANIPULATION;
        duration = Settings.ACTION_DUR_FAST;
        cardToSwap = c;
    }


    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

            for (AbstractCard c : p.drawPile.group) {
                tmp.addToRandomSpot(c);
            }
            if (tmp.size() == 0) {
                addToBot(new MakeTempCardInDrawPileAction(new SpentLodestone(),1,true, true));
                isDone = true;
                return;
            }
            if (tmp.size() == 1) {
                AbstractCard card = tmp.getTopCard();
                p.drawPile.addToTop(cardToSwap);
                p.drawPile.removeCard(card);
                p.hand.group.add(card);
                p.hand.refreshHandLayout();
                isDone = true;
                return;
            } else {
                AbstractDungeon.gridSelectScreen.open(tmp,1, false,"Choose a card to add to your hand.");
                tickDuration();
            }
        }
        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            int index = p.drawPile.group.indexOf(card);
            p.drawPile.group.set(index, cardToSwap);
            p.hand.group.add(card);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            isDone = true;
        }
    }
}
