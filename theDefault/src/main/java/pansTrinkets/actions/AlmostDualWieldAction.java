//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package pansTrinkets.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import java.util.ArrayList;
import java.util.Iterator;

public class AlmostDualWieldAction extends AbstractGameAction {

    private static final float DURATION_PER_CARD = 0.25F;
    private AbstractPlayer p;
    private int dupeAmount = 1;
    private ArrayList<AbstractCard> cannotDuplicate = new ArrayList();

    public AlmostDualWieldAction(AbstractCreature source, int amount) {
        this.setValues(AbstractDungeon.player, source, amount);// 26
        this.actionType = ActionType.DRAW;// 27
        this.duration = 0.25F;// 28
        this.p = AbstractDungeon.player;// 29
        this.dupeAmount = amount;// 30
    }// 31

    public void update() {
        Iterator var1;
        AbstractCard c;
        int i;
        if (this.duration == Settings.ACTION_DUR_FAST) {// 36
            var1 = this.p.hand.group.iterator();// 38

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                if (!this.isDualWieldable(c)) {// 39
                    this.cannotDuplicate.add(c);// 40
                }
            }

            if (this.cannotDuplicate.size() == this.p.hand.group.size()) {// 45
                this.isDone = true;// 46
                return;// 47
            }

            if (this.p.hand.group.size() - this.cannotDuplicate.size() == 1) {// 50
                var1 = this.p.hand.group.iterator();// 51

                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    if (this.isDualWieldable(c)) {// 52
                        for(i = 0; i < this.dupeAmount; ++i) {// 53
                            this.addToTop(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));// 54
                        }

                        this.isDone = true;// 56
                        return;// 57
                    }
                }
            }

            this.p.hand.group.removeAll(this.cannotDuplicate);// 64
            if (this.p.hand.group.size() > 1) {// 66
                AbstractDungeon.handCardSelectScreen.open("duplicate.", 1, false, false, false, false);// 67
                this.tickDuration();// 68
                return;// 69
            }

            if (this.p.hand.group.size() == 1) {// 70
                for(int j = 0; j < this.dupeAmount; ++j) {// 71
                    this.addToTop(new MakeTempCardInHandAction(this.p.hand.getTopCard().makeStatEquivalentCopy()));// 72
                }

                this.returnCards();// 74
                this.isDone = true;// 75
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {// 80
            var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();// 81

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                this.addToTop(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));// 82

                for(i = 0; i < this.dupeAmount; ++i) {// 83
                    this.addToTop(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));// 84
                }
            }

            this.returnCards();// 88
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;// 90
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();// 91
            this.isDone = true;// 92
        }

        this.tickDuration();// 95
    }// 96

    private void returnCards() {
        Iterator var1 = this.cannotDuplicate.iterator();// 99

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            this.p.hand.addToTop(c);// 100
        }

        this.p.hand.refreshHandLayout();// 102
    }// 103

    private boolean isDualWieldable(AbstractCard card) {
        return card.type.equals(CardType.ATTACK) || card.type.equals(CardType.POWER);// 106
    }

}
