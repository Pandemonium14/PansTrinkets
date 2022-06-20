package pansTrinkets.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Iterator;

public class OrnateMirrorAction extends AbstractGameAction {

    private final AbstractPlayer p;
    private boolean makeZeroCost = false;
    private final ArrayList<AbstractCard> cannotDuplicate = new ArrayList();

    public OrnateMirrorAction(AbstractPlayer player, boolean upgraded) {
        this.actionType = ActionType.DRAW;
        this.p = player;
        this.makeZeroCost = upgraded;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
            } else if (this.p.hand.size() == 1 ){
                for (AbstractCard C : p.hand.group) {

                    AbstractCard newCard = C.makeStatEquivalentCopy();
                    if (makeZeroCost && newCard.cost >= 1) {
                        newCard.costForTurn = newCard.cost - 1;
                    }
                    addToBot(new MakeTempCardInHandAction(newCard));

                    this.isDone = true;
                }
            } else {
                AbstractDungeon.handCardSelectScreen.open("duplicate.", 1, true, true);
                this.tickDuration();
            }
        } else if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {

                AbstractCard newCard = c.makeStatEquivalentCopy();
                if (makeZeroCost) {
                    newCard.costForTurn = 0;
                }
                addToBot(new MakeTempCardInHandAction(c));
                addToBot(new MakeTempCardInHandAction(newCard));


            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

    }

}// 96




