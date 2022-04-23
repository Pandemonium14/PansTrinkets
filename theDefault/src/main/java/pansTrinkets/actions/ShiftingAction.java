package pansTrinkets.actions;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pansTrinkets.cardmods.ShiftingModifier;
import pansTrinkets.cards.AbstractTrinket;
import pansTrinkets.helpers.TrinketLibrary;

public class ShiftingAction extends AbstractGameAction {
    public CardGroup group;
    public AbstractTrinket card;
    public boolean upgraded;
    private boolean doSomething;
    public CardGroup destination;

    public ShiftingAction(CardGroup g, AbstractTrinket c, boolean upgraded) {
        group = g;
        card = c;
        this.upgraded = upgraded;
        doSomething = false;
        destination = g;
    }

    public ShiftingAction(CardGroup g, AbstractTrinket c, boolean upgraded, boolean doSomething) {
        group = g;
        card = c;
        this.upgraded = upgraded;
        this.doSomething = doSomething;
        destination = g;
    }

    public ShiftingAction(CardGroup g, AbstractTrinket c, boolean upgraded, boolean doSomething, CardGroup removeFrom) {
        group = g;
        card = c;
        this.upgraded = upgraded;
        this.doSomething = doSomething;
        destination = removeFrom;
    }

    @Override
    public void update() {
        if (!doSomething) {
            addToBot(new ShiftingAction(group,card,upgraded, true, destination));
            isDone = true;
            return;
        }
        if (destination != null) {
            destination.removeCard(card);
        }
        AbstractTrinket newTrinket = (AbstractTrinket) TrinketLibrary.allTrinkets.getRandomCard(AbstractDungeon.cardRng);
        while (CardModifierManager.hasModifier(newTrinket, ShiftingModifier.ID)) {
            newTrinket = (AbstractTrinket) TrinketLibrary.allTrinkets.getRandomCard(AbstractDungeon.cardRng);
        }
        if (upgraded) {
            newTrinket.upgrade();
        }
        if (group == AbstractDungeon.player.hand) {
            newTrinket.triggerWhenDrawn();
        }
        CardModifierManager.addModifier(newTrinket, new ShiftingModifier(this.upgraded));
        group.group.add(newTrinket);

        this.isDone = true;
    }
}
