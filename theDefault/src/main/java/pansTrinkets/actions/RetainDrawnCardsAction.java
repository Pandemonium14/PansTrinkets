package pansTrinkets.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class RetainDrawnCardsAction extends AbstractGameAction {

    public RetainDrawnCardsAction() {
    }

    @Override
    public void update() {
        for (AbstractCard c : DrawCardAction.drawnCards) {
            c.retain = true;
        }
        isDone = true;
    }
}