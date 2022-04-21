package pansTrinkets.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FangHammerAction extends AbstractGameAction {

    public DamageInfo damageInfo;
    public AbstractMonster target;

    public FangHammerAction(AbstractMonster m, DamageInfo info ) {
        target = m;
        damageInfo = info;
    }

    public void update() {
        int monsterHealth = target.currentHealth;
        this.target.damage(this.damageInfo);
        if (((this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {
            addToBot(new HealAction(AbstractDungeon.player,AbstractDungeon.player,monsterHealth));
        }
        this.isDone = true;
    }


}
