package pansTrinkets.actions;

import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pansTrinkets.DefaultMod;
import pansTrinkets.cards.OrnateMirror;

import java.util.ArrayList;
import java.util.Iterator;

public class OrnateMirrorAction extends AbstractGameAction {

    private final AbstractPlayer p;
    private boolean makeZeroCost = false;
    private ArrayList<AbstractCard> cannotDuplicate = new ArrayList();

    public OrnateMirrorAction(AbstractPlayer player, boolean upgraded) {
        this.actionType = ActionType.DRAW;
        this.p = player;
        this.makeZeroCost = upgraded;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            cannotDuplicate = getUnapplicableCards();
            p.hand.group.removeAll(cannotDuplicate);
            if (this.p.hand.isEmpty()) {
                p.hand.group.addAll(cannotDuplicate);
                this.isDone = true;
            } else if (this.p.hand.size() == 1 ){
                for (AbstractCard C : p.hand.group) {

                    AbstractCard newCard = C.makeStatEquivalentCopy();
                    if (makeZeroCost && newCard.costForTurn >= 1) {
                        newCard.costForTurn = newCard.costForTurn - 1;
                    }
                    addToBot(new MakeTempCardInHandAction(newCard));
                }
                p.hand.group.addAll(cannotDuplicate);
                this.isDone = true;
            } else {
                AbstractDungeon.handCardSelectScreen.open(DefaultMod.actionsStrings.TEXT[1], 1, true, true);
                this.tickDuration();
            }
        } else if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {

                AbstractCard newCard = c.makeStatEquivalentCopy();
                if (makeZeroCost && newCard.costForTurn >= 1) {
                    newCard.costForTurn = newCard.costForTurn - 1;
                }
                addToBot(new MakeTempCardInHandAction(c, true, true));
                p.hand.group.add(c);

            }
            p.hand.group.addAll(cannotDuplicate);
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

    }

    public static ArrayList<AbstractCard> getUnapplicableCards() {
        ArrayList<AbstractCard> cards = new ArrayList<AbstractCard>();
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (FleetingField.fleeting.get(c) || c instanceof OrnateMirror) {
                cards.add(c);
            }
        }
        return cards;
    }

}// 96




