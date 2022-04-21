package pansTrinkets.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pansTrinkets.DefaultMod;
import pansTrinkets.actions.BoomerangAction;
import pansTrinkets.util.TextureLoader;

import static pansTrinkets.DefaultMod.makePowerPath;

public class BoomerangPower extends AbstractPower {

    public static final String POWER_ID = DefaultMod.makeID("BoomerangPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));
    private boolean addBack;

    public BoomerangPower(AbstractCreature owner, int  amount) {
        name = NAME;
        ID = POWER_ID;

        this.amount = amount;
        this.owner = owner;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        addBack = false;

        updateDescription();
    }


    @Override
    public void onAfterUseCard(AbstractCard c, UseCardAction action) {
        if (addBack) {
            addToBot(new BoomerangAction(c));
            addToBot(new ReducePowerAction(owner, owner, BoomerangPower.POWER_ID, 1));
        } else {
            addBack = true;
        }
    }

    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0];
        } else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }



}
