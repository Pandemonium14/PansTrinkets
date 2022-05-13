package pansTrinkets.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;

public class EchoChamberPower extends TwoAmountPower {

    public static final String POWER_ID = DefaultMod.makeID("EchoChamberPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;

    public String[] descriptions;

    public EchoChamberPower(AbstractCreature owner, int  amount) {
        name = NAME;
        ID = POWER_ID;

        this.amount = amount;
        this.owner = owner;

        type = PowerType.BUFF;
        isTurnBased = false;

        loadRegion("echo");


        descriptions = powerStrings.DESCRIPTIONS;
        updateDescription(descriptions);
    }

    public void updateDescription(String[] desc) {
        description = desc[0] + (amount+1) + desc[1];
    }


    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && this.amount > 0 && amount2==4) {// 46
            this.flash();// 49
            for (int count = 0 ; count<amount; count++) {
                AbstractMonster m = null;// 50
                if (action.target != null) {// 52
                    m = (AbstractMonster)action.target;// 53
                }

                AbstractCard tmp = card.makeSameInstanceOf();// 56
                AbstractDungeon.player.limbo.addToBottom(tmp);// 57
                tmp.current_x = card.current_x;// 58
                tmp.current_y = card.current_y;// 59
                tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;// 60
                tmp.target_y = (float)Settings.HEIGHT / 2.0F;// 61
                if (m != null) {// 63
                    tmp.calculateCardDamage(m);
                }
                tmp.purgeOnUse = true;
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
            }
            amount2 = 0;
            // 68
        } else if (!card.purgeOnUse) {
            amount2 += 1;
        }
    }

}
