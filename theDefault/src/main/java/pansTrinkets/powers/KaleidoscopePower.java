package pansTrinkets.powers;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pansTrinkets.DefaultMod;
import pansTrinkets.cardmods.IllusoryModifier;
import pansTrinkets.util.TextureLoader;

import static pansTrinkets.DefaultMod.makePowerPath;

public class KaleidoscopePower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("KaleidoscopePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public KaleidoscopePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void atStartOfTurn() {
        for(int i = 0; i < this.amount; ++i) {// 28


            int r = AbstractDungeon.cardRng.random(99);
            AbstractCard.CardRarity rarity;
            if (r < 15) {
                rarity = AbstractCard.CardRarity.RARE;
            } else if (r < 45) {
                rarity = AbstractCard.CardRarity.UNCOMMON;
            } else {
                rarity = AbstractCard.CardRarity.COMMON;
            }

            AbstractCard newCard = CardLibrary.getAnyColorCard(rarity);
            CardModifierManager.addModifier(newCard, new IllusoryModifier());
            this.addToBot(new MakeTempCardInHandAction(newCard));
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
