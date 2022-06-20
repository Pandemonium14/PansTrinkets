package pansTrinkets.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import pansTrinkets.DefaultMod;
import pansTrinkets.util.TextureLoader;

import static pansTrinkets.DefaultMod.makePowerPath;

public class SuperconductivePower extends TrinketPower {

    public static final String POWER_ID = DefaultMod.makeID("SuperconductivePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public SuperconductivePower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.loadRegion("mastery");
        descriptions = powerStrings.DESCRIPTIONS;

    }

    @Override
    public void updateDescription() {
        description = descriptions[0] + amount + descriptions[1] + amount + descriptions[2];
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (isPlayer) {
            boolean hasLightning = false;
            boolean hasFrost = false;
            for (AbstractOrb orb : AbstractDungeon.player.orbs) {
                if (orb instanceof Frost) {
                    hasFrost = true;
                } else if (orb instanceof Lightning) {
                    hasLightning = true;
                }
            }
            for (int i = 0 ; i < amount; i++ ) {
                if (!hasLightning) {
                    addToBot(new ChannelAction(new Lightning()));
                }
                if (!hasFrost) {
                    addToBot(new ChannelAction(new Frost()));
                }
            }
        }
    }
}
