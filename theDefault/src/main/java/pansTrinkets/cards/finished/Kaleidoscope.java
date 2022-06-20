package pansTrinkets.cards.finished;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;
import pansTrinkets.cards.AbstractTrinket;
import pansTrinkets.powers.KaleidoscopePower;

import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class Kaleidoscope extends AbstractTrinket {
    public static final String ID = DefaultMod.makeID(Kaleidoscope.class.getSimpleName());
    public static final String IMG = makeCardPath("Kaleidoscope.png");


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 1;

    public Kaleidoscope() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.weight = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot( new ApplyPowerAction(p, p, new KaleidoscopePower(p ,p ,1)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
            super.upgrade();
            this.initializeDescription();
        }
    }
}
