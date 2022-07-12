package pansTrinkets.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pansTrinkets.DefaultMod;
import pansTrinkets.cards.UnboundFury;
import pansTrinkets.util.TextureLoader;

import static com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch.NEUTRAL;
import static pansTrinkets.DefaultMod.makePowerPath;

public class FuryPower extends AbstractPower implements InvisiblePower {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("FuryPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public FuryPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = NEUTRAL;
        isTurnBased = false;


        loadRegion("vigor");

        updateDescription();
    }

    public FuryPower() {
        name = NAME;
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = 1;
        this.source = AbstractDungeon.player;

        type = PowerType.BUFF;
        isTurnBased = false;


        loadRegion("vigor");

        updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        int numberOfFury = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof UnboundFury) {
                numberOfFury += 1;
            }
        }
        if (!card.purgeOnUse && this.amount > 0 && card.type == AbstractCard.CardType.ATTACK) {
            for (int count = 0 ; count<numberOfFury; count++) {
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
        }
    }


}
