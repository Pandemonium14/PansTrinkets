package pansTrinkets.actions;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pansTrinkets.DefaultMod;
import pansTrinkets.cardmods.MaddenedModifier;
import pansTrinkets.helpers.TrinketHelper;
import pansTrinkets.relics.DefaultClickableRelic;

import java.util.ArrayList;

public class ShardOfMadnessAction extends AbstractGameAction {

    private static final AbstractPlayer p = AbstractDungeon.player;;
    private ArrayList<AbstractCard> cardsToAddBack;
    private int maxWeightCost;

    public ShardOfMadnessAction(int weightCost) {
        actionType = ActionType.CARD_MANIPULATION;
        duration = Settings.ACTION_DUR_FAST;
        maxWeightCost = weightCost;
    }

    @Override
    public void update() {


        if (duration == Settings.ACTION_DUR_FAST) {
            //WeightCheck
            if (TrinketHelper.maxWeight < maxWeightCost) {
                 isDone = true;
                 return;
            } else {
                TrinketHelper.changeMaxWeight(- maxWeightCost);
            }
            cardsToAddBack = getUnapplicableCards();
            p.hand.group.removeAll(cardsToAddBack);
            AbstractDungeon.handCardSelectScreen.open(DefaultMod.actionsStrings.TEXT[2],1,false);
            tickDuration();
        } else if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractCard cardInHand = AbstractDungeon.handCardSelectScreen.selectedCards.group.get(0);

            //do thing with cardInHand

            AbstractCard cardInDeck = StSLib.getMasterDeckEquivalent(cardInHand);

            cardInHand.cost = 0;
            if (cardInDeck != null) {
                CardModifierManager.addModifier(cardInDeck, new MaddenedModifier());
            }
            cardInHand.cost = 0;
            cardInHand.costForTurn = 0;
            cardInHand.isCostModified = true;
            for (AbstractCard c : cardsToAddBack) p.hand.addToTop(c);
            p.hand.addToTop(cardInHand);
            p.hand.refreshHandLayout();


            //mandatory stuff
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.clear();
            isDone = true;
        }

    }

    public static ArrayList<AbstractCard> getUnapplicableCards() {
        ArrayList<AbstractCard> cards = new ArrayList<AbstractCard>();
        for (AbstractCard c : p.hand.group) {
            AbstractCard masterDeckCard = StSLib.getMasterDeckEquivalent(c);
            if (masterDeckCard == null || masterDeckCard.cost < 1) {
                cards.add(c);
            }
        }
        return cards;
    }
}
