package pansTrinkets.actions;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pansTrinkets.cardmods.MaddenedModifier;

import java.util.ArrayList;

public class ShardOfMadnessAction extends AbstractGameAction {

    private static final AbstractPlayer p = AbstractDungeon.player;;
    private ArrayList<AbstractCard> cardsToAddBack;

    public ShardOfMadnessAction() {
        actionType = ActionType.CARD_MANIPULATION;
        duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            cardsToAddBack = getUnapplicableCards();
            p.hand.group.removeAll(cardsToAddBack);
            AbstractDungeon.handCardSelectScreen.open("Reduce a card's cost to zero.",1,false);
            tickDuration();
        } else if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractCard cardInHand = AbstractDungeon.handCardSelectScreen.selectedCards.group.get(0);

            AbstractCard cardInDeck = StSLib.getMasterDeckEquivalent(cardInHand);

            cardInHand.cost = 0;
            if (cardInDeck != null) {
                CardModifierManager.addModifier(cardInDeck, new MaddenedModifier());
            }
            cardInHand.cost = 0;
            cardInHand.costForTurn = 0;
            cardInHand.isCostModified = true;
            for (AbstractCard c : cardsToAddBack) p.hand.addToTop(c);
            p.hand.refreshHandLayout();
            addToTop(new MakeTempCardInHandAction(cardInHand));

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.clear();
            isDone = true;
        }

    }

    public static ArrayList<AbstractCard> getUnapplicableCards() {
        ArrayList<AbstractCard> cards = new ArrayList<AbstractCard>();
        for (AbstractCard c : p.hand.group) {
            AbstractCard masterDeckCard = StSLib.getMasterDeckEquivalent(c);
            if (c.cost < 1 || masterDeckCard == null) {
                cards.add(c);
            }
        }
        return cards;
    }
}