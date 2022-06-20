package pansTrinkets.cards.finished;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import pansTrinkets.DefaultMod;
import pansTrinkets.cards.AbstractTrinket;
import pansTrinkets.helpers.TrinketHelper;

import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class SealedBox extends AbstractTrinket {
    public static final String ID = DefaultMod.makeID(SealedBox.class.getSimpleName());
    public static final String IMG = makeCardPath("SealedBox.png");


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 2;
    private int maxWeightCost = 2;

    public SealedBox() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = 75;
        weight = 2;
        FleetingField.fleeting.set(this, true);
        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (TrinketHelper.maxWeight >= maxWeightCost) {
            AbstractDungeon.effectList.add(new RainingGoldEffect(this.magicNumber * 2, true));
            this.addToBot(new GainGoldAction(this.magicNumber));
            TrinketHelper.changeMaxWeight(-maxWeightCost);
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return TrinketHelper.maxWeight >= maxWeightCost && hasEnoughEnergy();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(75);
            super.upgrade();
            this.initializeDescription();
        }
    }
}
